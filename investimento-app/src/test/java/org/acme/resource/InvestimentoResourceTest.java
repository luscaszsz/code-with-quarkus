package org.acme.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.InjectMock;
import io.quarkus.test.security.TestSecurity;
import org.acme.dto.Analise;
import org.acme.dto.ResultadoSimulacao;
import org.acme.dto.request.SimulacaoRequest;
import org.acme.dto.response.ErroResponse;
import org.acme.dto.response.SimulacaoResponse;
import org.acme.model.Produto;
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

        SimulacaoRequest request = new SimulacaoRequest(1, new BigDecimal("1000"), 12, "CDB");

        Analise analise = new Analise(null, new ResultadoSimulacao(BigDecimal.valueOf(1500), 12));
        List<Analise> analiseList = new ArrayList<>();
        analiseList.add(analise);

        SimulacaoResponse responseMock = new SimulacaoResponse(analiseList, Instant.now().toString());

        Mockito.when(produtoService.getProdutoCompativel(Mockito.any(SimulacaoRequest.class)))
                .thenReturn(responseMock);

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

        SimulacaoRequest request = new SimulacaoRequest(1, new BigDecimal("1000"), 12, "CDB");

        Mockito.when(produtoService.getProdutoCompativel(Mockito.any(SimulacaoRequest.class)))
                .thenReturn(null);

        given()
                .contentType("application/json")
                .body(request)
                .when()
                .post("/investimento/simulacoes")
                .then()
                .statusCode(422);
    }

    @Test
    void deveRetornar400QuandoRequestInvalido() {

        SimulacaoRequest request = new SimulacaoRequest(1, new BigDecimal("1000"), -12, "CDB");

        Mockito.when(produtoService.getProdutoCompativel(Mockito.any(SimulacaoRequest.class)))
                .thenReturn(null);

        ErroResponse erroResponse = new ErroResponse(null,null,null);
        erroResponse.setCodigo(400);
        erroResponse.setMensagem("invalido");
        erroResponse.setDadosComplementares(null);

        given()
                .contentType("application/json")
                .body(request)
                .when()
                .post("/investimento/simulacoes")
                .then()
                .statusCode(400);
    }

    // =========================
    // TESTE GET
    // =========================

    @Test
    @TestSecurity(user = "testUser", roles = {"User","Admin"})
    void deveRetornarSimulacoesQuandoExistirem() {

        Simulacao simulacao = new Simulacao();
        simulacao.setDataSimulacao(Instant.now().toString());
        simulacao.setPrazoMeses(12);
        simulacao.setValorFinal(BigDecimal.valueOf(1500));
        simulacao.setClienteId(1);
        simulacao.setTipoProduto("CDB");
        simulacao.setRentabilidadeAplicada(BigDecimal.valueOf(0.12));

        Mockito.when(simulacaoService.retornaSimulacaoPorClientId(1))
                .thenReturn(List.of(simulacao));

        given()
                .when()
                .get("/investimento/simulacoes?clienteId=1")
                .then()
                .statusCode(200);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"User","Admin"})
    void deveRetornar422QuandoNaoExistiremSimulacoes() {

        Mockito.when(simulacaoService.retornaSimulacaoPorClientId(1))
                .thenReturn(List.of());

        given()
                .when()
                .get("/investimento/simulacoes?clienteId=1")
                .then()
                .statusCode(422);
    }

    @Test
    void deveGerarRuntimeException() {

        Mockito.when(produtoService.getProdutos())
                .thenReturn(null);

        given()
                .when()
                .get("/investimento/produtos")
                .then()
                .statusCode(500);
    }

    @Test
    void deveRetornarOkParaBuscaProdutos() {

        Mockito.when(produtoService.getProdutos())
                .thenReturn(List.of(new Produto()));

        given()
                .when()
                .get("/investimento/produtos")
                .then()
                .statusCode(200);
    }
}