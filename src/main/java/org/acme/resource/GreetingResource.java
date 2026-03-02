package org.acme.resource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.Simulacao;
import org.acme.dto.SimulacaoResponse;
import org.acme.service.ProdutoService;
import org.jboss.logging.Logger;

@Path("/investimento")
@ApplicationScoped
public class GreetingResource {

    private static final Logger LOG = Logger.getLogger(GreetingResource.class);

    final ProdutoService produtoService;

    public GreetingResource(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @POST
    @Path("/simulacoes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello(@Valid Simulacao simulacao) {

        SimulacaoResponse simulacaoResponse = produtoService.getProdutoCompativel(simulacao);

        LOG.info("DTO RECEBIDO: " + simulacao);

        if (simulacaoResponse.getContagemProdutos() == 0)
            return Response.status(422).entity("Não há produtos compatíveis com a simulação").build();
        return Response.ok(simulacaoResponse).build();

    }


    @POST
    @Path("/todas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello1(@Valid Simulacao simulacao) {

        return Response.ok().entity(produtoService.getProdutos()).build();

    }

}
