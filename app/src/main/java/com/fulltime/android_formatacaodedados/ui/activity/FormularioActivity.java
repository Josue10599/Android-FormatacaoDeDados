package com.fulltime.android_formatacaodedados.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fulltime.android_formatacaodedados.R;
import com.fulltime.android_formatacaodedados.ui.validator.ValidacaoCpf;
import com.fulltime.android_formatacaodedados.ui.validator.ValidacaoPadrao;

import br.com.caelum.stella.format.CPFFormatter;

public class FormularioActivity extends AppCompatActivity {

    private static final String TAG_ERRO = "Erro_FormularioActivity";
    private TextInputLayout campoNomeCompleto;
    private TextInputLayout campoCpf;
    private TextInputLayout campoTelefone;
    private TextInputLayout campoEmail;
    private TextInputLayout campoSenha;
    private Button botaoCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        configuraCamposDaActivity();
    }

    private void configuraCamposDaActivity() {
        configuraCampoNome();
        configuraCampoCpf();
        configuraCampoTelefone();
        configuraCampoEmail();
        configuraCampoSenha();
        configuraBotaoCadastrar();
    }

    private void configuraBotaoCadastrar() {
        botaoCadastrar = findViewById(R.id.formulario_cadastro_botao_cadastrar);
    }

    private void configuraCampoSenha() {
        campoSenha = findViewById(R.id.formulario_cadastro_senha);
        validacaoPadraoCampoDeTexto(campoSenha);
    }

    private void configuraCampoEmail() {
        campoEmail = findViewById(R.id.formulario_cadastro_email);
        validacaoPadraoCampoDeTexto(campoEmail);
    }

    private void configuraCampoTelefone() {
        campoTelefone = findViewById(R.id.formulario_cadastro_telefone);
        validacaoPadraoCampoDeTexto(campoTelefone);
    }

    private void configuraCampoCpf() {
        campoCpf = findViewById(R.id.formulario_cadastro_cpf);
        configuraCpf(campoCpf);
    }

    private void configuraCampoNome() {
        campoNomeCompleto = findViewById(R.id.formulario_cadastro_nome_completo);
        validacaoPadraoCampoDeTexto(campoNomeCompleto);
    }

    private void configuraCpf(final TextInputLayout campoCpf) {
        EditText editTextCpf = campoCpf.getEditText();
        editTextCpf.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (new ValidacaoCpf(campoCpf).valida()) formataCpf(campoCpf);
                } else desformataCpf(campoCpf);
            }
        });
    }

    private void desformataCpf(TextInputLayout campoCpf) {
        String cpfDigitado = campoCpf.getEditText().getText().toString();
        CPFFormatter cpfFormatter = new CPFFormatter();
        try {
            String cpfFormatado = cpfFormatter.unformat(cpfDigitado);
            campoCpf.getEditText().setText(cpfFormatado);
        } catch (IllegalArgumentException e) {
            Log.e(TAG_ERRO, "Campo CPF: " + e.getLocalizedMessage());
        }
    }

    private void formataCpf(TextInputLayout campoCpf) {
        String cpfDigitado = campoCpf.getEditText().getText().toString();
        CPFFormatter cpfFormatter = new CPFFormatter();
        String cpfFormatado = cpfFormatter.format(cpfDigitado);
        campoCpf.getEditText().setText(cpfFormatado);
    }

    private void validacaoPadraoCampoDeTexto(final TextInputLayout campoDeTexto) {
        EditText editTextDoCampoText = campoDeTexto.getEditText();
        if (editTextDoCampoText != null)
            editTextDoCampoText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) new ValidacaoPadrao(campoDeTexto).valida();
                }
            });
    }
}
