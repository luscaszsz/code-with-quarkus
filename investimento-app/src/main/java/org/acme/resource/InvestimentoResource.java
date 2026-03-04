package org.acme.resource;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.request.SimulacaoRequest;
import org.acme.dto.response.ErroResponse;
import org.acme.dto.response.SimulacaoResponse;
import org.acme.exception.NegocialException;
import org.acme.exception.mapper.GlobalExceptionMapper;
import org.acme.model.Produto;
import org.acme.model.Simulacao;
import org.acme.service.ProdutoService;
import org.acme.service.SimulacaoService;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import java.util.List;

@Path("/investimento")
@ApplicationScoped
public class InvestimentoResource {

    @Inject
    JsonWebToken jwt;

    private static final Logger LOG = Logger.getLogger(InvestimentoResource.class);

    final ProdutoService produtoService;
    final SimulacaoService simulacaoService;

    public InvestimentoResource(ProdutoService produtoService, SimulacaoService simulacaoService) {
        this.produtoService = produtoService;
        this.simulacaoService = simulacaoService;
    }

    @GET
    @Path("/produtos")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscaProdutos(){

        List<Produto> produtos = produtoService.getProdutos();
        if(produtos.isEmpty())
            throw new RuntimeException();
        return Response.ok().entity(produtoService.getProdutos()).build();
    }

    @POST
    @Path("/simulacoes")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response geraSimulacao(@Valid SimulacaoRequest simulacao) {

        SimulacaoResponse simulacaoResponse = produtoService.getProdutoCompativel(simulacao);

        LOG.info("DTO RECEBIDO: " + simulacao);

        if (simulacaoResponse == null)
            return Response.status(422).entity("Não há produtos compatíveis com a simulação").build();
        return Response.ok(simulacaoResponse).build();

    }


    @GET
    @Path("/simulacoes")
    @RolesAllowed({ "Admin" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscaSimulacoes(@QueryParam("clienteId") Integer clienteId) {

        LOG.info("CLIENTE ID RECEBIDO: " + clienteId);

        List<Simulacao> simulacoes = simulacaoService.retornaSimulacaoPorClientId(clienteId);

        if (simulacoes.isEmpty())
            throw new NegocialException("Não há simulações realizadas para o ID indicado");

        return Response.ok().entity(simulacoes).build();

    }

}
