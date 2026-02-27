package org.acme.resource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.model.Produtos;
import org.acme.service.ProdutoService;

import java.util.List;

@Path("/hello")
@ApplicationScoped
public class GreetingResource {

    final ProdutoService produtoService;

    public GreetingResource(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public List<Produtos> hello() {
        return produtoService.getProdutos();
    }

}
