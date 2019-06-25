package com.fulltime.android_formatacaodedados;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
        populaCampoDaActivity();

        validaCampoDeTexto(campoNomeCompleto);
        validaCampoDeTexto(campoCpf);
        validaCampoDeTexto(campoTelefone);
        validaCampoDeTexto(campoEmail);
        validaCampoDeTexto(campoSenha);
    }

    private void populaCampoDaActivity() {
        campoNomeCompleto = findViewById(R.id.formulario_cadastro_nome_completo);
        campoCpf = findViewById(R.id.formulario_cadastro_cpf);
        campoTelefone = findViewById(R.id.formulario_cadastro_telefone);
        campoEmail = findViewById(R.id.formulario_cadastro_email);
        campoSenha = findViewById(R.id.formulario_cadastro_senha);
        botaoCadastrar = findViewById(R.id.formulario_cadastro_botao_cadastrar);
    }

    private void validaCampoDeTexto(final TextInputLayout campoDeTexto) {
        final EditText editTextDoCampoText = campoDeTexto.getEditText();
        if (editTextDoCampoText != null) {
            editTextDoCampoText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    String textoDoCampo = editTextDoCampoText.getText().toString();
                    if (!hasFocus) {
                        if (textoDoCampo.isEmpty()) campoDeTexto.setError(getString(R.string.campo_obrigratorio));
                        else campoDeTexto.setErrorEnabled(false);
                    }
                }
            });
        }
    }
}
