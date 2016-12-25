package com.heavendevelopment.mantvida2017.DataBaseAccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.heavendevelopment.mantvida2017.Dominio.Devocional;
import com.heavendevelopment.mantvida2017.Dominio.Evento;
import com.heavendevelopment.mantvida2017.Dominio.Meta;
import com.heavendevelopment.mantvida2017.Dominio.Usuario;
import com.heavendevelopment.mantvida2017.Dominio.Versículo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alysson on 22/11/2015.
 */

public class DatabaseAccess {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */

    //passar como parâmetro, a versão que o usuário escolheu
    //e passar como parâmetro para o construtor do DatabaseOpenHelper
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    private DatabaseAccess(Context context, int versaoBiblia) {
        this.openHelper = new DatabaseOpenHelper(context, escolherBDBiblia(versaoBiblia));
    }

    //1 - NVI; 2 - NTLH; 3 - ACF; 4 - KJV

    private String escolherBDBiblia(int versaoBiblia){

        String BD_NAME = "";

        switch (versaoBiblia){

            case 1: BD_NAME = "NVI";
                break;
            case 2: BD_NAME = "NTLH";
                break;
            case 3: BD_NAME = "ACF";
                break;
            case 4: BD_NAME = "KJV";
                break;
        }

        return BD_NAME;
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public static DatabaseAccess getInstance(Context context, int versaoBiblia) {
        if (instance == null) {
            instance = new DatabaseAccess(context, versaoBiblia);
        }
        return instance;
    }


    public void open() {

        this.database = openHelper.getWritableDatabase();
    }

    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public boolean cadastrarMeta(Meta meta){

        ContentValues cValues = new ContentValues();

        cValues.put("titulo",meta.getTitulo());
        cValues.put("como", meta.getComo());
        cValues.put("objetivo", meta.getObjetivo());
        cValues.put("dataCriacao", meta.getDataCriacao());
        cValues.put("dataInicio", meta.getDataInicio());
        cValues.put("dataConclusao", meta.getDataConclusao());
        cValues.put("realizada", meta.getRealizada());
        cValues.put("idCategoria", meta.getIdCategoria());

        try {

            database.insert("meta", null, cValues);

        } catch (Exception ex) {

            return false;
        }

        return true;
    }

    public boolean cadastrarDevocional(Devocional devocional){

        ContentValues cValues = new ContentValues();

        cValues.put("titulo",devocional.getTitulo());
        cValues.put("dataCriacao", devocional.getDataCriacao());
        cValues.put("textoChave", devocional.getTextoChave());
        cValues.put("mensagemDeus", devocional.getMensagemDeDeus());


        try {

            database.insert("devocional", null, cValues);

        } catch (Exception ex) {

            return false;
        }

        return true;
    }

    public List<Meta> getMetas(){


        ArrayList<Meta> listaMetas = new ArrayList<>();


        Cursor cursor = database.rawQuery("SELECT * FROM meta", null);

        while(cursor.moveToNext()){

            Meta meta = new Meta();

            meta.setId(cursor.getInt(0));
            meta.setTitulo(cursor.getString(1));
            meta.setComo(cursor.getString(2));
            meta.setObjetivo(cursor.getString(3));
            meta.setDataCriacao(cursor.getString(4));
            meta.setDataInicio(cursor.getString(5));
            meta.setDataConclusao(cursor.getString(6));
            meta.setRealizada(cursor.getInt(7));
            meta.setIdCategoria(cursor.getInt(8));

            listaMetas.add(meta);
        }

        cursor.close();
        return listaMetas;

    }

    public List<Devocional> getDevocionais(){

        ArrayList<Devocional> listaDevocionais = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM devocional", null);

        while(cursor.moveToNext()){

            Devocional devocional = new Devocional();

            devocional.setId(cursor.getInt(0));
            devocional.setTitulo(cursor.getString(1));
            devocional.setDataCriacao(cursor.getString(2));
            devocional.setTextoChave(cursor.getString(3));
            devocional.setMensagemDeDeus(cursor.getString(4));

            listaDevocionais.add(devocional);
        }

        cursor.close();
        return listaDevocionais;

    }

    public ArrayList<Evento> getEventos(){

        ArrayList<Evento> listaEventos = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM evento ORDER BY id ASC", null);

        while(cursor.moveToNext()){

            Evento evento = new Evento();

            evento.setId(cursor.getInt(0));
            evento.setNome(cursor.getString(1));
            evento.setData(cursor.getString(2));
            evento.setDescricao(cursor.getString(3));

            listaEventos.add(evento);
        }

        cursor.close();
        return listaEventos;

    }

    public boolean atualizarMeta(Meta meta){

        ContentValues cValues = new ContentValues();

        cValues.put("titulo",meta.getTitulo());
        cValues.put("como", meta.getComo());
        cValues.put("objetivo", meta.getObjetivo());
        cValues.put("dataCriacao", meta.getDataCriacao());
        cValues.put("dataInicio", meta.getDataInicio());
        cValues.put("dataConclusao", meta.getDataConclusao());
        cValues.put("realizada", meta.getRealizada());
        cValues.put("idCategoria", meta.getIdCategoria());

        try {

            database.update("meta",cValues,"id = "+meta.getId(),null);

        } catch (Exception ex) {

            return false;
        }

        return true;
    }

    public boolean atualizarDevocional(Devocional devocional){

        ContentValues cValues = new ContentValues();

        cValues.put("titulo",devocional.getTitulo());
        cValues.put("dataCriacao", devocional.getDataCriacao());
        cValues.put("textoChave", devocional.getTextoChave());
        cValues.put("mensagemDeus", devocional.getMensagemDeDeus());


        try {

            database.update("devocional", cValues, "id = " + devocional.getId(), null);

        } catch (Exception ex) {

            return false;
        }

        return true;
    }

    public boolean deletarMeta(int idMeta){

        try {

            database.delete("meta", "id = " + idMeta, null);

        } catch (Exception ex) {

            return false;
        }

        return true;

    }

    public boolean deletarDevocional(int idDevocional){

        try {

            database.delete("devocional", "id = " + idDevocional, null);

        } catch (Exception ex) {

            return false;
        }

        return true;


    }

    public Meta getMetaById(int idMeta){

        Meta meta = new Meta();

        try {

            Cursor cursor = database.rawQuery("SELECT * FROM meta WHERE id = " + idMeta,null);

            if (cursor.moveToFirst()) {

                meta.setId(cursor.getInt(0));
                meta.setTitulo(cursor.getString(1));
                meta.setComo(cursor.getString(2));
                meta.setObjetivo(cursor.getString(3));
                meta.setDataCriacao(cursor.getString(4));
                meta.setDataInicio(cursor.getString(5));
                meta.setDataConclusao(cursor.getString(6));
                meta.setRealizada(cursor.getInt(7));
                meta.setIdCategoria(cursor.getInt(8));

                cursor.close();

            } else{

                return null;
            }

        }catch(Exception ex){

            return null;
        }

        return meta;

    }

    public Devocional getDevocionalById(int idDevocional){

        Devocional devocional = new Devocional();

        try {

            Cursor cursor = database.rawQuery("SELECT * FROM devocional WHERE id = " + idDevocional,null);

            if (cursor.moveToFirst()) {

                devocional.setId(cursor.getInt(0));
                devocional.setTitulo(cursor.getString(1));
                devocional.setDataCriacao(cursor.getString(2));
                devocional.setTextoChave(cursor.getString(3));
                devocional.setMensagemDeDeus(cursor.getString(4));

                cursor.close();

            } else{

                return null;
            }

        }catch(Exception ex){

            return null;
        }

        return devocional;

    }

    //get the reference of the biblical reading of the day
    public String getRefReadingOfDay(int dia, int mes){

        String reference = null;

        try {

            Cursor cursor = database.rawQuery("SELECT titulo_original_leitura, id_livros, capitulos FROM leitura_biblica", null);

            //reference;chapters;verses

            if(cursor.moveToFirst())
                reference = cursor.getString(0) + ";" + cursor.getString(1) + ";" + cursor.getString(2);

            cursor.close();
        }catch (Exception ex){

            return ex.getMessage();
        }

        return reference;
    }

    //get the reading of the day
    public ArrayList<Versículo> getReadingOfDay(int id_livro, int capitulo){

        ArrayList<Versículo> listaVersiculos = new ArrayList<>();
        Cursor cursor = null;

        try{

            cursor = database.rawQuery("SELECT verse, text FROM biblia_acf WHERE book_id = "+id_livro + " AND chapter = "+ capitulo, null);

            while(cursor.moveToNext()){

                Versículo versículo = new Versículo();
                versículo.setNumVersiculo(Integer.parseInt(cursor.getString(0)));
                versículo.setTexto(cursor.getString(1));

                listaVersiculos.add(versículo);
            }

        }catch(Exception ex){

            return null;
        }

        cursor.close();
        return listaVersiculos;

    }


    public String getUserName(){

        String retornoNome = null;

        try {

            Cursor cursor = database.rawQuery("SELECT nome FROM usuario", null);

            if(cursor.moveToFirst())
                retornoNome = cursor.getString(0);


            cursor.close();
        }catch (Exception ex){

            return "";

        }

        return retornoNome;
    }

    public boolean cadastrarUsuario(Usuario usuario) {

        ContentValues cValues = new ContentValues();

        cValues.put("nome",usuario.getNome());
        cValues.put("login", usuario.getLogin());
        cValues.put("senha", usuario.getSenha());
        cValues.put("email", usuario.getDataNascimento());
        cValues.put("id",1);

        try {

            database.insert("usuario", null, cValues);

        } catch (Exception ex) {

            return false;
        }

        return true;

    }

    public Usuario recuperarInformaoes(String dataNascimento){

        Usuario usuario = new Usuario();

        try {

            Cursor cursor = database.rawQuery("SELECT login,senha FROM usuario WHERE dataNascimento = '" + dataNascimento + "'",null);

            if (cursor.moveToFirst()) {

                usuario.setLogin(cursor.getString(0));
                usuario.setSenha(cursor.getString(1));

                cursor.close();

            } else
                return null;

        }catch(Exception ex){

            return null;
        }

        return usuario;

    }

    public boolean autenticarLogin(String login, String senha){


        try {

            Cursor cursor = database.rawQuery("SELECT login FROM usuario WHERE login = '"+login+"' AND senha = '"+senha+"'",null);

            if (cursor.moveToFirst())
                return true;

            cursor.close();

        }catch(Exception ex){

            return false;
        }

        return true;
    }




}
