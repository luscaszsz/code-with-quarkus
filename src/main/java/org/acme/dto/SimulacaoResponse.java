package org.acme.dto;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import org.acme.model.Produto;

import java.util.List;

@Getter
@JsonPropertyOrder({"contagemProdutos", "produtos"})
public class SimulacaoResponse {

    private final long contagemProdutos;
    private final List<Produto> produtos;

    public SimulacaoResponse(List<Produto> produtos) {
        this.contagemProdutos = produtos.size();
        this.produtos = produtos.isEmpty() ? null : produtos;
    }
}
