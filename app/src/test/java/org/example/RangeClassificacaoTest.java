package org.example;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RangeClassificacaoTest {

    @Test
    void givenDoubleValue_shouldCheckIfIsInRange() {
        RangeClassificacao rangeClassificacao = RangeClassificacao.of(7d, 19d, ClassificacaoIMC.SEM_CLASSIFICACAO);
        assertThat(rangeClassificacao.isInRange(8d)).isTrue();
        assertThat(rangeClassificacao.isInRange(7.1d)).isTrue();
        assertThat(rangeClassificacao.isInRange(7.0001d)).isTrue();
        assertThat(rangeClassificacao.isInRange(18.99d)).isTrue();
        assertThat(rangeClassificacao.isInRange(18.99999d)).isTrue();
    }

    @Test
    void givenDoubleValue_shouldCheckIfIsNotInRange() {
        RangeClassificacao rangeClassificacao = RangeClassificacao.of(7d, 19d, ClassificacaoIMC.SEM_CLASSIFICACAO);
        assertThat(rangeClassificacao.isInRange(1d)).isFalse();
        assertThat(rangeClassificacao.isInRange(6.9999d)).isFalse();
        assertThat(rangeClassificacao.isInRange(20d)).isFalse();
        assertThat(rangeClassificacao.isInRange(20.000001d)).isFalse();
        assertThat(rangeClassificacao.isInRange(21.000001d)).isFalse();
    }
}