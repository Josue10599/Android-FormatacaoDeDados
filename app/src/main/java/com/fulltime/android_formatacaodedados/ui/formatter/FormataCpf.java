package com.fulltime.android_formatacaodedados.ui.formatter;

import android.support.design.widget.TextInputLayout;
import android.util.Log;

import com.fulltime.android_formatacaodedados.ui.activity.FormularioActivity;

import br.com.caelum.stella.format.CPFFormatter;

public class FormataCpf {
    private final TextInputLayout campoCpf;
    private static final String TAG_ERRO = "Erro_FormularioActivity";

    public FormataCpf(TextInputLayout campoCpf) {
        this.campoCpf = campoCpf;
    }

    public String desformataCpf(TextInputLayout campoCpf) {
        String cpfDigitado = getCpfDigitado();
        String cpfFormatado = cpfDigitado;
        CPFFormatter cpfFormatter = new CPFFormatter();
        try {
             cpfFormatado = cpfFormatter.unformat(cpfDigitado);
        } catch (IllegalArgumentException e) {
            Log.e(TAG_ERRO, "Campo CPF: " + e.getLocalizedMessage());
        }
        return cpfFormatado;
    }

    private String getCpfDigitado() {
        return campoCpf.getEditText().getText().toString();
    }

    public String formataCpf(TextInputLayout campoCpf) {
        String cpfDigitado = getCpfDigitado();
        CPFFormatter cpfFormatter = new CPFFormatter();
        String cpfFormatado = cpfFormatter.format(cpfDigitado);
        return cpfFormatado;
    }
}