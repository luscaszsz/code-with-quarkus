package org.acme.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.InjectMock;
import org.acme.dto.Analise;
import org.acme.dto.ResultadoSimulacao;
import org.acme.dto.request.SimulacaoRequest;
import org.acme.dto.response.SimulacaoResponse;
import org.acme.model.Simulacao;
import org.acme.service.ProdutoService;
import org.acme.service.SimulacaoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
class InvestimentoResourceTest {

    @InjectMock
    ProdutoService produtoService;

    @InjectMock
    SimulacaoService simulacaoService;

    // =========================
    // TESTE POST
    // =========================

    @Test
    void deveGerarSimulacaoComSucesso() {

        // Arrange
        SimulacaoRequest request = new SimulacaoRequest(1, new BigDecimal("1000"), 12, "CDB");

        Analise analise = new Analise(null, new ResultadoSimulacao(BigDecimal.valueOf(1500), 12));
        List<Analise> analiseList = new ArrayList<>();
        analiseList.add(analise);

        SimulacaoResponse responseMock = new SimulacaoResponse(analiseList, Instant.now().toString());

        Mockito.when(produtoService.getProdutoCompativel(Mockito.any(SimulacaoRequest.class)))
                .thenReturn(responseMock);

        // Act + Assert
        given()
                .contentType("application/json")
                .body(request)
                .when()
                .post("/investimento/simulacoes")
                .then()
                .statusCode(200)
                .body("simulacoes[0].resultadoSimulacao.valorFinal", equalTo(1500));
    }

    @Test
    void deveRetornar422QuandoNaoHouverProdutoCompativel() {

        // Arrange
        SimulacaoRequest request = new SimulacaoRequest(1, new BigDecimal("1000"), 12, "CDB");

        Mockito.when(produtoService.getProdutoCompativel(Mockito.any(SimulacaoRequest.class)))
                .thenReturn(null);

        // Act + Assert
        given()
                .contentType("application/json")
                .body(request)
                .when()
                .post("/investimento/simulacoes")
                .then()
                .statusCode(422);
    }

    // =========================
    // TESTE GET
    // =========================

    @Test
    void deveRetornarSimulacoesQuandoExistirem() {

        // Arrange
        Simulacao simulacao = Simulacao.builder()
                .dataSimulacao(Instant.now().toString())
                .prazoMeses(12)
                .valorFinal(BigDecimal.valueOf(1500))
                .clienteId(1)
                .tipoProduto("CDB")
                .rentabilidadeAplicada(BigDecimal.valueOf(0.12)).build()
                ;

        Mockito.when(simulacaoService.retornaSimulacaoPorClientId(1))
                .thenReturn(List.of(simulacao));

        // Act + Assert
        given()
                .when()
                .get("/investimento/simulacoes?clienteId=1")
                .then()
                .statusCode(200);
    }

    @Test
    void deveRetornar422QuandoNaoExistiremSimulacoes() {

        // Arrange
        Mockito.when(simulacaoService.retornaSimulacaoPorClientId(1))
                .thenReturn(List.of());

        // Act + Assert
        given()
                .when()
                .get("/investimento/simulacoes?clienteId=1")
                .then()
                .statusCode(422);
    }
}