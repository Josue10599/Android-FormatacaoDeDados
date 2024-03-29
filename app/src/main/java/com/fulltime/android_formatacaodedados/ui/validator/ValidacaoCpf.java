package com.fulltime.android_formatacaodedados.ui.validator;

import android.support.design.widget.TextInputLayout;

import com.fulltime.android_formatacaodedados.R;

import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

public class ValidacaoCpf extends ValidacaoPadrao implements Validacao {

    private final TextInputLayout campoCpf;
    private String cpfDigitado;

    public ValidacaoCpf(TextInputLayout campoCpf) {
        super(campoCpf);
        this.campoCpf = campoCpf;
        try {
            this.cpfDigitado = new CPFFormatter().unformat(getTextoDigitadoNoCampo(campoCpf));
        } catch (Exception e) {
            this.cpfDigitado = getTextoDigitadoNoCampo(campoCpf);
        }
    }

    @Override
    public boolean valida() {
        if (campoVazio()) return false;
        if (quantidadeDigitosCpfInvalido()) return false;
        if (cpfInvalido()) return false;
        removeErro();
        return true;
    }

    private boolean cpfInvalido() {
        CPFValidator cpfValidator = new CPFValidator();
        try {
            cpfValidator.assertValid(cpfDigitado);
        } catch (InvalidStateException e) {
            campoCpf.setError(campoCpf.getContext().getString(R.string.cpf_digitado_invalido));
            return true;
        }
        return false;
    }

    private boolean quantidadeDigitosCpfInvalido() {
        if (cpfDigitado.length() != 11) {
            campoCpf.setError(campoCpf.getContext().getString(R.string.erro_quantidade_digitos_cpf));
            return true;
        }
        return false;
    }

}
