package org.acme.util;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class CalculadoraTest {

    @Test
    void deveCalcularRendimentoCorretamente() {
        // Arrange
        BigDecimal valor = new BigDecimal("1000.00");
        BigDecimal rentabilidade = new BigDecimal("0.12"); // 12% ao ano
        Integer prazo = 12; // 12 meses

        // Act
        BigDecimal resultado = Calculadora.calculaRendimento(valor, rentabilidade, prazo);

        // Assert
        BigDecimal esperado = new BigDecimal("1126.83");
        assertEquals(esperado, resultado);
    }

    @Test
    void deveRetornarMesmoValorSePrazoZero() {
        // Arrange
        BigDecimal valor = new BigDecimal("1000.00");
        BigDecimal rentabilidade = new BigDecimal("0.10");
        Integer prazo = 0;

        // Act
        BigDecimal resultado = Calculadora.calculaRendimento(valor, rentabilidade, prazo);

        // Assert
        assertEquals(new BigDecimal("1000.00"), resultado);
    }
}