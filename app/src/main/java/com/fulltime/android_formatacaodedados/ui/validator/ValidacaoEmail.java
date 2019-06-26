package com.fulltime.android_formatacaodedados.ui.validator;

import android.support.design.widget.TextInputLayout;

import com.fulltime.android_formatacaodedados.R;

public class ValidacaoEmail extends ValidacaoPadrao implements Validacao{
    private final TextInputLayout campoEmail;
    private final String emailDigitado;

    public ValidacaoEmail(TextInputLayout campoEmail) {
        super(campoEmail);
        this.campoEmail = campoEmail;
        this.emailDigitado = getTextoDigitadoNoCampo(campoEmail);
    }

    @Override
    public boolean valida() {
        if (campoVazio()) return false;
        if (emailDigitadoInvalido()) return false;
        removeErro();
        return true;
    }

    private boolean emailDigitadoInvalido() {
        if (emailDigitado.matches(".+@.+\\..+\\w")) return false;
        campoEmail.setError(campoEmail.getContext().getText(R.string.erro_email_invalido));
        return true;
    }
}
