package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.dto.Simulacao;
import org.acme.dto.SimulacaoResponse;
import org.acme.model.Produto;

import java.util.List;

@ApplicationScoped
public class ProdutoService {

    final Produto produtoModel;

    public ProdutoService(Produto produto) {
        this.produtoModel = produto;
    }

    public List<Produto> getProdutos(){
        return produtoModel.buscaNomesProdutos();
    }

    public SimulacaoResponse getProdutoCompativel(Simulacao simulacao) {

        List<Produto> produtos = produtoModel.findByTipoAndValorAndPrazo(simulacao.getTipoProduto(), simulacao.getValor(), simulacao.getPrazoMeses());
        return new SimulacaoResponse(produtos);

    }
}
