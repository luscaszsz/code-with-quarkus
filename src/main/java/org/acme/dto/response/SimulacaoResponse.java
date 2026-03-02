package org.acme.dto.response;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import org.acme.dto.Analise;

import java.util.List;

@Getter
@JsonPropertyOrder({"contagemSimulacoes", "simulacoes"})
public class SimulacaoResponse {

    private final long contagemSimulacoes;

    private final List<Analise> simulacoes;

    public SimulacaoResponse(List<Analise> simulacoes) {
        this.contagemSimulacoes = simulacoes.size();
        this.simulacoes = simulacoes.isEmpty() ? null : simulacoes;
    }
}
