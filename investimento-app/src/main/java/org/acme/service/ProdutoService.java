package org.acme.service;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.dto.Analise;
import org.acme.dto.ResultadoSimulacao;
import org.acme.dto.request.SimulacaoRequest;
import org.acme.dto.response.SimulacaoResponse;
import org.acme.model.Produto;
import org.acme.model.Simulacao;
import org.acme.util.Calculadora;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProdutoService {

    public List<Produto> getProdutos(){
        return Produto.findProdutosDisponiveis();
    }

    public SimulacaoResponse getProdutoCompativel(SimulacaoRequest simulacaoRequest) {

        String tipoProduto = simulacaoRequest.getTipoProduto();
        BigDecimal valor = simulacaoRequest.getValor();
        int prazoMeses = simulacaoRequest.getPrazoMeses();

        List<Produto> produtosCompativeis = Produto.findByTipoAndValorAndPrazo(tipoProduto, valor, prazoMeses);

        if(produtosCompativeis.isEmpty())
            return null;

        List<Analise> analises = new ArrayList<>();

        for(Produto p : produtosCompativeis){

            BigDecimal rendimento = Calculadora.calculaRendimento(
                    valor,
                    p.getRentabilidadeAnual(),
                    prazoMeses
            );

            Log.info("rendimento "+ rendimento);
            Log.info("valor " + valor);
            Log.info("rentabilidade " + p.getRentabilidadeAnual());
            Log.info("prazo " + prazoMeses);

            ResultadoSimulacao resultadoSimulacao = new ResultadoSimulacao(rendimento, prazoMeses);
            analises.add(new Analise(p, resultadoSimulacao));
        }

        String dataSimulacao = Instant.now().toString();

        SimulacaoResponse simulacaoResponse = new SimulacaoResponse(analises, dataSimulacao);
        gravaSimulacao(simulacaoResponse, simulacaoRequest);

        return simulacaoResponse;

    }

    private void gravaSimulacao(SimulacaoResponse simulacaoResponse, SimulacaoRequest simulacaoRequest) {

        for(Analise a : simulacaoResponse.getSimulacoes()){

            Simulacao simulacaoInsert = new Simulacao();
            simulacaoInsert.setClienteId(simulacaoRequest.getClienteId());
            simulacaoInsert.setProdutoNome(a.getProdutoValidado().getNome());
            simulacaoInsert.setTipoProduto(a.getProdutoValidado().getTipoProduto());
            simulacaoInsert.setValorInvestido(simulacaoRequest.getValor());
            simulacaoInsert.setPrazoMeses(a.getResultadoSimulacao().getPrazoMeses());
            simulacaoInsert.setRentabilidadeAplicada(a.getProdutoValidado().getRentabilidadeAnual());
            simulacaoInsert.setValorFinal(a.getResultadoSimulacao().getValorFinal());
            simulacaoInsert.setDataSimulacao(simulacaoResponse.getDataSimulacao());

            Simulacao.insert(simulacaoInsert);
        }


    }
}
