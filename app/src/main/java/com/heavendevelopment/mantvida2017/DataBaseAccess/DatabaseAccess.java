package com.heavendevelopment.mantvida2017.DataBaseAccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.heavendevelopment.mantvida2017.Dominio.AlimentoCelular;
import com.heavendevelopment.mantvida2017.Dominio.Devocional;
import com.heavendevelopment.mantvida2017.Dominio.Evento;
import com.heavendevelopment.mantvida2017.Dominio.Leitura;
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
    private static int bibleVersion;

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

    //1 - NVI; 2 - NTLH; 3 - ACF; 4 - KJV
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

    public static DatabaseAccess getInstance(Context context, int bibleVersionChoosed) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }

        bibleVersion = bibleVersionChoosed;
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

    public boolean cadastrarAlimentoCelular(AlimentoCelular alimentoCelular){

        ContentValues cValues = new ContentValues();

        cValues.put("titulo",alimentoCelular.getNome());
        cValues.put("data", alimentoCelular.getData());
        cValues.put("link", alimentoCelular.getLink());

        try {

            database.insert("alimento_celular", null, cValues);

        } catch (Exception ex) {

            return false;
        }

        return true;
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

    public List<AlimentoCelular> getAlimentosCelulares(){

        ArrayList<AlimentoCelular> listaAlimentoCelulares = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM alimento_celular", null);

        while(cursor.moveToNext()){

            AlimentoCelular alimentoCelular = new AlimentoCelular();

            alimentoCelular.setNome(cursor.getString(1));
            alimentoCelular.setData(cursor.getString(2));
            alimentoCelular.setLink(cursor.getString(3));

            listaAlimentoCelulares.add(alimentoCelular);
        }

        cursor.close();
        return listaAlimentoCelulares;

    }

    public AlimentoCelular getAlimentoCelularByNumero(int numero){

        AlimentoCelular alimentoCelular = new AlimentoCelular();

        try {

            Cursor cursor = database.rawQuery("SELECT link FROM alimento_celular WHERE numero = "+numero , null);

            if(cursor.moveToFirst())
                alimentoCelular.setLink(cursor.getString(0));


            cursor.close();
        }catch (Exception ex){

            return null;

        }

        return alimentoCelular;

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

    public ArrayList<Meta> getMetasByCategory(int categoria){

        //1 - Família, 2 - Ministério, 3 - Formação, 4 - Restituição, 5 - Finanças

        ArrayList<Meta> listaMetas = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT titulo, dataInicio, dataConclusao FROM meta WHERE idCategoria = " + categoria, null);

        while(cursor.moveToNext()){

            Meta meta = new Meta();

            meta.setTitulo(cursor.getString(0));
            meta.setDataInicio(cursor.getString(1));
            meta.setDataConclusao(cursor.getString(2));

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

    public Evento getEventoDoDia(int dia, int mes){

        Evento eventoHoje = null;

        ArrayList<Evento> listaEventos = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM evento WHERE mes = " + mes, null);

        //pega os eventos do mês atual.

        int qtdEventos = listaEventos.size();
        int diaEventoInicio = 0, diaEventoFim = 0;
        int eventoUmDia = 0;
        String [] eventoComDias;


        //enquanto ele for andando nos eventos que vinheram do banco, eu vou vendo se cada um deles é o evento de hoje ou não.
        while(cursor.moveToNext()){

            Evento evento = new Evento();

            evento.setId(cursor.getInt(0));
            evento.setNome(cursor.getString(1));
            evento.setData(cursor.getString(2));
            evento.setDescricao(cursor.getString(3));
            evento.setDia(cursor.getString(4));
            evento.setMes(cursor.getInt(5));

            //divido o 'dia" por "a" para ver se é um evento de um dia ou não.
            eventoComDias = evento.getDia().split("a");

            //evento de um dia
            if(eventoComDias.length == 1) {

                eventoUmDia = Integer.parseInt(evento.getDia());

                if(eventoUmDia == dia)
                    listaEventos.add(evento);

            }//evento tem mais de um dia
            else{

                diaEventoInicio = Integer.parseInt(eventoComDias[0]);
                diaEventoFim = Integer.parseInt(eventoComDias[1]);

                if(dia >= diaEventoInicio && dia <= diaEventoFim)
                    listaEventos.add(evento);
            }

        }

        //nesse ponto, a lista de evento está com o(s) evento(s) do dia.

        //se há mais de um evento no dia, eu crio um novo evento com a junção dos dois eventos.
        if(listaEventos.size() == 2) {

            Evento eventoUniao = new Evento();
            eventoUniao.setNome(listaEventos.get(1).getNome() + "\n" + listaEventos.get(0).getNome());
            eventoUniao.setData(listaEventos.get(0).getData());
            eventoUniao.setDescricao(listaEventos.get(1).getDescricao() + "\n\n" + listaEventos.get(0).getDescricao());

            eventoHoje = eventoUniao;
        }//se não há evento eu crio um apenas para mostrar as informações corretas
        else if(listaEventos.size() == 0){
            Evento evento = new Evento();
            evento.setNome("Nenhum evento");
            evento.setData("");
            evento.setDescricao("Não existem eventos acontecendo hoje");

            eventoHoje = evento;
        }// senão, é porque há apenas um evento no dia
        else{
            eventoHoje = listaEventos.get(0);
        }

        return eventoHoje;
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
    public String getRefReadingOfDay(int day, int month){

        String referencia = "";

        try {

            Cursor cursor = database.rawQuery("SELECT referencia FROM leitura_referencias WHERE mes = " + month + " AND dia = " + day, null);

            if(cursor.moveToFirst())
                referencia = cursor.getString(0);

        }catch (Exception ex){

            String x = "";
            return null;
        }

        return referencia;

    }

    //method description here
    public ArrayList<Leitura> getReadingOfDay(int dia, int mes){

        ArrayList<Leitura> listLeituras = new ArrayList<>();
        String tabela = escolheTabelaTrimestre(mes);

        try {

            Cursor cursor = database.rawQuery("SELECT book_id, capitulo, titulo FROM " + tabela + " WHERE mes = " + mes + " AND dia = " + dia, null);

            while(cursor.moveToNext()){

                Leitura leitura = new Leitura();
                leitura.setDia(dia);
                leitura.setMes(mes);
                leitura.setIdLivro(cursor.getInt(0));
                leitura.setCapitulo(cursor.getInt(1));
                leitura.setTitulo(cursor.getString(2));

                listLeituras.add(leitura);
            }

        }catch (Exception ex){
            String x = "";
            return null;
        }

        return listLeituras;
    }

    //get the reading of the day
    public ArrayList<Versículo> getLeitura(int id_livro, int capitulo){

        ArrayList<Versículo> listaVersiculos = new ArrayList<>();
        Cursor cursor = null;

        String bibleTable = chooseBibleTable();

        try{

            cursor = database.rawQuery("SELECT verse, text FROM " + bibleTable + " WHERE book_id = "+id_livro + " AND chapter = "+ capitulo, null);

            while(cursor.moveToNext()){

                Versículo versículo = new Versículo();
                versículo.setVerse(cursor.getInt(0));
                versículo.setText(cursor.getString(1));

                listaVersiculos.add(versículo);
            }

        }catch(Exception ex){

            return null;
        }

        cursor.close();
        return listaVersiculos;

    }

    private String chooseBibleTable(){

        String bibleTable = "";

        if(bibleVersion == 1)
            bibleTable = "biblia_nvi";
        else if(bibleVersion == 2)
            bibleTable = "biblia_ntlh";
        else if(bibleVersion == 3)
            bibleTable = "biblia_acf";
        else
            bibleTable = "biblia_kjv";

        return bibleTable;
    }

    private String escolheTabelaTrimestre(int mes){

        String tabela = "";

        if(mes < 4)
            tabela = "leitura_1_tri";
        else if(mes < 7)
            tabela = "leitura_2_tri";
        else if(mes < 10)
            tabela = "leitura_3_tri";
        else
            tabela = "leitura_4_tri";

        return tabela;
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
