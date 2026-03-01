package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Produto;

import java.util.List;

@ApplicationScoped
public class ProdutoService {

    final Produto produto;


    public ProdutoService(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getProdutos(){
        return produto.buscaNomesProdutos();
    }
}
