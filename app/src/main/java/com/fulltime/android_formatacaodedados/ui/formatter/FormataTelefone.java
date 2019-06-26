package com.fulltime.android_formatacaodedados.ui.formatter;

import android.support.design.widget.TextInputLayout;

public class FormataTelefone {
    private final TextInputLayout campoTelefone;

    public FormataTelefone(TextInputLayout campoTelefone) {
        this.campoTelefone = campoTelefone;
    }

    public String desformataTelefone() {
        String telefoneDigitadoComFormatacao = getTelefoneDigitado();
        String telefoneDigitadoSemFormatacao = telefoneDigitadoComFormatacao;
        if (telefoneDigitadoComFormatacao.matches("\\(([0-9]{2})\\) ([0-9]{4,5})\\-([0-9]{4})")) {
            telefoneDigitadoSemFormatacao = telefoneDigitadoComFormatacao
                    .replaceAll("\\(([0-9]{2})\\) ([0-9]{4,5})\\-([0-9]{4})", "$1$2$3");
        }
        return telefoneDigitadoSemFormatacao;
    }

    private String getTelefoneDigitado() {
        return campoTelefone.getEditText().getText().toString();
    }

    public String formataTelefone() {
        String telefoneDigitado = getTelefoneDigitado();
        String telefoneDigitadoFormatado = telefoneDigitado;
        if (telefoneDigitado.matches("([0-9]{2})([0-9]{4,5})([0-9]{4})")) {
            telefoneDigitadoFormatado = telefoneDigitado
                    .replaceAll("([0-9]{2})([0-9]{4,5})([0-9]{4})", "($1) $2-$3");
        }
        return telefoneDigitadoFormatado;
    }
}