package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.dto.Analise;
import org.acme.dto.ResultadoSimulacao;
import org.acme.dto.request.Simulacao;
import org.acme.dto.response.SimulacaoResponse;
import org.acme.model.Produto;
import org.acme.util.Calculadora;

import java.math.BigDecimal;
import java.util.ArrayList;
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

        String tipoProduto = simulacao.getTipoProduto();
        BigDecimal valor = simulacao.getValor();
        int prazoMeses = simulacao.getPrazoMeses();

        List<Produto> produtosCompativeis = produtoModel.findByTipoAndValorAndPrazo(tipoProduto, valor, prazoMeses);

        if(produtosCompativeis.isEmpty())
            return null;

        List<Analise> analises = new ArrayList<>();

        for(Produto p : produtosCompativeis){

            BigDecimal rendimento = Calculadora.calculaRendimento(
                    valor,
                    p.getRentabilidadeAnual(),
                    prazoMeses
            );

            ResultadoSimulacao resultadoSimulacao = new ResultadoSimulacao(rendimento, prazoMeses);
            analises.add(new Analise(p, resultadoSimulacao));
        }

        return new SimulacaoResponse(analises);

    }
}
