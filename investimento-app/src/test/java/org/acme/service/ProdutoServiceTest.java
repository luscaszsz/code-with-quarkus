package org.acme.service;

import io.quarkus.test.junit.QuarkusTest;
import org.acme.dto.Analise;
import org.acme.dto.request.SimulacaoRequest;
import org.acme.dto.response.SimulacaoResponse;
import org.acme.model.Produto;
import org.acme.model.Simulacao;
import org.acme.util.Calculadora;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
class ProdutoServiceTest {

    private ProdutoService produtoService;
    private MockedStatic<Produto> produtoMock;
    private MockedStatic<Calculadora> calculadoraMock;
    private MockedStatic<Simulacao> simulacaoMock;

    @BeforeEach
    void setup() {
        produtoService = new ProdutoService();

        produtoMock = mockStatic(Produto.class);
        calculadoraMock = mockStatic(Calculadora.class);
        simulacaoMock = mockStatic(Simulacao.class);
    }

    @AfterEach
    void tearDown() {
        produtoMock.close();
        calculadoraMock.close();
        simulacaoMock.close();
    }

    @Test
    void deveRetornarProdutosDisponiveis() {

        produtoMock.when(Produto::findProdutosDisponiveis)
                .thenReturn(List.of(new Produto()));

        List<Produto> resultado = produtoService.getProdutos();

        assertEquals(1, resultado.size());
        produtoMock.verify(Produto::findProdutosDisponiveis);
    }

    @Test
    void deveRetornarNullQuandoNaoExistirProdutoCompativel() {

        produtoMock.when(() ->
                Produto.findByTipoAndValorAndPrazo(anyString(), any(), anyInt())
        ).thenReturn(List.of());

        SimulacaoRequest request = new SimulacaoRequest(1,BigDecimal.valueOf(1000), 12, "CDB");

        SimulacaoResponse response = produtoService.getProdutoCompativel(request);

        assertNull(response);
    }

    @Test
    void deveRetornarSimulacaoEGravarNoBanco() {

        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("LCI");
        produto.setTipoProduto("CDB");
        produto.setRentabilidadeAnual(BigDecimal.valueOf(0.12));

        produtoMock.when(() ->
                Produto.findByTipoAndValorAndPrazo(anyString(), any(), anyInt())
        ).thenReturn(List.of(produto));

        SimulacaoRequest request = new SimulacaoRequest(1,BigDecimal.valueOf(1000), 12, "CDB");

        simulacaoMock.when(() -> Simulacao.insert(any()))
                .thenAnswer(invocation -> null);
        SimulacaoResponse response = produtoService.getProdutoCompativel(request);

        assertNotNull(response);
        assertEquals(1, response.getSimulacoes().size());

        Analise analise = response.getSimulacoes().get(0);

        assertEquals("LCI", analise.getProdutoValidado().getNome());

    }
}