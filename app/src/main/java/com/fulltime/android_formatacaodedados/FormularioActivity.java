package com.fulltime.android_formatacaodedados;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class FormularioActivity extends AppCompatActivity {

    TextInputLayout campoNomeCompleto;
    TextInputLayout campoCpf;
    TextInputLayout campoTelefone;
    TextInputLayout campoEmail;
    TextInputLayout campoSenha;
    Button botaoCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        populaCampoDaActivity();
    }

    private void populaCampoDaActivity() {
        campoNomeCompleto = findViewById(R.id.formulario_cadastro_nome_completo);
        campoCpf = findViewById(R.id.formulario_cadastro_cpf);
        campoTelefone = findViewById(R.id.formulario_cadastro_telefone);
        campoEmail = findViewById(R.id.formulario_cadastro_email);
        campoSenha = findViewById(R.id.formulario_cadastro_senha);
        botaoCadastrar = findViewById(R.id.formulario_cadastro_botao_cadastrar);
    }
}
