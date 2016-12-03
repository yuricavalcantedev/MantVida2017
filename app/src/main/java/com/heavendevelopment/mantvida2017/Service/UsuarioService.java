package com.heavendevelopment.mantvida2017.Service;

import android.content.Context;
import android.provider.ContactsContract;

import com.heavendevelopment.mantvida2017.DataBaseAccess.DatabaseAccess;
import com.heavendevelopment.mantvida2017.Dominio.Meta;
import com.heavendevelopment.mantvida2017.Dominio.Usuario;

import java.util.List;

/**
 * Created by Yuri on 02/12/2016.
 */

public class UsuarioService {

    private Context context;

    public UsuarioService(Context context){
        super();
        this.context = context;
    }

    public boolean cadastrarUsuario(Usuario usuario){

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        boolean cadastrado = databaseAccess.cadastrarUsuario(usuario);

        databaseAccess.close();

        return cadastrado;
    }

    public boolean atualizarUsuario(Usuario usuario){

        return true;
    }

    public Usuario recuperarInformacoes(String dataNascimento){

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        Usuario usuario = databaseAccess.recuperarInformaoes(dataNascimento);

        databaseAccess.close();

        return usuario;

    }

    public boolean autenticarLogin(String login, String senha){

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        boolean loginCorreto = databaseAccess.autenticarLogin(login,senha);

        databaseAccess.close();

        return loginCorreto;
    }

    public String getNomeUsuario(){

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        String nome = databaseAccess.getUserName();
        databaseAccess.close();

        return nome;
    }

}
