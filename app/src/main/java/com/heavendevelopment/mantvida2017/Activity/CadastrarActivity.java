package com.heavendevelopment.mantvida2017.Activity;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.heavendevelopment.mantvida2017.Dominio.Usuario;
import com.heavendevelopment.mantvida2017.R;
import com.heavendevelopment.mantvida2017.Service.UsuarioService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CadastrarActivity extends AppCompatActivity {

    @BindView(R.id.til_nome_cadastrar) TextInputLayout tilNome;
    @BindView(R.id.til_login_cadastrar)  TextInputLayout tilLogin;
    @BindView(R.id.til_senha_cadastrar) TextInputLayout tilSenha;
    @BindView(R.id.til_repetir_senha_cadastrar) TextInputLayout tilRepetirSenha;
    @BindView(R.id.til_dataNascimento_cadastrar) TextInputLayout tilDataNascimento;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        context = this;

        ButterKnife.bind(this);


    }

    @OnClick(R.id.bt_cadastrar)
    public void cadastrarUsuario(){

        boolean signIn = verifyData();

        if(signIn) {
            signInUser();
            Toast.makeText(context, "Cadastro realizado com sucesso.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private boolean verifyData(){

        boolean allCorrect = true;

        String nome = tilNome.getEditText().getText().toString();
        String login = tilLogin.getEditText().getText().toString();
        String senha = tilSenha.getEditText().getText().toString();
        String repetirSenha = tilRepetirSenha.getEditText().getText().toString();
        String dataNascimento = tilDataNascimento.getEditText().getText().toString();

        if(nome.length() < 4){

            tilNome.setError("Nome muito curto");
            allCorrect = false;
        }else{

            tilNome.setErrorEnabled(false);
        }

        if(login.length() < 4){

            tilNome.setError("Login muito curto");
            allCorrect = false;

        }else{

            tilLogin.setErrorEnabled(false);
        }

        if(senha.length() < 4){

            tilSenha.setError("Senha muito curta");
            allCorrect = false;
        }else{

            tilSenha.setErrorEnabled(false);

        }

        if(!repetirSenha.equals(senha)){
            tilRepetirSenha.setError("Senhas não são iguais.");
            allCorrect = false;
        }else{

            tilRepetirSenha.setErrorEnabled(false);
        }

        Pattern regexDataNascimento = Pattern.compile("\\d{2}/\\d{2}/\\d{2,4}");
        Matcher matcherDataNascimento = regexDataNascimento.matcher(dataNascimento);

        if(!matcherDataNascimento.matches()){
            tilDataNascimento.setError("Formato da data inválido. Ex: 14/07/1996");

        }else{

            tilDataNascimento.setErrorEnabled(false);
        }


        return (allCorrect);

    }

    private void signInUser(){

        String nome = tilNome.getEditText().getText().toString();
        String login = tilLogin.getEditText().getText().toString();
        String senha = tilSenha.getEditText().getText().toString();
        String dataNascimento = tilDataNascimento.getEditText().getText().toString();


        String nomeSeparado [] = nome.split(" ");

        nome = nomeSeparado[0];

        UsuarioService usuarioService = new UsuarioService(context);
        Usuario usuario = new Usuario(nome,login,senha,dataNascimento);

        usuarioService.cadastrarUsuario(usuario);

    }

}
