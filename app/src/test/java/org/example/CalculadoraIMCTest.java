package org.example;

import org.assertj.core.data.Offset;
import org.example.model.Pessoa;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

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
        var expected = ClassificacaoIMC.ACIMA_PESO;

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
        var expected = ClassificacaoIMC.MUITO_ABAIXO_PESO;

        // When
        CalculadoraIMC calculadoraIMC = new CalculadoraIMC(pessoa);

        var result = calculadoraIMC.classificar();
        // Then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/imc.csv", numLinesToSkip = 1)
    void givenValidIMCResult_shouldReturnCorrectIMCWith2DecimalCases(double peso, double altura, double resultado, String classificacao) {
        CalculadoraIMC calculadoraIMC = new CalculadoraIMC(new Pessoa("test", peso, altura));
        var resultadoCalculo = calculadoraIMC.calcular();

        assertThat(resultadoCalculo).isCloseTo(resultado, Offset.offset(0.0000001));
        var classificacaoResultado = calculadoraIMC.classificar();

        var classificacaoEsperada = ClassificacaoIMC.valueOf(classificacao);

        assertThat(classificacaoResultado).isEqualTo(classificacaoEsperada);
    }

}