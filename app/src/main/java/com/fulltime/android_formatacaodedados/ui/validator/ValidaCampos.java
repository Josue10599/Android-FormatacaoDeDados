package com.fulltime.android_formatacaodedados.ui.validator;

import java.util.Arrays;
import java.util.List;

public class ValidaCampos implements Validacao{
    private final List<Validacao> validadores;

    public ValidaCampos(Validacao... validadores) {
        this.validadores = Arrays.asList(validadores);
    }

    @Override
    public boolean valida() {
        boolean valido = true;
        for (Validacao validacao: validadores) {
            if (!validacao.valida()) valido = false;
        }
        return valido;
    }
}
