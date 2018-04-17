package com.heavendevelopment.mantvida20182.DataBaseAccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.heavendevelopment.mantvida20182.Dominio.CabecalhoAjuda;
import com.heavendevelopment.mantvida20182.Dominio.Devocional;
import com.heavendevelopment.mantvida20182.Dominio.EstadoLeitura;
import com.heavendevelopment.mantvida20182.Dominio.Evento;
import com.heavendevelopment.mantvida20182.Dominio.ItemCabecalhoAjuda;
import com.heavendevelopment.mantvida20182.Dominio.Leitura;
import com.heavendevelopment.mantvida20182.Dominio.Meta;
import com.heavendevelopment.mantvida20182.Dominio.Versículo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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

                int idLivro = cursor.getInt(0);

                String leituraDia = cursor.getString(2);

                //eu uso um método a parte para tratar os casos que tem vírgula.
                if(leituraDia.contains(",")){
                    return getReadingOfDayManyBooks(dia,mes);
                }else{ //caso não seja uma das leituras que contenham vírgulas, segue o fluxo normalmente

                    String [] leiturasDoDia = leituraDia.split(Pattern.quote("‐"));
                    String abreviacao;
                    String iniLeitura;
                    try{

                        int aux = Integer.parseInt(leiturasDoDia[0].substring(0,1));
                        abreviacao = leituraDia.substring(0,3);
                        iniLeitura = leiturasDoDia[0].substring(4, leiturasDoDia[0].length()).trim();
                    }catch (Exception ex){
                        abreviacao = leituraDia.substring(0,2);
                        iniLeitura = leiturasDoDia[0].substring(2, leiturasDoDia[0].length()).trim();
                    }


                    String fimLeitura= leiturasDoDia[1];

                    int inicioLeitura = Integer.parseInt(iniLeitura);
                    int finalLeitura = Integer.parseInt(fimLeitura);

                    for(int i = inicioLeitura; i <= finalLeitura; i++){

                        Leitura leitura = new Leitura();
                        leitura.setDia(dia);
                        leitura.setMes(mes);
                        leitura.setIdLivro(idLivro);
                        leitura.setCapitulo(i);
                        leitura.setTitulo(nomeLivro(abreviacao) + " "+ i);

                        listLeituras.add(leitura);
                    }
                }

            }

        }catch (Exception ex){
            return null;
        }

        return listLeituras;
    }

    public ArrayList<Leitura> getReadingOfDayManyBooks(int dia, int mes){

        ArrayList<Leitura> listLeituras = new ArrayList<>();
        String tabela = escolheTabelaTrimestre(mes);

        try {

            Cursor cursor = database.rawQuery("SELECT book_id, capitulo, titulo FROM " + tabela + " WHERE mes = " + mes + " AND dia = " + dia, null);

            while(cursor.moveToNext()){


                int idLivro = cursor.getInt(0);


                String leituraDia = cursor.getString(2);
                String [] leiturasDoDia = leituraDia.split(Pattern.quote(","));
                String ultimaAbreviacao = "";
                String iniLeitura = "";

                for(int i = 0; i < leiturasDoDia.length; i++){

                    //separo por vírgula, percorro cada item do vertor, se ele tiver "-" separo de novo, e além disso verifico se o item do vetor tem
                    //Abreviação, se não tiver, pego a última abreviação guardada em uma variável auxiliar

                    try{
                        //se converter, é porque a posição "i" atual não tem abreviação, então eu pego a abreviação antiga
                        int x = Integer.parseInt(leiturasDoDia[i]);

                    }catch (Exception ex){

                        //se não, é porque tem abreviação, então eu seto a abreviação
                        ultimaAbreviacao = leituraDia.substring(0,3).trim();
                    }

                    iniLeitura = leiturasDoDia[i].substring(leiturasDoDia[i].length() - 2, leiturasDoDia[i].length()).trim();

                    int capituloLeitura = Integer.parseInt(iniLeitura);

                    Leitura leitura = new Leitura();
                    leitura.setDia(dia);
                    leitura.setMes(mes);
                    leitura.setIdLivro(idLivro);
                    leitura.setCapitulo(capituloLeitura);
                    leitura.setTitulo(nomeLivro(ultimaAbreviacao) + " "+ capituloLeitura);

                    listLeituras.add(leitura);

                }
            }

        }catch (Exception ex){
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

    private String nomeLivro(String abreviacao){

        switch (abreviacao){

            case "Gn" :	return "Gênesis";
            case "Ex" :	return "Êxodo";
            case "Lv" :	return "Levítico";
            case "Nm" :	return "Números";
            case "Dt" :	return "Deuteronômio";
            case "Js" :	return "Josué";
            case "Jz" :	return "Juízes";
            case "Rt" :	return "Rute";
            case "1Sm" : return "1 Samuel";
            case "2Sm" : return "2 Samuel";
            case "1Rs" : return "1 Reis";
            case "2Rs" : return "2 Reis";
            case "1Cr" : return  "1 Crônicas";
            case "2Cr" : return "2 Crônicas";
            case "Ed" :	 return "Esdras";
            case "Ne" :	return "Neemias";
            case "Et" :	return "Ester";
            case "Jó" : return 	"Jó";
            case "Sl" :	return "Salmos";
            case "Pv" :	return "Provérbios";
            case "Ec" :	return "Eclesiastes";
            case "Ct" :	return "Cânticos dos Cânticos";
            case "Is" :	return "Isaías";
            case "Jr" :	return "Jeremias";
            case "Lm" :	return "Lamentações de Jeremias";
            case "Ez" :	return "Ezequiel";
            case "Dn" : return "Daniel";
            case "Os" :	return "Oséias";
            case "Jl" :	return "Joel";
            case "Am" :	return "Amós";
            case "Ob" :	return "Obadias";
            case "Jn" :	return "Jonas";
            case "Mq" :	return "Miquéias";
            case "Na" :	return "Naum";
            case "Hc" :	return "Habacuque";
            case "Sf" :	return "Sofonias";
            case "Ag" : return "Ageu";
            case "Zc" :	return "Zacarias";
            case "Ml" :	return "Malaquias";
            case "Mt" : return "Mateus";
            case "Mc" : return "Marcos";
            case "Lc" : return "Lucas";
            case "Jo" : return "João";
            case "At" : return "Atos dos Apóstolos";
            case "Rm" : return "Romanos";
            case "1Co" : return "1ª Coríntios";
            case "2Co" : return "2ª Coríntios";
            case "Gl" : return "Gálatas";
            case "Ef" : return "Efésios";
            case "Fp" : return "Filipenses";
            case "Cl" : return "Colossenses";
            case "1Ts" : return "1ª Tessalonicenses";
            case "2Ts" : return "2ª Tessalonicenses";
            case "1Tn" : return "1ª Timóteo";
            case "2Tm" : return "2ª Timóteo";
            case "Tt" : return "Tito";
            case "Fm" : return "Filemom";
            case "Hb" : return "Hebreus";
            case "Tg" : return "Tiago";
            case "1Pe" : return "1ª Pedro";
            case "2Pe" : return "2ª Pedro";
            case "1Jo" : return "1ª João";
            case "2Jo" : return "2ª João";
            case "3Jo" : return "3ª João";
            case "Jd" : return "Judas";
            case "Ap" : return "Apocalipse";
            default : return "";
        }

    }

}

