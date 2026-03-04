package org.acme.dto;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import org.acme.model.Produto;

@Getter
@JsonPropertyOrder({"produtoValidado", "resultadoSimulacao"})
public class Analise {

    private final Produto produtoValidado;
    private final ResultadoSimulacao resultadoSimulacao;

    public Analise(Produto produto, ResultadoSimulacao resultadoSimulacao) {
        this.produtoValidado = produto;
        this.resultadoSimulacao = resultadoSimulacao;
    }
}
