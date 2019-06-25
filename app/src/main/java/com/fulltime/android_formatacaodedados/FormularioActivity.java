package com.fulltime.android_formatacaodedados;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

public class FormularioActivity extends AppCompatActivity {

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
        validaQuantidadeDigitosCpf();
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

    private void validaQuantidadeDigitosCpf() {
        campoCpf = findViewById(R.id.formulario_cadastro_cpf);
        configuraCpf(campoCpf);
    }

    private void configuraCpf(final TextInputLayout campoCpf) {
        EditText editTextCpf = campoCpf.getEditText();
        editTextCpf.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (validaCampoObrigatorio(campoCpf)) return;
                    if (validaQuantidadeDigitosCpf(campoCpf)) return;
                    if (validaCpf(campoCpf)) return;
                    removeErro(campoCpf);
                }
            }
        });
    }

    private boolean validaCpf(TextInputLayout campoCpf) {
        CPFValidator cpfValidator = new CPFValidator();
        String cpf = campoCpf.getEditText().getText().toString();
        try {
            cpfValidator.assertValid(cpf);
        } catch (InvalidStateException e) {
            campoCpf.setError(getString(R.string.cpf_digitado_invalido));
            return true;
        }
        return false;
    }

    private boolean validaQuantidadeDigitosCpf(TextInputLayout campoCpf) {
        String cpfDigitado = campoCpf.getEditText().getText().toString();
        if (cpfDigitado.length() != 11) {
            campoCpf.setError(getString(R.string.erro_quantidade_digitos_cpf));
            return true;
        }
        return false;
    }

    private void configuraCampoNome() {
        campoNomeCompleto = findViewById(R.id.formulario_cadastro_nome_completo);
        validacaoPadraoCampoDeTexto(campoNomeCompleto);
    }

    private void validacaoPadraoCampoDeTexto(final TextInputLayout campoDeTexto) {
        EditText editTextDoCampoText = campoDeTexto.getEditText();
        if (editTextDoCampoText != null) {
            editTextDoCampoText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        if (validaCampoObrigatorio(campoDeTexto)) return;
                        removeErro(campoDeTexto);
                    }
                }
            });
        }
    }

    private boolean validaCampoObrigatorio(TextInputLayout campoDeTexto) {
        String textoDoCampo = campoDeTexto.getEditText().getText().toString();
        if (textoDoCampo.isEmpty()) {
            campoDeTexto.setError(getString(R.string.campo_obrigratorio));
            return true;
        }
        return false;
    }

    private void removeErro(TextInputLayout campoDeTexto) {
        campoDeTexto.setError(null);
        campoDeTexto.setErrorEnabled(false);
    }
}
