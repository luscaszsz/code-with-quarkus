package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Produtos;

import java.util.List;

@ApplicationScoped
public class ProdutoService {

    final Produtos produtos;


    public ProdutoService(Produtos produtos) {
        this.produtos = produtos;
    }

    public List<Produtos> getProdutos(){
        return produtos.buscaNomesProdutos();
    }
}
