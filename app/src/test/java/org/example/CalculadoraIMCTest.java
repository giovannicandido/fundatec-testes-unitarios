package org.example;

import org.assertj.core.data.Offset;
import org.example.model.Pessoa;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CalculadoraIMCTest {

    @Test
    @DisplayName("Given normal weight when calcular must return correct imc with 2 decimals")
    void givenAboveWeight_whenCalcular_mustReturnCorrectIMCWith2DecimalCases() {
        // Given
        Pessoa pessoa = new Pessoa("Joao", 82.00d, 1.75d);
        var expected = 26.77d;

        // When
        CalculadoraIMC calculadoraIMC = new CalculadoraIMC(pessoa);

        var result = calculadoraIMC.calcular();
        // Then

        assertThat(result).isCloseTo(expected, Offset.offset(0.01));
    }

    @Test
    void givenAboveWeight_whenClassificar_mustReturnPesoNormal() {
        // Given
        Pessoa pessoa = new Pessoa("Joao", 82.00d, 1.75d);
        var expected = "Acima do peso";

        // When
        CalculadoraIMC calculadoraIMC = new CalculadoraIMC(pessoa);

        var result = calculadoraIMC.classificar();
        // Then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void givenLowWeight_whenCalcular_mustReturnLessThen17() {
        // Given
        Pessoa pessoa = new Pessoa("Joao", 40.00d, 1.75d);
        var expected = 17;

        // When
        CalculadoraIMC calculadoraIMC = new CalculadoraIMC(pessoa);

        var result = calculadoraIMC.calcular();
        // Then
        assertThat(result).isLessThan(expected);
    }


    @Test
    void givenLowWeight_whenClassificar_mustReturnMuitoAbaixoPeso() {
        // Given
        Pessoa pessoa = new Pessoa("Joao", 40.00d, 1.75d);
        var expected = "Muito abaixo do peso";

        // When
        CalculadoraIMC calculadoraIMC = new CalculadoraIMC(pessoa);

        var result = calculadoraIMC.classificar();
        // Then
        assertThat(result).isEqualTo(expected);
    }
}