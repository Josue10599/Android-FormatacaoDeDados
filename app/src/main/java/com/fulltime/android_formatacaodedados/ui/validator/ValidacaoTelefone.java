package com.fulltime.android_formatacaodedados.ui.validator;

import android.support.design.widget.TextInputLayout;

import com.fulltime.android_formatacaodedados.R;
import com.fulltime.android_formatacaodedados.ui.formatter.FormataTelefone;

public class ValidacaoTelefone extends ValidacaoPadrao{

    private final TextInputLayout campoTelefone;
    private final String telefoneDigitado;

    public ValidacaoTelefone(TextInputLayout campoTelefone) {
        super(campoTelefone);
        this.campoTelefone = campoTelefone;
        this.telefoneDigitado = new FormataTelefone(campoTelefone).desformataTelefone();
    }

    public boolean valida() {
        if (campoVazio()) return false;
        if (quantidadeDigitosInvalida()) return false;
        removeErro();
        return true;
    }

    private boolean quantidadeDigitosInvalida() {
        if (telefoneDigitado.length() == 10 || telefoneDigitado.length() == 11) return false;
        campoTelefone.setError(campoTelefone.getContext().getText(R.string.erro_quantidade_digitos_telefone));
        return true;
    }
}
