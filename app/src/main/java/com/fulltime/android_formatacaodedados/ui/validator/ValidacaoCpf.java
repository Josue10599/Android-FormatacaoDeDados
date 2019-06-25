package com.fulltime.android_formatacaodedados.ui.validator;

import android.support.design.widget.TextInputLayout;

import com.fulltime.android_formatacaodedados.R;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

public class ValidacaoCpf extends ValidacaoPadrao {

    private final TextInputLayout campoCpf;
    private final String cpfDigitado;

    public ValidacaoCpf(TextInputLayout campoCpf) {
        super(campoCpf);
        this.campoCpf = campoCpf;
        cpfDigitado = getTextoDigitadoNoCampo(campoCpf);
    }

    public boolean valida() {
        if (validaCampoObrigatorio()) return false;
        if (validaQuantidadeDigitosCpf()) return false;
        if (validaCpf()) return false;
        removeErro();
        return true;
    }

    private boolean validaCpf() {
        CPFValidator cpfValidator = new CPFValidator();
        try {
            cpfValidator.assertValid(cpfDigitado);
        } catch (InvalidStateException e) {
            campoCpf.setError(campoCpf.getContext().getString(R.string.cpf_digitado_invalido));
            return true;
        }
        return false;
    }

    private boolean validaQuantidadeDigitosCpf() {
        if (cpfDigitado.length() != 11) {
            campoCpf.setError(campoCpf.getContext().getString(R.string.erro_quantidade_digitos_cpf));
            return true;
        }
        return false;
    }

}
