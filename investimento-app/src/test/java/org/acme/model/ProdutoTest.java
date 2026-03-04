package org.acme.model;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
class ProdutoTest {

    private MockedStatic<Produto> mocked;

    @BeforeEach
    void setup() {
        mocked = mockStatic(Produto.class);
    }

    @AfterEach
    void tearDown() {
        mocked.close();
    }

    @Test
    void deveRetornarListaDeProdutos() {

        Produto produto = new Produto();
        produto.setNome("CDB");
        produto.setTipoProduto("CDB");
        produto.setRisco("medio");
        produto.setId(2L);
        produto.setValorMax(BigDecimal.valueOf(100000));
        produto.setValorMin(BigDecimal.valueOf(10));
        produto.setPrazoMinMeses(1);
        produto.setPrazoMaxMeses(10);
        produto.setRentabilidadeAnual(BigDecimal.valueOf(0.1));

        mocked.when(Produto::findProdutosDisponiveis)
                .thenReturn(List.of(produto));

        List<Produto> resultado = Produto.findProdutosDisponiveis();

        assertEquals(1, resultado.size());
        assertEquals("CDB", resultado.get(0).getNome());
        assertEquals("CDB", resultado.get(0).getTipoProduto());
        assertEquals("medio", resultado.get(0).getRisco());
        assertEquals(BigDecimal.valueOf(100000), resultado.get(0).getValorMax());
        assertEquals(BigDecimal.valueOf(10), resultado.get(0).getValorMin());
        assertEquals(1, resultado.get(0).getPrazoMinMeses());
        assertEquals(10, resultado.get(0).getPrazoMaxMeses());
    }

}