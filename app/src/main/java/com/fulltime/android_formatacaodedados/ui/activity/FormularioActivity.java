package com.fulltime.android_formatacaodedados.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.fulltime.android_formatacaodedados.R;
import com.fulltime.android_formatacaodedados.ui.formatter.FormataCpf;
import com.fulltime.android_formatacaodedados.ui.formatter.FormataTelefone;
import com.fulltime.android_formatacaodedados.ui.validator.ValidacaoCpf;
import com.fulltime.android_formatacaodedados.ui.validator.ValidacaoEmail;
import com.fulltime.android_formatacaodedados.ui.validator.ValidacaoPadrao;
import com.fulltime.android_formatacaodedados.ui.validator.ValidacaoTelefone;

public class FormularioActivity extends AppCompatActivity {
    private TextInputLayout campoNomeCompleto;
    private TextInputLayout campoCpf;
    private TextInputLayout campoTelefone;
    private TextInputLayout campoEmail;
    private TextInputLayout campoSenha;

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
        Button botaoCadastrar = findViewById(R.id.formulario_cadastro_botao_cadastrar);
        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validaCampos()) {
                    mensagemCadastroEfetuado();
                    campoNomeCompleto.requestFocus();
                    escondeTeclado();
                }
            }
        });
    }

    private void limpaCampos() {
        campoNomeCompleto.getEditText().getText().clear();
        campoCpf.getEditText().getText().clear();
        campoTelefone.getEditText().getText().clear();
        campoEmail.getEditText().getText().clear();
        campoSenha.getEditText().getText().clear();
    }

    private void mensagemCadastroEfetuado() {
        new AlertDialog.Builder(FormularioActivity.this)
                .setTitle(R.string.titulo_cadastro)
                .setMessage(R.string.cadastro_concluido)
                .setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        limpaCampos();
                    }
                })
                .show();
    }

    private void escondeTeclado() {
        ((InputMethodManager)getSystemService(INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(campoNomeCompleto.getWindowToken(),0);
    }

    private boolean validaCampos() {
        return validaCampoPreenchido(campoNomeCompleto) && validaCampoPreenchido(campoSenha) &&
                validaCpf() && validaTelefone() && validaEmail();
    }

    private void configuraCampoSenha() {
        campoSenha = findViewById(R.id.formulario_cadastro_senha);
        validacaoCampoVazio(campoSenha);
    }

    private void configuraCampoEmail() {
        campoEmail = findViewById(R.id.formulario_cadastro_email);
        validacaoEmail(campoEmail);
    }

    private void validacaoEmail(final TextInputLayout campoEmail) {
        EditText editTextEmail = campoEmail.getEditText();
        editTextEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validaEmail();
                }
            }
        });
    }

    private boolean validaEmail() {
        return new ValidacaoEmail(campoEmail).valida();
    }

    private void configuraCampoTelefone() {
        campoTelefone = findViewById(R.id.formulario_cadastro_telefone);
        validacaoTelefone(campoTelefone);
    }

    private void validacaoTelefone(final TextInputLayout campoTelefone) {
        EditText editTextTelefone = campoTelefone.getEditText();
        final FormataTelefone formataTelefone = new FormataTelefone(campoTelefone);
        editTextTelefone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (validaTelefone())
                        campoTelefone.getEditText().setText(formataTelefone.formataTelefone());
                } else
                    campoTelefone.getEditText().setText(formataTelefone.desformataTelefone());
            }
        });
    }

    private boolean validaTelefone() {
        return new ValidacaoTelefone(campoTelefone).valida();
    }

    private void configuraCampoCpf() {
        campoCpf = findViewById(R.id.formulario_cadastro_cpf);
        validacaoCpf(campoCpf);
    }

    private void configuraCampoNome() {
        campoNomeCompleto = findViewById(R.id.formulario_cadastro_nome_completo);
        validacaoCampoVazio(campoNomeCompleto);
    }

    private void validacaoCpf(final TextInputLayout campoCpf) {
        final FormataCpf formataCpf = new FormataCpf(campoCpf);
        EditText editTextCpf = campoCpf.getEditText();
        editTextCpf.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (validaCpf())
                        campoCpf.getEditText().setText(formataCpf.formataCpf(campoCpf));
                } else
                    campoCpf.getEditText().setText(formataCpf.desformataCpf(campoCpf));
            }
        });
    }

    private boolean validaCpf() {
        return new ValidacaoCpf(campoCpf).valida();
    }

    private void validacaoCampoVazio(final TextInputLayout campoDeTexto) {
        EditText editTextDoCampoText = campoDeTexto.getEditText();
        if (editTextDoCampoText != null)
            editTextDoCampoText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) validaCampoPreenchido(campoDeTexto);
                }
            });
    }

    private boolean validaCampoPreenchido(TextInputLayout campoDeTexto) {
        return new ValidacaoPadrao(campoDeTexto).valida();
    }
}
