package com.heavendevelopment.mantvida2017.Dominio;

import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Yuri on 22/11/2016.
 */

public class Usuario {

    private String nome;
    private String login;
    private String senha;
    private String dataNascimento;

    public Usuario(){
        super();

    }

    public Usuario(String nome, String login, String senha, String dataNascimento) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }



}
