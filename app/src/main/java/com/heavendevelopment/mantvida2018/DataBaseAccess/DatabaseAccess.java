package com.heavendevelopment.mantvida2018.DataBaseAccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.heavendevelopment.mantvida2018.Dominio.CabecalhoAjuda;
import com.heavendevelopment.mantvida2018.Dominio.Devocional;
import com.heavendevelopment.mantvida2018.Dominio.EstadoLeitura;
import com.heavendevelopment.mantvida2018.Dominio.Evento;
import com.heavendevelopment.mantvida2018.Dominio.ItemCabecalhoAjuda;
import com.heavendevelopment.mantvida2018.Dominio.Leitura;
import com.heavendevelopment.mantvida2018.Dominio.Meta;
import com.heavendevelopment.mantvida2018.Dominio.Versículo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuri on 22/11/2015.
 */

public class DatabaseAccess {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;
    private static int bibleVersion;

    //passar como parâmetro, a versão que o usuário escolheu
    //e passar como parâmetro para o construtor do DatabaseOpenHelper
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    //1 - NVI; 2 - NTLH; 3 - ACF; 4 - KJV

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


    //METAS

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

    public boolean deletarMeta(int idMeta){

        try {

            database.delete("meta", "id = " + idMeta, null);

        } catch (Exception ex) {

            return false;
        }

        return true;

    }


    //DEVOCIONAIS

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

    public boolean deletarDevocional(int idDevocional){

        try {

            database.delete("devocional", "id = " + idDevocional, null);

        } catch (Exception ex) {

            return false;
        }

        return true;


    }


    //EVENTOS

    public Evento getEventoDoDia(int dia, int mes){

        Evento eventoHoje = null;

        ArrayList<Evento> listaEventos = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM evento WHERE mes = " + mes, null);


        //pega os eventos do mês atual.

        int qtdEventos = listaEventos.size();
        int diaEventoInicio = 0, diaEventoFim = 0;
        int eventoUmDia = 0;
        String [] eventoComDias;

        try{

            //enquanto ele for andando nos eventos que vinheram do banco, eu vou vendo se cada um deles é o evento de hoje ou não.
            while(cursor.moveToNext()){

                Evento evento = new Evento();

                evento.setId(cursor.getInt(0));
                evento.setNome(cursor.getString(1));
                evento.setDia(cursor.getString(2));
                evento.setMes(cursor.getInt(3));
                evento.setData(cursor.getString(4));
                evento.setDescricao(cursor.getString(5));


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

        }catch(Exception ex){

            String x = "";
        }



        return eventoHoje;
    }

    public ArrayList<Evento> getEventos(){

        ArrayList<Evento> listaEventos = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM evento ORDER BY mes", null);

        while(cursor.moveToNext()){

            Evento evento = new Evento();

            evento.setId(cursor.getInt(0));
            evento.setNome(cursor.getString(1));
            evento.setDia(cursor.getString(2));
            evento.setMes(cursor.getInt(3));
            evento.setData(cursor.getString(4));
            evento.setDescricao(cursor.getString(5));

            listaEventos.add(evento);
        }

        cursor.close();
        return listaEventos;

    }

    public boolean atualizarEvento(Evento evento){

        ContentValues cValues = new ContentValues();

        cValues.put("id", evento.getId());
        cValues.put("nome", evento.getNome());
        cValues.put("mes", evento.getMes());
        cValues.put("data", evento.getData());
        cValues.put("descricao", evento.getDescricao());

        try {

            database.update("evento", cValues, "id = " + evento.getId(), null);

        } catch (Exception ex) {

            return false;
        }

        return true;


    }


    //LEITURA (ACOMPANHAMENTO)

    public EstadoLeitura getLeituraUmDia(int mes, int dia){

        EstadoLeitura estadoLeitura = null;

        try {

            Cursor cursor = database.rawQuery("SELECT estado FROM leitura_decorator WHERE mes = " + mes + " AND dia = " + dia, null);

            if(cursor.moveToFirst())
                estadoLeitura = new EstadoLeitura(0,0,cursor.getInt(0));


            cursor.close();
        }catch (Exception ex){

            return null;

        }

        return estadoLeitura;

    }

    public List<EstadoLeitura> getLeiturasAteHoje(int mes, int dia){

        ArrayList<EstadoLeitura> listEstadoLeituras = new ArrayList<>();

        try {

            Cursor cursor = database.rawQuery("SELECT * FROM leitura_decorator WHERE mes <= " + mes, null);

            while(cursor.moveToNext()){

                EstadoLeitura estadoLeitura = new EstadoLeitura();
                estadoLeitura.setMes(cursor.getInt(1));
                estadoLeitura.setDia(cursor.getInt(2));
                estadoLeitura.setEstado(cursor.getInt(3));

                listEstadoLeituras.add(estadoLeitura);

            }


        }catch (Exception ex){
            return null;
        }

        return listEstadoLeituras;

    }

    public boolean setEstadoLeitura(int mes,int dia, int estado){

        ContentValues cValues = new ContentValues();

        cValues.put("mes", mes);
        cValues.put("dia", dia);
        cValues.put("estado", estado);

        try {

            database.update("leitura_decorator", cValues, "mes = " + mes + " AND dia = " + dia,null);

        } catch (Exception ex) {

            return false;
        }

        return true;

    }


    //AJUDA

    public List<CabecalhoAjuda> getCabecalhosAjuda(){

        List<CabecalhoAjuda> listCabecalhos = new ArrayList<>();
        Cursor cursor;

        try{

            cursor = database.rawQuery("SELECT * FROM lista_cabecalhos_ajuda", null);

            while(cursor.moveToNext()){

                CabecalhoAjuda cabecalhoAjuda = new CabecalhoAjuda();
                cabecalhoAjuda.setId(cursor.getInt(0));
                cabecalhoAjuda.setTitulo(cursor.getString(1));

                listCabecalhos.add(cabecalhoAjuda);
            }

        }catch(Exception ex){

            return null;
        }

        cursor.close();
        return listCabecalhos;

    }

    public List<ItemCabecalhoAjuda> getItensPorCabecalhoAjuda(int idCabecalho){

        List<ItemCabecalhoAjuda> listaItens = new ArrayList<>();
        Cursor cursor;

        try {

            cursor = database.rawQuery("SELECT * FROM lista_itens_cabecalho_ajuda WHERE id_cabecalho = " + idCabecalho, null);

            while(cursor.moveToNext()){

                ItemCabecalhoAjuda itemCabecalho = new ItemCabecalhoAjuda();
                itemCabecalho.setId(cursor.getInt(0));
                itemCabecalho.setIdCabecalho(cursor.getInt(1));
                itemCabecalho.setTitulo(cursor.getString(2));

                listaItens.add(itemCabecalho);

            }


        }catch (Exception ex){
            return null;
        }

        return listaItens;

    }


    //ACESSO A BÍBLIA

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


    //MÉTODOS AUXILIARES DO ACESSO A BÍBLIA

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



}

