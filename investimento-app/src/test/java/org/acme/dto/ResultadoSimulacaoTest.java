package org.acme.dto;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class ResultadoSimulacaoTest {

    @Test
    void deveTestarSettersEGetters() {

        ResultadoSimulacao resultado =
                new ResultadoSimulacao(BigDecimal.TEN, 12);

        // valida valores do construtor
        assertEquals(BigDecimal.TEN, resultado.getValorFinal());
        assertEquals(12, resultado.getPrazoMeses());

        // testa setters
        resultado.setValorFinal(BigDecimal.valueOf(2000));
        resultado.setPrazoMeses(24);

        assertEquals(BigDecimal.valueOf(2000), resultado.getValorFinal());
        assertEquals(24, resultado.getPrazoMeses());
    }

    @Test
    void deveTestarToString() {

        ResultadoSimulacao resultado =
                new ResultadoSimulacao(BigDecimal.valueOf(1500), 18);

        String toString = resultado.toString();

        assertTrue(toString.contains("1500"));
        assertTrue(toString.contains("18"));
        assertTrue(toString.contains("valorFinal"));
        assertTrue(toString.contains("prazoMeses"));
    }
}