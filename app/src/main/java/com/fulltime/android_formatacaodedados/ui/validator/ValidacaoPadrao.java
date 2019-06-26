package com.fulltime.android_formatacaodedados.ui.validator;

import android.support.design.widget.TextInputLayout;

import com.fulltime.android_formatacaodedados.R;

public class ValidacaoPadrao implements Validacao {

    private final TextInputLayout campoDeTexto;
    private final String textoDigitadoNoCampo;

    public ValidacaoPadrao(TextInputLayout campoDeTexto) {
        this.campoDeTexto = campoDeTexto;
        textoDigitadoNoCampo = getTextoDigitadoNoCampo(campoDeTexto);
    }

    @Override
    public boolean valida() {
        if (campoVazio()) return false;
        removeErro();
        return true;
    }

    protected boolean campoVazio() {
        if (textoDigitadoNoCampo.isEmpty()) {
            campoDeTexto.setError(campoDeTexto.getContext().getString(R.string.campo_obrigratorio));
            return true;
        }
        return false;
    }

    protected void removeErro() {
        campoDeTexto.setError(null);
        campoDeTexto.setErrorEnabled(false);
    }

    protected String getTextoDigitadoNoCampo(TextInputLayout campoDeTexto) {
        return campoDeTexto.getEditText().getText().toString();
    }

}
