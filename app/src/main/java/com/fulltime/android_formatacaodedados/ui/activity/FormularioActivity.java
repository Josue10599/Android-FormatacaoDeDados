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
import com.fulltime.android_formatacaodedados.ui.validator.ValidacaoEmail;
import com.fulltime.android_formatacaodedados.ui.validator.ValidacaoPadrao;
import com.fulltime.android_formatacaodedados.ui.validator.ValidacaoTelefone;

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
        validacaoEmail(campoEmail);
    }

    private void validacaoEmail(final TextInputLayout campoEmail) {
        EditText editTextEmail = campoEmail.getEditText();
        editTextEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    new ValidacaoEmail(campoEmail).valida();
                }
            }
        });
    }

    private void configuraCampoTelefone() {
        campoTelefone = findViewById(R.id.formulario_cadastro_telefone);
        validacaoTelefone(campoTelefone);
    }

    private void validacaoTelefone(final TextInputLayout campoTelefone) {
        EditText editTextTelefone = campoTelefone.getEditText();
        editTextTelefone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (new ValidacaoTelefone(campoTelefone).valida()) formataTelefone(campoTelefone);
                } else desformataTelefone(campoTelefone);
            }
        });
    }

    private void desformataTelefone(TextInputLayout campoTelefone) {
        String telefoneDigitadoComFormatacao = getTextoDigitado(campoTelefone);
        String telefoneDigitadoSemFormatacao = telefoneDigitadoComFormatacao
                .replaceAll("\\(([0-9]{2})\\) ([0-9]{4,5})\\-([0-9]{4})","$1$2$3");
        campoTelefone.getEditText().setText(telefoneDigitadoSemFormatacao);
    }

    private void formataTelefone(TextInputLayout campoTelefone) {
        String telefoneDigitado = getTextoDigitado(campoTelefone);
        String telefoneDigitadoFormatado = telefoneDigitado
                .replaceAll("([0-9]{2})([0-9]{4,5})([0-9]{4})","($1) $2-$3");
        campoTelefone.getEditText().setText(telefoneDigitadoFormatado);
    }

    private String getTextoDigitado(TextInputLayout campoDeTexto) {
        return campoDeTexto.getEditText().getText().toString();
    }

    private void configuraCampoCpf() {
        campoCpf = findViewById(R.id.formulario_cadastro_cpf);
        validacaoCpf(campoCpf);
    }

    private void configuraCampoNome() {
        campoNomeCompleto = findViewById(R.id.formulario_cadastro_nome_completo);
        validacaoPadraoCampoDeTexto(campoNomeCompleto);
    }

    private void validacaoCpf(final TextInputLayout campoCpf) {
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
        String cpfDigitado = getTextoDigitado(campoCpf);
        CPFFormatter cpfFormatter = new CPFFormatter();
        try {
            String cpfFormatado = cpfFormatter.unformat(cpfDigitado);
            campoCpf.getEditText().setText(cpfFormatado);
        } catch (IllegalArgumentException e) {
            Log.e(TAG_ERRO, "Campo CPF: " + e.getLocalizedMessage());
        }
    }

    private void formataCpf(TextInputLayout campoCpf) {
        String cpfDigitado = getTextoDigitado(campoCpf);
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
