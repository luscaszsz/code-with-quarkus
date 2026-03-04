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
import org.acme.dto.response.SimulacaoResponse;
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
    @RolesAllowed({ "User", "Admin" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscaSimulacoes(@QueryParam("clienteId") Integer clienteId) {

        List<Simulacao> simulacoes = simulacaoService.retornaSimulacaoPorClientId(clienteId);

        if (simulacoes.isEmpty())
            return Response.status(422).entity("Não há simulações realizadas para o ID indicado").build();

        return Response.ok().entity(simulacoes).build();

    }

}
