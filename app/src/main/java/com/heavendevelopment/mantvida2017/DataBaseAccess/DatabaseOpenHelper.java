package com.heavendevelopment.mantvida2017.DataBaseAccess;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.view.accessibility.AccessibilityManager;
import android.widget.Toast;

import com.heavendevelopment.mantvida2017.Dominio.Evento;
import com.heavendevelopment.mantvida2017.Service.EventoService;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.List;


/**
 * Created by Alysson on 17/11/2015.
 */


public class DatabaseOpenHelper extends SQLiteAssetHelper {

    //a nova versão que vai pra ser publicada é a 8

    private static final String BD_NAME = "db_mantvida_2017";
    private static final int BD_VERSION = 11 ;
    private Context context;

    public DatabaseOpenHelper(Context ctx) {
        super(ctx, BD_NAME, null, BD_VERSION);
        context = ctx;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //NÃO APAGAR AS VERSÕES ANTERIORES DO BANCO!!!

        try{

            switch(newVersion) {

                case 5 : updateVersao5AlimentoCelulares(db);
                    break;
                case 6 : updateVersao6Eventos(db);
                case 7 : update7PlanoLeituraAndMaps(db);
                case 11 : update8Ajuda(db);
            }

        }catch (Exception ex){

            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * @param db db of application
     **/

    private void update8Ajuda(SQLiteDatabase db){

        try {

            String dropCabecalhoAjuda = "DROP TABLE IF EXISTS lista_cabecalhos_ajuda";
            String dropItensCabecalhoAjuda = "";

            String createTableCabecalhoAjuda = "CREATE TABLE lista_cabecalhos_ajuda (\n" +
                    "    id     INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    titulo STRING\n" +
                    ");";

            String createTableItensCabecalhoAjuda = "CREATE TABLE lista_itens_cabecalho_ajuda (\n" +
                    "    id           INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    id_cabecalho INTEGER REFERENCES lista_cabecalhos_ajuda (id),\n" +
                    "    titulo       STRING\n" +
                    ");";

            db.execSQL(dropCabecalhoAjuda);
            db.execSQL(dropItensCabecalhoAjuda);
            db.execSQL(createTableCabecalhoAjuda);
            db.execSQL(createTableItensCabecalhoAjuda);

            int[] idCabecalhos = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

            String[] tituloCabecalho = new String[]{"Como faço para ler a leitura de hoje?",
                    "Como deixar os dias que li marcados?", "Como criar uma meta no meu projeto de vida?",
                    "Como visualizar uma meta do meu projeto de vida?", "Como editar uma meta do meu projeto de vida?",
                    "Como excluir uma meta do meu projeto de vida?", "Como criar um devocional?",
                    "Como compartilhar um devocional que eu criei?", "Como editar um devocional?", "Como excluir um devocional?",
                    "Como traçar um trajeto entre onde eu estou e uma igreja no mapa?", "Como deixar minha leitura em modo noturno?" };

            int[] idItenCabecalhos = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33,
                    34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63};

            int[] idForenKeyCabecalhos = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 3, 3, 3, 3, 3, 4, 4, 4, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
                    6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 9, 10, 11, 11, 11, 11, 11, 12, 12, 12};

            String[] tituloItensCabecalho = new String[]{"Existem 2 maneiras:", "    ", "1.1 Vá para a tela principal do aplicativo.",
                    "1.2 Selecione o botão em formato de círculo com um livro dentro, no canto inferior direito da tela.", "       ",
                    "2.1 Vá para o menu principal.", "2.2 Selecione 'Plano de Leitura'.", "2.3 Selecione no dia de hoje (ele contêm um pequeno círculo azul).",
                    "2.4 Selecione 'LER'.", "Não é necessário, o aplicativo já faz isso por você automaticamente.", "1. Vá para o menu principal.",
                    "2. Selecione 'Projeto de Vida'.", "3. Selecione o botão no canto inferior direito da tela.", "4. Informe todas as informações da sua meta.",
                    "5. Selecione no botão superior direito.", "1. Vá para o menu principal.", "2. Selecione 'Projeto de Vida'.", "3. Selecione na meta desejada.",
                    "1. Vá para o menu principal.", "2. Selecione 'Projeto de Vida'.", "3. Selecione na meta desejada.", "4. Altere sua meta com as novas informações.",
                    "5. Selecione no primeiro botão superior direito.", "Existem 2 maneiras:", "    ", "1.1 Vá para o menu principal.", "1.2 Selecione 'Projeto de Vida'.",
                    "1.3 Selecione na meta desejada.", "1.4 Selecione no segundo botão superior direito.", "1.5 Selecione 'EXCLUIR'.", "     ",
                    "2.1 Vá para o menu principal.", "2.2 Selecione 'Projeto de Vida'.", "2.3 Pressione e segure uma das metas listadas.",
                    "2.4 Selecione 'EXCLUIR'", "Existem 2 maneiras:", "    ", "1.1 Vá para o menu principal.", "1.2 Selecione 'Plano de Leitura'.",
                    "1.3 Selecione no dia de hoje (ele contêm um pequeno círculo azul).", "1.4 Selecione 'LER'.", "1.5 Selecione o botão superior direito.",
                    "1.6 Informe todas as informações necessárias.", "1.7 Selecione o primeiro botão superior direito.", "    ", "2.1 Vá para o menu principal.",
                    "2.2 Selecione 'Devocionais'.", "2.3 Selecione o botão no canto inferior direito da tela.", "2.4 Informe todas as informações necessárias.",
                    "2.5 Selecione o primeiro botão superior direito.", "1. Selecione um devocional que você já criou ou está criando nesse momento.",
                    "2. Selecione o botão que cuja figura é semelhante a 3 pontos interligados.", "3. Selecione a rede social ou app que deseja compartilhar o devocional.",
                    "De forma semelhante a como se edita uma meta do projeto de vida.", "De forma semelhante a como se edita uma meta do projeto de vida.",
                    "1. Vá para o menu principal.", "2. Selecione 'Localização'.", "3. Selecione a igreja que você deseja ir.",
                    "4. Selecione o botão cuja figura é uma placa azul (ele vai aparecer depois de você selecionar a igreja, no canto inferior direito).",
                    "5. Selecione a opção que habilita a sua localização.", "1. Vá para o menu principal.", "2. Selecione 'Configurações'.", "3. Habilite a opção 'Modo Noturno'." };


            for (int i = 0; i < idCabecalhos.length; i++) {

                ContentValues content_aux1 = new ContentValues();

                content_aux1.put("id", idCabecalhos[i]);
                content_aux1.put("titulo", tituloCabecalho[i]);

                db.insert("lista_cabecalhos_ajuda", null, content_aux1);
            }

            for (int i = 0; i < idItenCabecalhos.length; i++) {

                ContentValues content_aux1 = new ContentValues();

                content_aux1.put("id", idItenCabecalhos[i]);
                content_aux1.put("id_cabecalho", idForenKeyCabecalhos[i]);
                content_aux1.put("titulo", tituloItensCabecalho[i]);

                db.insert("lista_itens_cabecalho_ajuda", null, content_aux1);
            }

        }catch (Exception ex){

            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void update7PlanoLeituraAndMaps(SQLiteDatabase db){

        try{

            String dropLeituraDecorator = "DROP TABLE IF EXISTS leitura_decorator";
            String dropMarcadorMaps = "DROP TABLE IF EXISTS marcadorMaps";

            String createTableLeituraDecorator = "CREATE TABLE leitura_decorator (\n" +
                    "    id     INTEGER,\n" +
                    "    mes    INTEGER,\n" +
                    "    dia    INTEGER,\n" +
                    "    estado INTEGER\n" +
                    ");\n";

            String createTableMarcadorMaps = "CREATE TABLE marcadorMaps (\n" +
                    "    id        INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    nome      STRING  NOT NULL,\n" +
                    "    latitude  DOUBLE  NOT NULL,\n" +
                    "    longitude DOUBLE  NOT NULL\n" +
                    ");";

            db.execSQL(dropLeituraDecorator);
            db.execSQL(dropMarcadorMaps);
            db.execSQL(createTableLeituraDecorator);
            db.execSQL(createTableMarcadorMaps);

            int [] idMarcadoresMaps = new int[]{1,2,3,4,5};

            String [] nomesMarcadoresMaps = new String[]{"Mant-CE - Aldeota",
                    "Mant-CE - Conj.Ceará",
                    "Mant-USA - Peabody MA",
                    "Mant-PA - Marabá",
                    "Mant-TO - Palmas"};

            double [] latitude = new double[]{-3.738108, -3.782177, 42.527103, -5.371304, -10.152779};
            double [] longitude = new double[]{-38.517243, -38.611822, -70.924446, -49.128333, -48.339345};


            for(int i = 0; i < idMarcadoresMaps.length; i++){

                ContentValues content_aux1 = new ContentValues();

                content_aux1.put("id", idMarcadoresMaps[i]);
                content_aux1.put("nome", nomesMarcadoresMaps[i]);
                content_aux1.put("latitude", latitude[i]);
                content_aux1.put("longitude", longitude[i]);

                db.insert("marcadorMaps",null, content_aux1);
            }

            int [] idLeituraDecorator = new int [] { 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,
                    31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,
                    61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,
                    91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,
                    121,122,123,124,125,126,127,128,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,150,
                    151,152,153,154,155,156,157,158,159,160,161,162,163,164,165,166,167,168,169,170,171,172,173,174,175,176,177,178,179,180,
                    181,182,183,184,185,186,187,188,189,190,191,192,193,194,195,196,197,198,199,200,201,202,203,204,205,206,207,208,209,210,
                    211,212,213,214,215,216,217,218,219,220,221,222,223,224,225,226,227,228,229,230,231,232,233,234,235,236,237,238,239,240,
                    241,242,243,244,245,246,247,248,249,250,251,252,253,254,255,256,257,258,259,260,261,262,263,264,265,266,267,268,269,270,
                    271,272,273,274,275,276,277,278,279,280,281,282,283,284,285,286,287,288,289,290,291,292,293,294,295,296,297,298,299,300,
                    301,302,303,304,305,306,307,308,309,310,311,312,313,314,315,316,317,318,319,320,321,322,323,324,325,326,327,328,329,330,
                    331,332,333,334,335,336,337,338,339,340,341,342,343,344,345,346,347,348,349,350,351,352,353,354,355,356,357,358,359,360,
                    361,362,363,364,365 };

            int [] mesLeituraDecorator = new int [] { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                    0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                    1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,
                    2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3,3,3,3,3,3,3,3,3,3,
                    3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,4,4,4,4,
                    4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,
                    4,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5
                    ,5,5,5,5,5,5,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,
                    6,6,6,6,6,6,6,6,6,6,6,6,6,7,7,7,7,7,7,7,7,7,7,7,7
                    ,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,8,8,8,8,8,8
                    ,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,9,
                    9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,
                    9,9,9,9,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,
                    10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,11,11,11,
                    11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,
                    11,11,11,11,11,11,11,11,11,11 };

            int [] diaLeituraDecorator = new int [] { 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,
                    31,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,
                    1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,
                    1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,
                    1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,
                    1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,
                    1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,
                    1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,
                    1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,
                    1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31
                    ,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,2,526,27,28,29,30,
                    1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31 };

            int [] estadoLeituraDecorator = new int [] {   1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                    1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,
                    0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
                    ,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                    0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                    0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                    0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                    0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                    0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                    0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
                    ,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
                    ,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };

            for(int i = 0; i < idLeituraDecorator.length; i++){

                ContentValues content_aux1 = new ContentValues();

                content_aux1.put("id", idLeituraDecorator[i]);
                content_aux1.put("mes", mesLeituraDecorator[i]);
                content_aux1.put("dia", diaLeituraDecorator[i]);
                content_aux1.put("estado", estadoLeituraDecorator[i]);

                db.insert("leitura_decorator",null, content_aux1);
            }

        }catch (Exception ex){

            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    private void updateVersao6Eventos(SQLiteDatabase db){

        //parte dos eventos

        try{

            String dropTableEventos = "DROP TABLE IF EXISTS evento";

            String createTableEventos = "CREATE TABLE evento (\n" +
                    "    id        DOUBLE  UNIQUE,\n" +
                    "    nome      TEXT    NOT NULL,\n" +
                    "    dia       TEXT,\n" +
                    "    mes       INTEGER,\n" +
                    "    data      TEXT    NOT NULL,\n" +
                    "    descricao TEXT\n" +
                    ");\n";

            int [] idEventos = new int[]{1,2,3,4,5,6,7,8,9,10,
                    11,12,13,14,15,16,17,18,19,20,
                    21,22,23,24,25,26,17,28,29,30,31};

            int [] mesEventos = new int[]{1,1,1,1,2,3,3,4,4,4,4,5,6,6,6,7,7,7,
                    8,8,8,8,9,9,9,10,10,11,12,12,12} ;

            String [] diaEventos = new String[]{"01a12","13","21","27a29","26a28",
                    "4","10a12","1","13a16","20a22",
                    "24","22a30","01a03","9a10","23a25",
                    "1","06a08","14a16","1","10a12",
                    "13","21","09a11","09a11","30",
                    "04a13","23a29","03a05","08a10","30",
                    "31"};

            String [] nomeEventos = new String[]{"Campanha do Projeto de Vida","Unção de novos líderes","Conectado com uma vida (Rede Jovem)",
                    "Encontro de Adolescentes","Acamp Jovem","Chá para Mulheres","Encontro com Deus (Homens e Mulheres)",
                    "Conclusão da 1ª etapa do Plano de Leitura","Liberação Profética","Congresso de Mulheres","Início Camapanha de Ana",
                    "Pentecostes (Israel)","Pentecostes(Israel)","Congresso de encerramento Campanha de Ana","Encontro de Adolescentes",
                    "Conclusão da 2ª etapado Plano de Leitura","Festa de Pentecostes","Encontro com Deus (Homens)",
                    "Abertura Campanha de Daniel","Congresso de Sacerdotes","Café da manhã para a Família (Todas as igrejas)",
                    "Encerramento Campanha de Daniel","Encontro com Deus para Mulheres","Encontro de Casais","Encerramento (Células) 3ª Etapa do Plano de Leitura",
                    "Festa Tabernáculos Israel","Festa de Tabernáculos em Marabá","Encontro com Deus para Homens","Encontro de Multiplicadores",
                    "Encerramento (Células) 4ª etapa do Plano de Leitura","Culto da Virada"};

            String [] dataEventos = new String[]{"01 a 12.01.17","13.01.17","21.01.17","27 a 29.01.17","26 a 28.02.17","04.03.17",
                    "10 a 12.03.17","01.04.17","13 a 16.04.17","20 a 22.04.17","24.04.17","22 a 30.05.17","01 a 03.06.17","9 a 10.06.17",
                    "23 a 25.06.17","01.07.17","06 a 08.07.17","14 a 16.07.17","01.08.17","10 a 12.08.17","13.08.17","21.08.17",
                    "09 a 11.09.17","09 a 11.09.17","30.09.17","04 a 13.10.17","23 a 29.10.17","03 a 05.11.17","08 a 10.12.17",
                    "30.12.17","31.12.17"};

            String [] descricaoEventos = new String[]{"Serão 12 cultos, iniciando em 1º. de Janeiro e indo até o dia 12. É o momento de você marcar território de conquista em 2017. O Senhor nos deu a chave da vitória quando nos instruiu a buscar primeiro o seu Reino e a sua Justiça. Então, ao priorizar cultuar nestes primeiros 12 dias de 2017, você está dizendo como será a sua vida durante todo ano. Priorize, quem mais precisa é você.",
                    "Descrição ainda não disponível","Descrição ainda não disponível","Descrição ainda não disponível","Descrição ainda não disponível",
                    "Descrição ainda não disponível","Descrição ainda não disponível","Descrição ainda não disponível","Descrição ainda não disponível",
                    "Descrição ainda não disponível","Descrição ainda não disponível","Descrição ainda não disponível","Descrição ainda não disponível",
                    "Descrição ainda não disponível","Descrição ainda não disponível","Descrição ainda não disponível","Descrição ainda não disponível",
                    "Descrição ainda não disponível","Descrição ainda não disponível","Descrição ainda não disponível","Descrição ainda não disponível",
                    "Descrição ainda não disponível","Descrição ainda não disponível","Descrição ainda não disponível","Descrição ainda não disponível",
                    "Descrição ainda não disponível","Descrição ainda não disponível","Descrição ainda não disponível","Descrição ainda não disponível",
                    "Descrição ainda não disponível","Descrição ainda não disponível"};

            db.execSQL(dropTableEventos);
            db.execSQL(createTableEventos);


            for(int i =0; i < idEventos.length; i++){

                ContentValues content_aux1 = new ContentValues();

                content_aux1.put("id", idEventos[i]);
                content_aux1.put("nome", nomeEventos[i]);
                content_aux1.put("dia",diaEventos[i]);
                content_aux1.put("mes", mesEventos[i]);
                content_aux1.put("data", dataEventos[i]);
                content_aux1.put("descricao", descricaoEventos[i]);

                db.insert("evento",null, content_aux1);
            }

            //parte dos alimentos celulares

            String dropTableAlimentoCelular = "DROP TABLE IF EXISTS alimento_celular";

            String queryCreateTableAlimentoCelular = "CREATE TABLE alimento_celular (\n" +
                    "    id     INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    numero INTEGER,\n" +
                    "    titulo TEXT    NOT NULL,\n" +
                    "    data   TEXT,\n" +
                    "    link   TEXT\n" +
                    ");";

            db.execSQL(dropTableAlimentoCelular);
            db.execSQL(queryCreateTableAlimentoCelular);

            int [] idAlimento = new int[]{1,2,3};
            int [] numeroAlimento = new int[]{1,2,3};

            String [] tituloAlimento = new String[]{"Benção no pronunciamento das sentenças",
                    "Benção na forma certa do tratamento com Deus",
                    "Benção na adversidade"};

            String [] dataAlimento = new String[]{"23/01/17","31/01/17","06/02/17"};
            String [] linksAlimento = new String[]{"http://igcristo.com/down/alimentosCelulares/Alimentos%20Celulares%20de%20Multiplicacao%20-%20001-2017%20-%20Bencao%20no%20pronunciamento%20das%20sentencas.pdf",
                    "http://igcristo.com/down/alimentosCelulares/Alimentos%20Celulares%20de%20Multiplicacao%20-%20002-2017%20-%20Bencao%20na%20forma%20certa%20do%20tratamento%20com%20Deus.pdf",
                    "http://igcristo.com/down/alimentosCelulares/Alimentos%20Celulares%20de%20Multiplicacao%20-%20003-2017%20-%20Bencao%20na%20advsersidade.pdf"
            };

            for(int i =0; i < idAlimento.length; i++){

                ContentValues content_aux1 = new ContentValues();

                content_aux1.put("id", idAlimento[i]);
                content_aux1.put("numero", numeroAlimento[i]);
                content_aux1.put("titulo",tituloAlimento[i]);
                content_aux1.put("data", dataAlimento[i]);
                content_aux1.put("link", linksAlimento[i]);

                db.insert("alimento_celular",null, content_aux1);
            }

        }catch(Exception ex){

            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }



    }

    private void updateVersao5AlimentoCelulares(SQLiteDatabase db){

        //parte dos eventos

        String dropTableEventos = "DROP IF EXISTS evento";
        String createTableEventos = "CREATE TABLE evento (\n" +
                "    id        DOUBLE UNIQUE,\n" +
                "    nome      TEXT   NOT NULL,\n" +
                "    mes       INTEGER,\n"+
                "    data      TEXT   NOT NULL,\n" +
                "    descricao TEXT\n" +
                ");";

        int [] idEventos = new int[]{1,2,3,4,5,6,7,8,9,10,
                11,12,13,14,15,16,17,18,19,20,
                21,22,23,24,25,26,17,28,29,30,31};

        int [] mesEventos = new int[]{1,1,1,1,2,3,3,4,4,4,4,5,6,6,6,7,7,7,
                8,8,8,8,9,9,9,10,10,11,12,12,12} ;

        String [] nomeEventos = new String[]{"Campanha do Projeto de Vida","Unção de novos líderes","Conectado com uma vida (Rede Jovem)",
                "Encontro de Adolescentes","Acamp Jovem","Chá para Mulheres","Encontro com Deus (Homens e Mulheres)",
                "Conclusão da 1ª etapa do Plano de Leitura","Liberação Profética","Congresso de Mulheres","Início Camapanha de Ana",
                "Pentecostes (Israel)","Pentecostes(Israel)","Congresso de encerramento Campanha de Ana","Encontro de Adolescentes",
                "Conclusão da 2ª etapado Plano de Leitura","Festa de Pentecostes","Encontro com Deus (Homens)",
                "Abertura Campanha de Daniel","Congresso de Sacerdotes","Café da manhã para a Família (Todas as igrejas)",
                "Encerramento Campanha de Daniel","Encontro com Deus para Mulheres","Encontro de Casais","Encerramento (Células) 3ª Etapa do Plano de Leitura",
                "Festa Tabernáculos Israel","Festa de Tabernáculos em Marabá","Encontro com Deus para Homens","Encontro de Multiplicadores",
                "Encerramento (Células) 4ª etapa do Plano de Leitura","Culto da Virada"};

        String [] dataEventos = new String[]{"01 a 12.01.17","13.01.17","21.01.17","27 a 29.01.17","26 a 28.02.17","04.03.17",
                "10 a 12.03.17","01.04.17","13 a 16.04.17","20 a 22.04.17","24.04.17","22 a 30.05.17","01 a 03.06.17","9 a 10.06.17",
                "23 a 25.06.17","01.07.17","06 a 08.07.17","14 a 16.07.17","01.08.17","10 a 12.08.17","13.08.17","21.08.17",
                "09 a 11.09.17","09 a 11.09.17","30.09.17","04 a 13.10.17","23 a 29.10.17","03 a 05.11.17","08 a 10.12.17",
                "30.12.17","31.12.17"};

        String [] descricaoEventos = new String[]{"Serão 12 cultos, iniciando em 1º. de Janeiro e indo até o dia 12. É o momento de você marcar território de conquista em 2017."
                ,"O Senhor nos deu a chave da vitória quando nos instruiu a buscar primeiro o seu Reino e a sua Justiça. Então, ao priorizar cultuar nestes primeiros 12 dias de 2017, você está dizendo como será a sua vida durante todo ano. Priorize, quem mais precisa é você.",
                "Descrição ainda não disponível","Descrição ainda não disponível","Descrição ainda não disponível","Descrição ainda não disponível",
                "Descrição ainda não disponível","Descrição ainda não disponível","Descrição ainda não disponível","Descrição ainda não disponível",
                "Descrição ainda não disponível","Descrição ainda não disponível","Descrição ainda não disponível","Descrição ainda não disponível",
                "Descrição ainda não disponível","Descrição ainda não disponível","Descrição ainda não disponível","Descrição ainda não disponível",
                "Descrição ainda não disponível","Descrição ainda não disponível","Descrição ainda não disponível","Descrição ainda não disponível",
                "Descrição ainda não disponível","Descrição ainda não disponível","Descrição ainda não disponível","Descrição ainda não disponível",
                "Descrição ainda não disponível","Descrição ainda não disponível","Descrição ainda não disponível","Descrição ainda não disponível",
                "Descrição ainda não disponível","Descrição ainda não disponível"};

        db.execSQL(dropTableEventos);
        db.execSQL(createTableEventos);


        for(int i =0; i < idEventos.length; i++){

            ContentValues content_aux1 = new ContentValues();

            content_aux1.put("id", idEventos[i]);
            content_aux1.put("nome", nomeEventos[i]);
            content_aux1.put("mes", mesEventos[i]);
            content_aux1.put("data", dataEventos[i]);
            content_aux1.put("descricao", descricaoEventos[i]);

            db.insert("evento",null, content_aux1);
        }

        String dropTableAlimentoCelular = "DROP IF EXISTS alimento_celular";

        String queryCreateTableAlimentoCelular = "CREATE TABLE alimento_celular (\n" +
                "    id     INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    numero INTEGER,\n" +
                "    titulo TEXT    NOT NULL,\n" +
                "    data   TEXT,\n" +
                "    link   TEXT\n" +
                ");";

        db.execSQL(dropTableAlimentoCelular);
        db.execSQL(queryCreateTableAlimentoCelular);

        ContentValues content_aux1 = new ContentValues();

        content_aux1.put("id", 1);
        content_aux1.put("titulo", "001 - Benção no pronunciamento das sentenças");
        content_aux1.put("data", "23/01/17");
        content_aux1.put("link", "http://igcristo.com/down/alimentosCelulares/Alimentos%20Celulares%20de%20Multiplicacao%" +
                "20-%20001-2017%20-%20Bencao%20no%20pronunciamento%20das%20sentencas.pdf");

        db.insert("alimento_celular",null, content_aux1);

        updateTableLeitura2Tri(db);
        updateTableLeitura3Tri(db);
        updateTableLeitura4Tri(db);
        updateTableLeituraReferencias(db);

    }

    private void updateTableLeitura2Tri(SQLiteDatabase db){

        String dropTableLeitura2Tri = "DROP IF EXISTS leitura_2_tri";

        String createTabelaLeitura2Tri = "CREATE TABLE leitura_2_tri (\n" +
                "    id       INTEGER PRIMARY KEY,\n" +
                "    mes      INTEGER,\n" +
                "    dia      INTEGER,\n" +
                "    book_id  INTEGER,\n" +
                "    capitulo INTEGER,\n" +
                "    titulo   TEXT\n" +
                ");";

        db.execSQL(dropTableLeitura2Tri);
        db.execSQL(createTabelaLeitura2Tri);

        int [] idLeitura2Tri = new int[]{1,2,3,4,5,6,7,8,9,10,
                11,12,13,14,15,16,17,18,19,20,
                21,22,23,24,25,26,27,28,29,30,
                31,32,33,34,35,36,37,38,39,40,
                41,42,43,44,45,46,47,48,49,50,
                51,52,53,54,55,56,57,58,59,60,
                61,62,63,64,65,66,67,68,69,70,
                71,72,73,74,75,76,77,78,79,80,
                81,82,83,84,85,86,87,88,89,90,
                91,92,93,94,95,96,97,98,99,100,
                101,102,103,104,105,106,107,108,109,110,
                111,112,113,114,115,116,117,118,119,120,
                121,122,123,124,125,126,127,128,129,130,
                131,132,133,134,135,136,137,138,139,140,
                141,142,143,144,145,146,147,148,149,150
                ,151,152,153,154,155,156,157,158,159,160
                ,161,162,163,164,165,166,167,168,169,170
                ,171,172,173,174,175,176,177,178,179,180
                ,181,182,183,184,185,186,187,188,189,190
                ,191,192,193,194,195,196,197,198,199,200
                ,201,202,203,204,205,206,207,208,209,210
                ,211,212,213,214,215,216,217,218,219,220,
                221,222,223,224,225,226,227,228,229,230,
                231,232,233,234,235,236,237,238,239,240
                ,241,242,243,244,245,246,247,248,249,250
                ,251,252,253,254,255,256,257,258,259,260,
                261,262,263,264,265,266,267,268,269,270,
                271,272,273,274,275,276,277,278,279,280
                ,281,282,283,284,285,286,287,288,289,290
                ,291,292,293,294,295,296,297,298,299,300
                ,301,302,303,304,305,306,307,308,309,310
                ,311,312,313,314,315,316,317,318,319,320
                ,321,322,323,324,325,326};

        int [] mesLeitura2Tri = new int[]{4,4,4,4,4,4,4,4,4,4,
                4,4,4,4,4,4,4,4,4,4,
                4,4,4,4,4,4,4,4,4,4,
                4,4,4,4,4,4,4,4,4,4,
                4,4,4,4,4,4,4,4,4,4,
                4,4,4,4,4,4,4,4,4,4,
                4,4,4,4,4,4,4,4,4,4,
                4,4,4,4,4,4,4,4,4,4,
                4,4,4,5,5,5,5,5,5,5,
                5,5,5,5,5,5,5,5,5,5,
                5,5,5,5,5,5,5,5,5,5,
                5,5,5,5,5,5,5,5,5,5,
                5,5,5,5,5,5,5,5,5,5,
                5,5,5,5,5,5,5,5,5,5,
                5,5,5,5,5,5,5,5,5,5,
                5,5,5,5,5,5,5,5,5,5,
                5,5,5,5,5,5,5,5,5,5,
                5,5,5,5,5,5,5,5,5,5,
                5,5,5,5,5,5,5,5,5,5,
                5,5,5,5,5,5,5,5,5,5,
                5,5,5,5,5,5,5,6,6,6,
                6,6,6,6,6,6,6,6,6,6,
                6,6,6,6,6,6,6,6,6,6,
                6,6,6,6,6,6,6,6,6,6,
                6,6,6,6,6,6,6,6,6,6,
                6,6,6,6,6,6,6,6,6,6,
                6,6,6,6,6,6,6,6,6,6,
                6,6,6,6,6,6,6,6,6,6,
                6,6,6,6,6,6,6,6,6,6,
                6,6,6,6,6,6,6,6,6,6,
                6,6,6,6,6,6,6,6,6,6,
                6,6,6,6,6,6,6,6,6,6,
                6,6,6,6,6,6};

        int [] diaLeitura2Tri = new int[]{6,6,7,7,8,8,9,9,10,10,
                11,11,12,12,13,13,14,14,14,15,
                15,15,15,16,16,16,17,17,17,17,
                18,18,18,18,19,19,19,19,20,20,
                20,20,21,21,21,21,22,22,22,22,
                23,23,23,23,24,24,24,24,25,25,
                25,25,26,26,26,26,27,27,27,27,
                28,28,28,28,29,29,29,29,29,30,
                30,30,30,1,1,1,2,2,2,2,
                3,3,3,3,4,4,4,4,5,5,
                5,5,6,6,6,6,7,7,7,7,
                8,8,8,8,9,9,9,9,10,10,
                10,10,11,11,11,12,12,12,12,13,
                13,13,13,14,14,14,14,15,15,15,
                15,16,16,16,16,17,17,17,17,18,
                18,18,18,19,19,19,19,20,20,20,
                20,21,21,21,21,22,22,22,22,23,
                23,23,23,24,24,24,11,11,24,25,
                25,25,25,26,26,26,26,27,27,27,
                27,28,28,28,28,29,29,29,29,30,
                30,30,30,31,31,31,31,1,1,1,
                1,2,2,2,2,3,3,3,4,4,
                4,4,5,5,5,5,6,6,6,6,
                7,7,7,7,8,8,8,8,9,9,
                9,9,10,10,10,10,11,11,11,11,
                12,12,12,12,13,13,13,13,14,14,
                14,14,15,15,15,15,16,16,16,16,
                17,17,17,17,18,18,18,18,19,19,
                19,19,20,20,20,20,21,21,21,21,
                22,22,22,22,23,23,23,23,24,24,
                24,24,25,25,25,25,26,26,26,26,
                26,27,27,27,28,28,28,28,29,29,
                29,29,30,30,30,30};

        int [] bookIdLeitura2Tri = new int[]{55,55,55,56,56,56,57,58,58,
                58,58,58,58,58,58,58,58,58,
                58,58,59,59,59,59,60,60,60,
                60,60,61,61,61,62,62,62,62,
                62,63,64,65,66,66,66,66,66,
                66,66,66,66,66,66,66,66,66,
                66,66,66,66,66,66,66,66,1,
                1,1,19,1,1,1,19,1,1,1,
                19,1,1,1,1,19,1,1,1,19,
                1,1,19,1,1,1,19,1,1,1,
                19,1,1,1,19,1,1,1,19,1,
                1,1,19,1,1,1,19,1,1,1,
                19,1,1,1,19,1,1,1,19,1,
                1,19,1,2,2,19,2,2,2,19,
                2,2,2,19,2,2,2,19,2,2,
                2,19,2,2,2,19,2,2,2,19,
                2,2,2,19,2,2,2,19,2,2,
                2,19,2,2,2,19,2,2,2,19,
                2,2,2,1,1,19,2,2,3,19,
                3,3,3,19,3,3,3,19,3,3,
                3,19,3,3,3,19,3,3,3,19,
                3,3,3,19,3,3,3,19,3,3,
                3,19,3,3,19,4,4,4,19,4,
                4,4,19,4,4,4,19,4,4,4,
                19,4,4,4,19,4,4,4,19,4,
                4,4,19,4,4,4,19,4,4,4,
                19,4,4,4,19,4,4,4,19,4,
                4,4,19,5,5,5,19,5,5,5,
                19,5,5,5,19,5,5,5,19,5,
                5,5,19,5,5,5,19,5,5,5,
                19,5,5,5,19,5,5,5,19,5,
                5,5,19,5,5,5,5,6,6,6,
                19,6,6,6,19,6,6,6,6,6,
                6,6,6};

        int [] capituloLeitura2Tri = new int[]{2,3,4,1,2,3,1,1,2,3,
                4,5,6,7,8,9,10,11,12,13,
                1,2,3,4,1,2,3,4,5,1,
                2,3,1,2,3,4,5,1,1,1,
                1,2,3,4,5,6,7,8,9,10,
                11,12,13,14,15,16,17,18,19,20,
                21,22,1,2,3,1,4,5,6,2,7,8,
                9,3,10,11,12,13,4,14,15,16,
                5,17,18,6,19,20,21,7,22,23,
                24,8,25,26,27,9,28,29,30,10,
                31,32,33,11,34,35,36,12,37,38,
                39,13,40,41,42,14,43,44,45,15,
                46,47,16,50,1,2,17,3,4,5,
                18,6,7,8,19,9,10,11,20,12,
                13,14,21,15,16,17,22,18,19,20,
                23,21,22,23,24,24,25,26,25,27,
                28,29,26,30,31,32,27,33,34,35,
                28,36,37,38,48,49,29,39,40,1,
                30,2,3,4,31,5,6,7,32,8,
                9,10,33,11,12,13,34,14,15,16,
                35,17,18,19,36,20,21,22,37,23,
                24,25,38,26,27,39,1,2,3,40,
                4,5,6,41,7,8,9,42,10,11,
                12,43,13,14,15,44,16,17,18,45,
                19,20,21,46,22,23,24,47,25,26,
                27,48,28,29,30,49,31,32,33,50,
                34,35,36,51,1,2,3,52,4,5,6,
                53,7,8,9,54,10,11,12,55,13,
                14,15,56,16,17,18,57,19,20,21,
                58,22,23,24,59,25,26,27,60,28,
                29,30,61,31,32,33,34,1,2,3,
                62,4,5,6,63,7,8,9,64,10,
                11,12,65};

        String [] tituloLeitura2Tri = new String[]{"II Timóteo 2","II Timóteo 3","II Timóteo 4","Tito 1","Tito 2","Tito 3","Filemon 1","Hebreus 1","Hebreus 2","Hebreus 3",
                "Hebreus 4","Hebreus 5","Hebreus 6","Hebreus 7","Hebreus 8","Hebreus 9","Hebreus 10","Hebreus 11","Hebreus 12","Hebreus 13",
                "Tiago 1","Tiago 2","Tiago 3","Tiago 4","I Pedro 1","I Pedro 2","I Pedro 3","I Pedro 4","I Pedro 5","II Pedro 1",
                "II Pedro 2","II Pedro 3","I João 1","I João 2","I João 3","I João 4","I João 5","II João ","III João ","Judas",
                "Apocalipse 1","Apocalipse 2","Apocalipse 3","Apocalipse 4","Apocalipse 5","Apocalipse 6","Apocalipse 7","Apocalipse 8","Apocalipse 9","Apocalipse 10",
                "Apocalipse 11","Apocalipse 12","Apocalipse 13","Apocalipse 14","Apocalipse 15","Apocalipse 16","Apocalipse 17","Apocalipse 18","Apocalipse 19","Apocalipse 20",
                "Apocalipse 21","Apocalipse 22","Gênesis 1","Gênesis 2","Gênesis 3","Salmos 1","Gênesis 4","Gênesis 5","Gênesis 6","Salmos 2",
                "Gênesis 7","Gênesis 8","Gênesis 9","Salmos 3","Gênesis 10","Gênesis 11","Gênesis 12","Gênesis 13","Salmos 4","Gênesis 14",
                "Gênesis 15","Gênesis 16","Salmos 5","Gênesis 17","Gênesis 18","Salmos 6","Gênesis 19","Gênesis 20","Gênesis 21","Salmos 7",
                "Gênesis 22","Gênesis 23","Gênesis 24","Salmos 8","Gênesis 25","Gênesis 26","Gênesis 27","Salmos 9","Gênesis 28","Gênesis 29",
                "Gênesis 30","Salmos 10","Gênesis 31","Gênesis 32","Gênesis 33","Salmos 11","Gênesis 34","Gênesis 35","Gênesis 36","Salmos 12",
                "Gênesis 37","Gênesis 38","Gênesis 39","Salmos 13","Gênesis 40","Gênesis 41","Gênesis 42","Salmos 14","Gênesis 43","Gênesis 44",
                "Gênesis 45","Salmos 15","Gênesis 46","Gênesis 47","Salmos 16","Gênesis 50","Êxodo 1","Êxodo 2","Salmos 17","Êxodo 3",
                "Êxodo 4","Êxodo 5","Salmos 18","Êxodo 6","Êxodo 7","Êxodo 8","Salmos 19","Êxodo 9","Êxodo 10","Êxodo 11",
                "Salmos 20","Êxodo 12","Êxodo 13","Êxodo 14","Salmos 21","Êxodo 15","Êxodo 16","Êxodo 17","Salmos 22","Êxodo 18",
                "Êxodo 19","Êxodo 20","Salmos 23","Êxodo 21","Êxodo 22","Êxodo 23","Salmos 24","Êxodo 24","Êxodo 25","Êxodo 26",
                "Salmos 25","Êxodo 27","Êxodo 28","Êxodo 29","Salmos 26","Êxodo 30","Êxodo 31","Êxodo 32","Salmos 27","Êxodo 33",
                "Êxodo 34","Êxodo 35","Salmos 28","Êxodo 36","Êxodo 37","Êxodo 38","Gênesis 48","Gênesis 49","Salmos 29","Êxodo 39",
                "Êxodo 40","Levítico 1","Salmos 30","Levítico 2","Levítico 3","Levítico 4","Salmos 31","Levítico 5","Levítico 6","Levítico 7",
                "Salmos 32","Levítico 8","Levítico 9","Levítico 10","Salmos 33","Levítico 11","Levítico 12","Levítico 13","Salmos 34","Levítico 14",
                "Levítico 15","Levítico 16","Salmos 35","Levítico 17","Levítico 18","Levítico 19","Salmos 36","Levítico 20","Levítico 21","Levítico 22",
                "Salmos 37","Levítico 23","Levítico 24","Levítico 25","Salmos 38","Levítico 26","Levítico 27","Salmos 39","Números 1","Números 2",
                "Números 3","Salmos 40","Números 4","Números 5","Números 6","Salmos 41","Números 7","Números 8","Números 9","Salmos 42",
                "Números 10","Números 11","Números 12","Salmos 43","Números 13","Números 14","Números 15","Salmos 44","Números 16","Números 17",
                "Números 18","Salmos 45","Números 19","Números 20","Números 21","Salmos 46","Números 22","Números 23","Números 24","Salmos 47",
                "Números 25","Números 26","Números 27","Salmos 48","Números 28","Números 29","Números 30","Salmos 49","Números 31","Números 32",
                "Números 33","Salmos 50","Números 34","Números 35","Números 36","Salmos 51","Deuteronômio 1","Deuteronômio 2","Deuteronômio 3","Salmos 52",
                "Deuteronômio 4","Deuteronômio 5","Deuteronômio 6","Salmos 53","Deuteronômio 7","Deuteronômio 8","Deuteronômio 9","Salmos 54","Deuteronômio 10","Deuteronômio 11",
                "Deuteronômio 12","Salmos 55","Deuteronômio 13","Deuteronômio 14","Deuteronômio 15","Salmos 56","Deuteronômio 16","Deuteronômio 17","Deuteronômio 18","Salmos 57",
                "Deuteronômio 19","Deuteronômio 20","Deuteronômio 21","Salmos 58","Deuteronômio 22","Deuteronômio 23","Deuteronômio 24","Salmos 59","Deuteronômio 25","Deuteronômio 26",
                "Deuteronômio 27","Salmos 60","Deuteronômio 28","Deuteronômio 29","Deuteronômio 30","Salmos 61","Deuteronômio 31","Deuteronômio 32","Deuteronômio 33","Deuteronômio 34",
                "Josué 1","Josué 2","Josué 3","Salmos 62","Josué 4","Josué 5","Josué 6","Salmos 63","Josué 7","Josué 8",
                "Josué 9","Salmos 64","Josué 10","Josué 11","Josué 12","Salmos 65"};


        ContentValues contentValues = new ContentValues();

        for(int i = 0; i < idLeitura2Tri.length; i++){


            contentValues.put("id", idLeitura2Tri[i]);
            contentValues.put("mes", mesLeitura2Tri[i]);
            contentValues.put("dia", diaLeitura2Tri[i]);
            contentValues.put("book_id", bookIdLeitura2Tri[i]);
            contentValues.put("capitulo", capituloLeitura2Tri[i]);
            contentValues.put("titulo", tituloLeitura2Tri[i]);

            db.insert("leitura_2_tri", null,contentValues);

        }

    }

    private void updateTableLeitura3Tri(SQLiteDatabase db){

        String dropTableLeitura3Tri = "DROP IF EXISTS leitura_3_tri";

        String createTabelaLeitura3Tri = "CREATE TABLE leitura_3_tri (\n" +
                "    id       INTEGER PRIMARY KEY,\n" +
                "    mes      INTEGER,\n" +
                "    dia      INTEGER,\n" +
                "    book_id  INTEGER,\n" +
                "    capitulo INTEGER,\n" +
                "    titulo   TEXT\n" +
                ");";

        db.execSQL(dropTableLeitura3Tri);
        db.execSQL(createTabelaLeitura3Tri);


        int [] idLeitura3Tri = new int[]{1,2,3,4,5,6,7,8,9,10,
                11,12,13,14,15,16,17,18,19,20,
                21,22,23,24,25,26,27,28,29,30,
                31,32,33,34,35,36,37,38,39,40,
                41,42,43,44,45,46,47,48,49,50,
                51,52,53,54,55,56,57,58,59,60,
                61,62,63,64,65,66,67,68,69,70,
                71,72,73,74,75,76,77,78,79,80,
                81,82,83,84,85,86,87,88,89,90,
                91,92,93,94,95,96,97,98,99,100,
                101,102,103,104,105,106,107,108,109,110,
                111,112,113,114,115,116,117,118,119,120,
                121,122,123,124,125,126,127,128,129,130,
                131,132,133,134,135,136,137,138,139,140,
                141,142,143,144,145,146,147,148,149,150
                ,151,152,153,154,155,156,157,158,159,160
                ,161,162,163,164,165,166,167,168,169,170
                ,171,172,173,174,175,176,177,178,179,180
                ,181,182,183,184,185,186,187,188,189,190
                ,191,192,193,194,195,196,197,198,199,200
                ,201,202,203,204,205,206,207,208,209,210
                ,211,212,213,214,215,216,217,218,219,220,
                221,222,223,224,225,226,227,228,229,230,
                231,232,233,234,235,236,237,238,239,240
                ,241,242,243,244,245,246,247,248,249,250
                ,251,252,253,254,255,256,257,258,259,260,
                261,262,263,264,265,266,267,268,269,270,
                271,272,273,274,275,276,277,278,279,280
                ,281,282,283,284,285,286,287,288,289,290
                ,291,292,293,294,295,296,297,298,299,300
                ,301,302,303,304,305,306,307,308,309,310
                ,311,312,313,314,315,316,317,318,319,320
                ,321,322,323,324,325,326326,327,328,329,330,
                331,332,333,334,335,336,337,338,339,341,342,
                343,344,345,346,347,348,349,350,351,352,353,
                354,355,356,357,358,359,360,361,362,363,364,
                365,366,367,368,370,371,372,373,374,375,376,
                378,379};
        int [] mesLeitura3Tri= new int[]{7,7,7,7,7,7,7,7,7,7,
                7,7,7,7,7,7,7,7,7,7,
                7,7,7,7,7,7,7,7,7,7,
                7,7,7,7,7,7,7,7,7,7,
                7,7,7,7,7,7,7,7,7,7,7,
                7,7,7,7,7,7,7,7,7,7,7,
                7,7,7,7,7,7,7,7,7,7,7,
                7,7,7,7,7,7,7,7,7,7,7,
                7,7,7,7,7,7,7,7,7,7,7,
                7,7,7,7,7,7,7,7,7,7,7,
                7,7,7,7,7,7,7,7,7,7,7,
                7,7,7,7,7,7,7,8,8,8,8
                ,8,8,8,8,8,8,8,8,8,8,8
                ,8,8,8,8,8,8,8,8,8,8,8
                ,8,8,8,8,8,8,8,8,8,
                8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8
                ,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,9,9,
                9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9
                ,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9
                ,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9};

        int [] diaLeitura3Tri = new int[]{1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5,6,6,6,6,7,7,7,7,8,8,8,8,9,9,9,9,10,10,10,10,11,11,11,
                11,12,12,12,12,13,13,13,13,14,14,14,14,15,15,15,15,16,16,16,16,17,17,17,17,18,18,18,18,19,19,19,19,20,20,20,20,21,21,
                21,21,22,22,22,22,23,23,23,23,24,24,24,24,25,25,25,25,26,26,26,26,27,27,27,27,28,28,28,28,29,29,29,29,30,30,30,30,31
                ,31,31,31,1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5,6,6,6,6,7,7,7,7,8,8,8,8,9,9,9,9,9,10,10,10,10,11,11,11,11,12,12,12
                ,12,13,13,13,13,14,14,14,14,15,15,15,15,16,16,16,16,17,17,17,17,18,18,18,18,19,19,19,19,20,20,20,20,21,21,21,21,22,22
                ,22,22,23,23,23,23,24,24,24,24,25,25,25,25,26,26,26,27,27,27,27,27,28,28,29,29,29,29,30,30,30,30,31,31,31,31,1,1,1,1,
                2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5,6,6,6,6,6,7,7,7,7,7,8,8,8,9,9,9,10,10,10,11,11,11,11,12,12,12,12,12,13,13,13,13,13,14
                ,14,14,14,14,15,15,15,15,15,16,16,16,16,16,17,17,17,17,17,18,18,18,18,18,19,19,19,19,19,20,20,20,20,20,21,21,21,21,21
                ,22,22,22,22,23,23,23,23,24,24,24,24,25,25,25,25,26,26,26,26,27,27,27,28,28,28,28,29,29,29,29,30,30,30,27,30};

        int [] bookIdLeitura3Tri = new int[]{6,6,6,19,6,6,6,19,6,6,6,19,6,6,6,19,7,7,7,19,7,7,7,19,7,7,7,19,7,7,7,19,7,7,7,19,7,7,
                7,19,7,7,7,19,8,8,8,19,9,9,9,9,9,9,9,19,9,9,9,19,9,9,9,19,9,9,9,19,9,9,9,19,9,9,9,19,9,9,9,19,9,9,9,19,9,9,9,9,10,
                10,10,19,10,10,10,19,10,10,10,19,10,10,10,19,10,10,10,19,10,10,10,19,10,10,10,19,10,10,10,19,11,11,11,19,11,11,11
                ,19,11,11,11,19,11,11,11,19,11,11,11,19,11,11,11,19,11,11,11,11,12,12,12,12,19,12,12,12,19,12,12,12,19,12,12,12,19,
                12,12,12,19,12,12,12,19,12,12,12,19,12,12,12,19,13,13,13,19,13,13,13,19,13,13,13,19,13,13,13,19,13,13,13,19,13,13,
                13,19,13,13,13,19,13,13,13,19,13,13,13,19,13,14,14,14,14,14,19,19,14,19,14,14,14,19,14,14,14,19,14,14,14,19,14,14,
                14,19,14,14,14,19,14,14,14,19,14,14,14,19,14,14,14,19,14,14,14,19,14,14,14,19,15,15,15,15,15,15,15,15,15,15,16,16,
                16,16,16,16,16,16,16,16,16,16,16,17,17,17,17,17,17,17,17,17,17,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,
                18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,23,23,23,19,23,23,23,19,23,23,23,19,23,23,23,
                19,23,23,23,19,23,23,19,23,23,23,19,23,23,23,19,23,23,23,23,19};

        int [] capituloLeitura3Tri = new int[]{13,14,15,66,16,17,18,67,19,20,21,68,22,23,24,69,1,2,3,70,4,5,6,71,7,8,9,72,10,11,12,
                73,13,14,15,74,16,17,18,75,19,20,21,76,1,2,3,4,1,2,3,77,4,5,6,78,7,8,9,79,10,11,12,80,13,14,15,81,16,17,18,82,19,20,
                21,83,22,23,24,84,25,26,27,85,28,29,30,31,1,2,3,86,4,5,6,87,7,8,9,88,10,11,12,89,13,14,15,90,16,17,18,91,19,20,21,92,
                22,23,24,93,1,2,3,94,4,5,6,95,7,8,9,96,10,11,12,97,13,14,15,98,16,17,18,99,19,20,21,22,1,2,3,4,100,5,6,7,101,8,9,10,
                102,11,12,13,103,14,15,16,104,17,18,19,105,20,21,22,106,23,24,25,107,1,2,3,108,4,5,6,109,7,8,9,110,10,11,12,111,13,
                14,15,112,16,17,18,113,19,20,21,114,22,23,24,115,25,26,27,116,28,1,2,3,4,5,117,118,6,119,7,8,9,120,10,11,12,121,13,
                14,15,122,16,17,18,123,19,20,21,124,22,23,24,125,25,26,27,126,28,29,30,127,31,32,33,128,34,35,36,129,1,2,3,4,5,6,7,
                8,9,10,1,2,3,4,5,6,7,8,9,10,11,12,13,1,2,3,4,5,6,7,8,9,10,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,
                23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,1,2,3,130,4,5,6,131,7,8,9,132,10,11,12,133,13,14,15,134,17,18
                ,135,19,20,21,136,22,23,24,137,25,26,27,16,128};


        String [] tituloLeitura3Tri = new String[]{"Josué 13","Josué 14","Josué 15","Salmos 66","Josué 16","Josué 17","Josué 18",
                "Salmos 67","Josué 19","Josué 20","Josué 21","Salmos 68","Josué 22","Josué 23","Josué 24","Salmos 69","Juízes 1",
                "Juízes 2","Juízes 3","Salmos 70","Juízes 4","Juízes 5","Juízes 6","Salmos 71","Juízes 7","Juízes 8","Juízes 9",
                "Salmos 72","Juízes 10","Juízes 11","Juízes 12","Salmos 73","Juízes 13","Juízes 14","Juízes 15","Salmos 74",
                "Juízes 16","Juízes 17","Juízes 18","Salmos 75","Juízes 19","Juízes 20","Juízes 21","Salmos 76","Rute 1","Rute 2",
                "Rute 3","Rute 4","I Samuel 1","I Samuel 2","I Samuel 3","Salmos 77","I Samuel 4","I Samuel 5","I Samuel 6",
                "Salmos 78","I Samuel 7","I Samuel 8","I Samuel 9","Salmos 79","I Samuel 10","I Samuel 11","I Samuel 12","Salmos 80",
                "I Samuel 13","I Samuel 14","I Samuel 15","Salmos 81","I Samuel 16","I Samuel 17","I Samuel 18","Salmos 82",
                "I Samuel 19","I Samuel 20","I Samuel 21","Salmos 83","I Samuel 22","I Samuel 23","I Samuel 24","Salmos 84"
                ,"I Samuel 25","I Samuel 26","I Samuel 27","Salmos 85","I Samuel 28","I Samuel 29","I Samuel 30","I Samuel 31",
                "II Samuel 1","II Samuel 2","II Samuel 3","Salmos 86","II Samuel 4","II Samuel 5","II Samuel 6","Salmos 87"
                ,"II Samuel 7","II Samuel 8","II Samuel 9","Salmos 88","II Samuel 10","II Samuel 11","II Samuel 12","Salmos 89",
                "II Samuel 13","II Samuel 14","II Samuel 15","Salmos 90","II Samuel 16","II Samuel 17","II Samuel 18","Salmos 91",
                "II Samuel 19","II Samuel 20","II Samuel 21","Salmos 92","II Samuel 22","II Samuel 23","II Samuel 24","Salmos 93",
                "I Reis 1","I Reis 2","I Reis 3","Salmos 94","I Reis 4","I Reis 5","I Reis 6","Salmos 95","I Reis 7","I Reis 8",
                "I Reis 9","Salmos 96","I Reis 10","I Reis 11","I Reis 12","Salmos 97","I Reis 13","I Reis 14","I Reis 15",
                "Salmos 98","I Reis 16","I Reis 17","I Reis 18","Salmos 99","I Reis 19","I Reis 20","I Reis 21","I Reis 22",
                "II Reis 1","II Reis 2","II Reis 3","II Reis 4","Salmos 100","II Reis 5","II Reis 6","II Reis 7","Salmos 101",
                "II Reis 8","II Reis 9","II Reis 10","Salmos 102","II Reis 11","II Reis 12","II Reis 13","Salmos 103","II Reis 14",
                "II Reis 15","II Reis 16","Salmos 104","II Reis 17","II Reis 18","II Reis 19","Salmos 105","II Reis 20",
                "II Reis 21","II Reis 22","Salmos 106","II Reis 23","II Reis 24","II Reis 25","Salmos 107","I Crônicas 1",
                "I Crônicas 2","I Crônicas 3","Salmos 108","I Crônicas 4","I Crônicas 5","I Crônicas 6","Salmos 109","I Crônicas 7",
                "I Crônicas 8","I Crônicas 9","Salmos 110","I Crônicas 10","I Crônicas 11","I Crônicas 12","Salmos 111",
                "I Crônicas 13","I Crônicas 14","I Crônicas 15","Salmos 112","I Crônicas 16","I Crônicas 17","I Crônicas 18",
                "Salmos 113","I Crônicas 19","I Crônicas 20","I Crônicas 21","Salmos 114","I Crônicas 22","I Crônicas 23",
                "I Crônicas 24","Salmos 115","I Crônicas 25","I Crônicas 26","I Crônicas 27","Salmos 116","I Crônicas 28",
                "II Crônicas 1","II Crônicas 2","II Crônicas 3","II Crônicas 4","II Crônicas 5","Salmos 117","Salmos 118",
                "II Crônicas 6","Salmos 119","II Crônicas 7","II Crônicas 8","II Crônicas 9","Salmos 120","II Crônicas 10",
                "II Crônicas 11","II Crônicas 12","Salmos 121","II Crônicas 13","II Crônicas 14","II Crônicas 15","Salmos 122",
                "II Crônicas 16","II Crônicas 17","II Crônicas 18","Salmos 123","II Crônicas 19","II Crônicas 20","II Crônicas 21"
                ,"Salmos 124","II Crônicas 22","II Crônicas 23","II Crônicas 24","Salmos 125","II Crônicas 25","II Crônicas 26",
                "II Crônicas 27","Salmos 126","II Crônicas 28","II Crônicas 29","II Crônicas 30","Salmos 127","II Crônicas 31",
                "II Crônicas 32","II Crônicas 33","Salmos 128","II Crônicas 34","II Crônicas 35","II Crônicas 36","Salmos 129",
                "Esdras 1","Esdras 2","Esdras 3","Esdras 4","Esdras 5","Esdras 6","Esdras 7","Esdras 8","Esdras 9","Esdras 10",
                "Neemias 1","Neemias 2","Neemias 3","Neemias 4","Neemias 5","Neemias 6","Neemias 7","Neemias 8","Neemias 9",
                "Neemias 10","Neemias 11","Neemias 12","Neemias 13","Ester 1","Ester 2","Ester 3","Ester 4","Ester 5","Ester 6",
                "Ester 7","Ester 8","Ester 9","Ester 10","Jó 1","Jó 2","Jó 3","Jó 4","Jó 5","Jó 6","Jó 7","Jó 8","Jó 9","Jó 10",
                "Jó 11","Jó 12","Jó 13","Jó 14","Jó 15","Jó 16","Jó 17","Jó 18","Jó 19","Jó 20","Jó 21","Jó 22","Jó 23","Jó 24",
                "Jó 25","Jó 26","Jó 27","Jó 28","Jó 29","Jó 30","Jó 31","Jó 32","Jó 33","Jó 34","Jó 35","Jó 36","Jó 37","Jó 38",
                "Jó 39","Jó 40","Isaías 1","Isaías 2","Isaías 3","Salmos 130","Isaías 4","Isaías 5","Isaías 6","Salmos 131",
                "Isaías 7","Isaías 8","Isaías 9","Salmos 132","Isaías 10","Isaías 11","Isaías 12","Salmos 133","Isaías 13",
                "Isaías 14","Isaías 15","Salmos 134","Isaías 17","Isaías 18","Salmos 135","Isaías 19","Isaías 20","Isaías 21",
                "Salmos 136","Isaías 22","Isaías 23","Isaías 24","Salmos 137","Isaías 25","Isaías 26","Isaías 27","Isaías 16",
                "Salmos 138"};

        ContentValues contentValues = new ContentValues();

        for(int i = 0; i < idLeitura3Tri.length; i++){


            contentValues.put("id", idLeitura3Tri[i]);
            contentValues.put("mes", mesLeitura3Tri[i]);
            contentValues.put("dia", diaLeitura3Tri[i]);
            contentValues.put("book_id", bookIdLeitura3Tri[i]);
            contentValues.put("capitulo", capituloLeitura3Tri[i]);
            contentValues.put("titulo", tituloLeitura3Tri[i]);

            db.insert("leitura_3_tri", null,contentValues);

        }




    }

    private void updateTableLeitura4Tri(SQLiteDatabase db){

        String dropTableLeitura4Tri = "DROP IF EXISTS leitura_4_tri";

        String createTabelaLeitura4Tri = "CREATE TABLE leitura_4_tri (\n" +
                "    id       INTEGER PRIMARY KEY,\n" +
                "    mes      INTEGER,\n" +
                "    dia      INTEGER,\n" +
                "    book_id  INTEGER,\n" +
                "    capitulo INTEGER,\n" +
                "    titulo   TEXT\n" +
                ");";

        db.execSQL(dropTableLeitura4Tri);
        db.execSQL(createTabelaLeitura4Tri);


        int [] idLeitura4Tri = new int[]{ 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,
                34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,
                72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,
                108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,128,129,130,131,132,133,134,135,136,
                137,138,139,140,141,142,143,144,145,146,147,148,149,150,151,152,153,154,155,156,157,158,159,160,161,162,163,164,165,
                166,167,168,169,170,171,172,173,174,175,176,177,178,179,180,181,182,183,184,185,186,187,188,189,190,191,192,193,194,
                195,196,197,198,199,200,201,202,203,204,205,206,207,208,209,210,211,212,213,214,215,216,217,218,219,220,222,223,224,
                225,226,227,228,229,230,231,232,233,234,235,236,237,238,239,240,241,242,243,244,245,246,247,248,249,250,251,252,253,
                254,255,256,257,258,259,260,261,262,263,264,265,266,267,268,269,270,271,272,273,274,275,276,277,278,279,280,281,282,
                283,284,285,286,371 };

        int [] mesLeitura4Tri= new int[]{ 10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,
                10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,
                10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,
                10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,
                11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,
                11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,12,12,12,
                12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,
                12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,11 };

        int [] diaLeitura4Tri = new int[]{1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5,6,6,6,6,7,7,7,7,8,8,8,8,9,9,9,9,9,10,10,10,10,
                11,11,11,11,12,12,12,12,13,13,13,13,14,14,14,14,15,15,15,15,16,16,16,16,17,17,17,17,18,18,18,18,19,19,19,19,20,20,
                20,20,21,21,21,21,22,22,22,22,23,23,23,23,24,24,24,24,25,25,25,25,26,26,26,26,27,27,27,27,28,28,28,28,28,29,29,29,30,
                30,30,30,31,31,31,31,1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5,6,6,6,6,7,7,7,7,8,8,8,8,9,9,10,10,11,11,11,12,12,12,13,
                13,13,14,14,14,15,15,15,16,16,16,17,17,18,18,18,19,19,20,20,21,21,21,22,22,22,23,23,23,24,24,24,25,25,25,26,26,26,27,
                27,27,28,28,28,29,29,29,30,30,30,1,1,2,3,3,3,4,4,4,5,5,6,6,7,7,8,8,9,9,10,10,11,11,12,12,13,13,14,14,15,15,16,16,17,
                17,18,18,18,19,19,20,20,21,21,21,21,23,23,23,23,24,24,25,25,26,26,27,27,28,28,29,29,30,30,31,31,31,26
        };

        int [] bookIdLeitura4Tri = new int[]{23,23,23,19,23,23,23,19,23,23,23,19,23,23,23,19,23,23,23,19,23,23,23,19,23,23,23,23,29,
                23,23,23,19,23,23,23,19,23,23,23,19,23,23,23,19,23,23,23,19,23,23,24,24,24,24,24,20,24,24,24,20,24,24,24,20,24,24,24,
                20,24,24,24,20,24,24,24,20,24,24,24,20,24,24,24,20,24,24,24,20,24,24,24,20,24,24,24,20,24,24,24,20,24,24,24,24,20,24,
                24,20,24,24,24,20,24,24,24,20,24,24,25,25,25,25,25,20,26,26,26,20,26,26,26,20,26,26,26,20,26,26,26,20,26,26,26,20,26,
                26,26,20,26,26,26,20,26,26,26,20,26,26,26,20,26,26,20,26,26,20,26,26,20,26,26,20,26,26,20,26,26,21,26,26,26,26,26,26,
                26,26,26,27,27,21,27,27,21,27,27,21,27,27,21,27,27,21,27,27,21,28,28,21,28,28,21,28,28,21,28,28,21,28,28,28,28,28,28,
                29,29,29,30,22,30,22,30,22,30,22,30,22,30,22,30,22,30,30,31,22,32,32,32,32,33,33,33,33,33,33,33,34,34,34,35,35,35,36,
                36,36,37,37,38,38,38,38,38,38,38,38,38,38,38,38,38,38,39,39,39,39,21 };

        int [] capituloLeitura4Tri = new int[]{28,29,30,139,31,32,33,140,34,35,36,141,37,38,39,142,40,41,42,143,43,44,45,144,46,47,48,
                49,145,50,51,52,146,53,54,55,147,56,57,58,148,59,60,61,149,62,63,64,150,65,66,1,2,3,4,5,1,6,7,8,2,9,10,11,3,12,13,14,
                4,15,16,17,5,18,19,20,6,21,22,23,7,24,25,26,8,27,28,29,9,30,31,32,10,33,34,35,11,36,37,38,12,39,40,41,42,13,43,44,14,
                45,46,47,15,48,49,50,16,51,52,1,2,3,4,5,17,1,2,3,18,4,5,6,19,7,8,9,20,10,11,12,21,13,14,15,22,16,17,18,23,19,20,21,24,
                22,23,24,25,25,26,27,26,28,29,27,30,31,28,32,33,29,34,35,30,36,37,31,38,39,1,40,41,42,43,44,45,46,47,48,1,2,2,3,4,3,5,
                6,4,7,8,5,9,10,6,11,12,7,1,2,8,3,4,9,5,6,10,7,8,11,9,10,11,12,13,14,1,2,3,1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,9,1,8,1,2,3,4,
                1,2,3,4,5,6,7,1,2,3,1,2,3,1,2,3,1,2,1,2,3,4,5,6,7,8,9,10,11,12,13,14,1,2,3,4,8 };

        String [] tituloLeitura4Tri = new String[]{"Isaías 28","Isaías 29","Isaías 30","Salmos 139","Isaías 31","Isaías 32",
                "Isaías 33","Salmos 140","Isaías 34","Isaías 35","Isaías 36","Salmos 141","Isaías 37","Isaías 38","Isaías 39",
                "Salmos 142","Isaías 40","Isaías 41","Isaías 42","Salmos 143","Isaías 43","Isaías 44","Isaías 45","Salmos 144",
                "Isaías 46","Isaías 47","Isaías 48","Isaías 49","Salmos 145","Isaías 50","Isaías 51","Isaías 52","Salmos 146",
                "Isaías 53","Isaías 54","Isaías 55","Salmos 147","Isaías 56","Isaías 57","Isaías 58","Salmos 148","Isaías 59",
                "Isaías 60","Isaías 61","Salmos 149","Isaías 62","Isaías 63","Isaías 64","Salmos 150","Isaías 65","Isaías 66",
                "Jeremias 1","Jeremias 2","Jeremias 3","Jeremias 4","Jeremias 5","Provérbios 1","Jeremias 6","Jeremias 7",
                "Jeremias 8","Provérbios 2","Jeremias 9","Jeremias 10","Jeremias 11","Provérbios 3","Jeremias 12","Jeremias 13",
                "Jeremias 14","Provérbios 4","Jeremias 15","Jeremias 16","Jeremias 17","Provérbios 5","Jeremias 18","Jeremias 19",
                "Jeremias 20","Provérbios 6","Jeremias 21","Jeremias 22","Jeremias 23","Provérbios 7","Jeremias 24","Jeremias 25",
                "Jeremias 26","Provérbios 8","Jeremias 27","Jeremias 28","Jeremias 29","Provérbios 9","Jeremias 30","Jeremias 31",
                "Jeremias 32","Provérbios 10","Jeremias 33","Jeremias 34","Jeremias 35","Provérbios 11","Jeremias 36","Jeremias 37",
                "Jeremias 38","Provérbios 12","Jeremias 39","Jeremias 40","Jeremias 41","Jeremias 42","Provérbios 13","Jeremias 43",
                "Jeremias 44","Provérbios 14","Jeremias 45","Jeremias 46","Jeremias 47","Provérbios 15","Jeremias 48","Jeremias 49",
                "Jeremias 50","Provérbios 16","Jeremias 51","Jeremias 52","Lamentações 1","Lamentações 2","Lamentações 3",
                "Lamentações 4","Lamentações 5","Provérbios 17","Ezequiel 1","Ezequiel 2","Ezequiel 3","Provérbios 18","Ezequiel 4",
                "Ezequiel 5","Ezequiel 6","Provérbios 19","Ezequiel 7","Ezequiel 8","Ezequiel 9","Provérbios 20","Ezequiel 10",
                "Ezequiel 11","Ezequiel 12","Provérbios 21","Ezequiel 13","Ezequiel 14","Ezequiel 15","Provérbios 22","Ezequiel 16",
                "Ezequiel 17","Ezequiel 18","Provérbios 23","Ezequiel 19","Ezequiel 20","Ezequiel 21","Provérbios 24","Ezequiel 22",
                "Ezequiel 23","Ezequiel 24","Provérbios 25","Ezequiel 25","Ezequiel 26","Ezequiel 27","Provérbios 26","Ezequiel 28",
                "Ezequiel 29","Provérbios 27","Ezequiel 30","Ezequiel 31","Provérbios 28","Ezequiel 32","Ezequiel 33","Provérbios 29",
                "Ezequiel 34","Ezequiel 35","Provérbios 30","Ezequiel 36","Ezequiel 37","Provérbios 31","Ezequiel 38","Ezequiel 39",
                "Eclesiastes 1","Ezequiel 40","Ezequiel 41","Ezequiel 42","Ezequiel 43","Ezequiel 44","Ezequiel 45","Ezequiel 46",
                "Ezequiel 47","Ezequiel 48","Daniel 1","Daniel 2","Eclesiastes 2","Daniel 3","Daniel 4","Eclesiastes 3","Daniel 5",
                "Daniel 6","Eclesiastes 4","Daniel 7","Daniel 8","Eclesiastes 5","Daniel 9","Daniel 10","Eclesiastes 6","Daniel 11",
                "Daniel 12","Eclesiastes 7","Oséias 1","Oséias 2","Eclesiastes 9","Oséias 3","Oséias 4","Eclesiastes 10","Oséias 5",
                "Oséias 6","Eclesiastes 11","Oséias 7","Oséias 8","Eclesiastes 12","Oséias 9","Oséias 10","Oséias 11","Oséias 12",
                "Oséias 13","Oséias 14","Joel 1","Joel 2","Joel 3","Amós 1","Cânticos 1 ","Amós 2","Cânticos 2","Amós 3","Cânticos 3",
                "Amós 4","Cânticos 4","Amós 5","Cânticos 5","Amós 6","Cânticos 6","Amós 7","Cânticos 7","Amós 8","Amós 9","Obadias",
                "Cânticos 8","Jonas 1","Jonas 2","Jonas 3","Jonas 4","Miquéias 1","Miquéias 2","Miquéias 3","Miquéias 4","Miquéias 5",
                "Miquéias 6","Miquéias 7","Naum 1","Naum 2","Naum 3","Habacuque 1","Habacuque 2","Habacuque 3","Sofonias 1","Sofonias 2",
                "Sofonias 3","Ageu 1","Ageu 2","Zacarias 1","Zacarias 2","Zacarias 3","Zacarias 4","Zacarias 5","Zacarias 6","Zacarias 7",
                "Zacarias 8","Zacarias 9","Zacarias 10","Zacarias 11","Zacarias 12","Zacarias 13","Zacarias 14","Malaquias 1",
                "Malaquias 2","Malaquias 3","Malaquias 4","Eclesiastes 8" };


        ContentValues contentValues = new ContentValues();

        for(int i = 0; i < idLeitura4Tri.length; i++){


            contentValues.put("id", idLeitura4Tri[i]);
            contentValues.put("mes", mesLeitura4Tri[i]);
            contentValues.put("dia", diaLeitura4Tri[i]);
            contentValues.put("book_id", bookIdLeitura4Tri[i]);
            contentValues.put("capitulo", capituloLeitura4Tri[i]);
            contentValues.put("titulo", tituloLeitura4Tri[i]);

            db.insert("leitura_4_tri", null,contentValues);

        }

    }

    private void updateTableLeituraReferencias(SQLiteDatabase db){


        String dropTableLeitura4Tri = "DROP IF EXISTS leitura_referencias";

        String createTabelaLeitura4Tri = "CREATE TABLE leitura_referencias (\n" +
                "    id         INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    mes        INTEGER NOT NULL,\n" +
                "    dia        INTEGER NOT NULL,\n" +
                "    referencia TEXT    NOT NULL\n" +
                ");\n";

        db.execSQL(dropTableLeitura4Tri);
        db.execSQL(createTabelaLeitura4Tri);

        int [] idLeituraReferencias = new int[]{ 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,
                33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,
                74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108,109,110,
                111,112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,128,129,130,131,132,133,134,135,136,137,138,139,140,
                141,142,143,144,145,146,147,148,149,150,151,152,153,154,155,156,157,158,159,160,161,162,163,164,165,166,167,168,169,170,
                171,172,173,174,175,176,177,178,179,180,181,182,183,184,185,186,187,188,189,190,191,192,193,194,195,196,197,198,199,200,
                201,202,203,204,205,206,207,208,209,210,211,212,213,214,215,216,217,218,219,220,221,222,223,224,225,226,227,228,229,230,
                231,232,233,234,235,236,237,238,239,240,241,242,243,244,245,246,247,248,249,250,251,252,253,254,255,256,257,258,259,260,
                261,262,263,264,265,266,267,268,269,270,271,272,273,274,275,276,277,278,279,280,281,282,283,284,285,286,287,288,289,290,
                291,292,293,294,295,296,297,298,299,300,301,302,303,304,305,306,307,308,309,310,311,312,313,314,315,316,317,318,319,320,
                321,322,323,324,325,326,327,328,329,330,331,332,333,334,335,336,337,338,339,340,341,342,343,344,345,346,347,348,349,350,
                351,352,353,354,355,356,357,358,359,360,361,362,363,364,365 };

        int [] mesLeituraReferencia = new int[]{ 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,2,
                2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,4,4,4,4,4,4,4,4,4,4,
                4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,6,6,6,6,6,6,6,6,6,
                6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,8,8,8,8,8,8,8,
                8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,10,10,10,
                10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,11,11,11,11,11,11,11,11,11,11,11,
                11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,
                12,12,12,12,12,12,12,12,12,12,12 };

        int [] diaLeituraReferencia = new int[]{ 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,
                1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,
                18,19,20,21,22,23,24,25,26,27,28,29,30,31,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,
                29,30,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,1,2,3,4,5,6,7,8,9,10,11,12,
                13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,
                25,26,27,28,29,30,31,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,1,2,3,4,5,6,
                7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,
                20,21,22,23,24,25,26,27,28,29,30,31,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,
                1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31 };

        String [] tituloLeituraReferencia = new String[]{ "Mateus 1 a 2","Mateus 3 a 4","Mateus 5 a 6","Mateus 7 a 8","Mateus 9 a 10",
                "Mateus 11 a 12","Mateus 13 a 14","Mateus 15 a 16","Mateus 17 a 18","Mateus 19 a 20","Mateus 21 a 22","Mateus 23 a 24",
                "Mateus 25 a 26","Mateus 27 a 28","Marcos 1 a 2","Marcos 3 a 4","Marcos 5 a 6","Marcos 7 a 8","Marcos 9 a 10",
                "Marcos 11 a 12","Marcos 13 a 14","Marcos 15 a 16","Lucas 1 a 2","Lucas 3 a 4","Lucas 5 a 6","Lucas 7 a 8",
                "Lucas 9 a 10","Lucas 11 a 12","Lucas 13 a 14","Lucas 15 a 16","Lucas 17 a 18","Lucas 19 a 20","Lucas 21 a 22",
                "Lucas 23 a 24","João 1 a 2","João 3 a 4","João 5 a 6","João 7 a 8","João 9 a 10","João 11 a 12","João 13 a 14",
                "João 15 a 16","João 17 a 18","João 19 a 20","João 21 a Atos 1","Atos 2 a 3","Atos 4 a 5","Atos 6 a 7",
                "Atos 8 a 9","Atos 10 a 11","Atos 12 a 13","Atos 14 a 15","Atos 16 a 17","Atos 18 a 19","Atos 20 a 21",
                "Atos 22 a 23","Atos 24 a 25","Atos 26 a 27","Atos 28 a Romanos 1","Romanos 2 a 3","Romanos 4 a 5","Romanos 6 a 7",
                "Romanos 8 a 9","Romanos 10 a 11","Romanos 12 a 13","Romanos 14 a 15","Romanos 16 a I Coríntios 1",
                "I Coríntios 2 a 3","I Coríntios 4 a 5","I Coríntios 6 a 7","I Coríntios 8 a 9","I Coríntios 10 a 11",
                "I Coríntios 12 a 13","I Coríntios 14 a 15","I Coríntios 16 a II Coríntios 1","II Coríntios 2 a 3",
                "II Coríntios 4 a 5","II Coríntios 6 a 7","II Coríntios 8 a 9","II Coríntios 10 a 11","II Coríntios 12 a Gálatas 1",
                "Gálatas 2 a 3","Gálatas 4 a 5","Gálatas 6 a Efésios 1","Efésios 2 a 3","Efésios 4 a 5",
                "Efésios 6 a Filipenses 1","Filipenses 2 a 3","Colossenses 1 a 2","Colossenses 3 a I Tessalonicenses 1",
                "I Tessalonicenses 2 a 3","I Tessalonicenses 4 a 5","II Tessalonicenses 1 a 2",
                "II Tessalonicenses 3 a I Timóteo 3","I Timóteo 4 a II Timóteo 1","II Timóteo 2 a 3","II Timóteo 4 a Tito 1",
                "Tito 2 a 3","Filemom 1 a Hebreus 1","Hebreus 2 a 3","Hebreus 4 a 5","Hebreus 6 a 7","Hebreus 8 a 9",
                "Hebreus 10 a 12","Hebreus 13 a Tiago 3","Tiago 4 a I Pedro 2","I Pedro 3 a II Pedro 1","II Pedro 2 a I João 2",
                "I João 3 a II João","III João, Judas e Apocalipse 1 e 2","Apocalipse 3 a 6","Apocalipse 7 a 10",
                "Apocalipse 11 a 14","Apocalipse 15 a 18","Apocalipse 19 a 22","Gênesis 1 a 3 e Salmo 1","Gênesis 4 a 6 e Salmo 2",
                "Gênesis 7 a 9 e Salmo 3","Gênesis 10 a 13 e Salmo 4","Gênesis 14 a 15 e Salmo 5","Gênesis 16 a 18 e Salmo 6",
                "Gênesis 19 a 21 e Salmo 7","Gênesis 22 a 24 e Salmo 8","Gênesis 25 a 27 e Salmo 9","Gênesis 28 a 30 e Salmo 10",
                "Gênesis 31 a 33 e Salmo 11","Gênesis 34 a 36 e Salmo 12","Gênesis 37 a 39 e Salmo 13","Gênesis 40 a 42 e Salmo 14",
                "Gênesis 43 a 45 e Salmo 15","Gênesis 47 a 49 e Salmo 16","Gênesis 50 a Êxodo 2 e Salmo 17","Êxodo 3 a 5 e Salmo 18",
                "Êxodo 6 a 8 e Salmo 19","Êxodo 9 a 11 e Salmo 20","Êxodo 12 a 14 e Salmo 21","Êxodo 15 a 17 e Salmo 22",
                "Êxodo 18 a 20 e Salmo 23","Êxodo 21 a 23 e Salmo 24","Êxodo 24 a 26 e Salmo 25","Êxodo 27 a 29 e Salmo 26",
                "Êxodo 30 a 32 e Salmo 27","Êxodo 33 a 35 e Salmo 28","Êxodo 36 a 38 e Salmo 29","Êxodo 39 a Levítico 1 e Salmo 30",
                "Levítico 2 a 4 e Salmo 31","Levítico 5 a 7 e Salmo 32","Levítico 8 a 10 e Salmo 33","Levítico 11 a 13 e Salmo 34",
                "Levítico 14 a 16 e Salmo 35","Levítico 17 a 19 e Salmo 36","Levítico 20 a 22 e Salmo 37",
                "Levítico 23 a 25 e Salmo 38","Levítico 26 a 27 e Salmo 39","Números 1 a 3 e Salmo 40","Números 4 a 6 e Salmo 41",
                "Números 7 a 9 e Salmo 42","Números 10 a 12 e Salmo 43","Números 13 a 15 e Salmo 44","Números 16 a 18 e Salmo 45",
                "Números 19 a 21 e Salmo 46","Números 22 a 24 e Salmo 47","Números 25 a 27 e Salmo 48","Números 28 a 30 e Salmo 49",
                "Números 31 a 33 e Salmo 50","Números 34 a 36 e Salmo 51","Deuteronômio 1 a 3 e Salmo 52",
                "Deuteronômio 4 a 6 e Salmo 53","Deuteronômio 7 a 9 e Salmo 54","Deuteronômio 10 a 12 e Salmo 55",
                "Deuteronômio 13 a 15 e Salmo 56","Deuteronômio 16 a 18 e Salmo 57","Deuteronômio 19 a 21 e Salmo 58",
                "Deuteronômio 22 a 24 e Salmo 59","Deuteronômio 25 a 27 e Salmo 60","Deuteronômio 28 a 30 e Salmo 61",
                "Deuteronômio 31 a 34","Josué 1 a 3 e Salmo 62","Josué 4 a 6 e Salmo 63","Josué 7 a 9 e Salmo 64",
                "Josué 10 a 12 e Salmo 65","Josué 13 a 15 e Salmo 66","Josué 16 a 18 e Salmo 67","Josué 19 a 21 e Salmo 68",
                "Josué 22 a 24 e Salmo 60","Juízes 1 a 3 e Salmo 70","Juízes 4 a 6 e Salmo 71","Juízes 7 a 9 e Salmo 72",
                "Juízes 10 a 12 e Salmo 73","Juízes 13 a 15 e Salmo 74","Juízes 16 a 18 e Salmo 75","Juízes 19 a 21 e Salmo 76",
                "Rute 1 a 4","I Samuel 1 a 3 e Salmo 77","I Samuel 4 a 6 e Salmo 78","I Samuel 7 a 9 e Salmo 79",
                "I Samuel 10 a 12 e Salmo 80","I Samuel 13 a 15 e Salmo 81","I Samuel 16 a 18 e Salmo 82",
                "I Samuel 19 a 21 e Salmo 83","I Samuel 22 a 24 e Salmo 84","I Samuel 25 a 27 e Salmo 85",
                "I Samuel 28 a 31","II Samuel 1 a 3 e Salmo 86","II Samuel 4 a 6 e Salmo 87","II Samuel 7 a 9 e Salmo 88",
                "II Samuel 10 a 12 e Salmo 89","II Samuel 13 a 15 e Salmo 90","II Samuel 16 a 18 e Salmo 91",
                "II Samuel 19 a 21 e Salmo 92","II Samuel 22 a 24 e Salmo 93","I Reis 1 a 3 e Salmo 94","I Reis 4 a 6 e Salmo 95",
                "I Reis 7 a 9 e Salmo 96","I Reis 10 a 12 e Salmo 97","I Reis 13 a 15 e Salmo 98","I Reis 16 a 18 e Salmo 99",
                "I Reis 19 a 22","II Reis 1 a 4 e Salmo 100","II Reis 5 a 7 e Salmo 101","II Reis 8 a 10 e Salmo 102",
                "II Reis 11 a 13 e Salmo 103","II Reis 14 a 16 e Salmo 104","II Reis 17 a 19 e Salmo 105",
                "II Reis 20 a 22 e Salmo 106","II Reis 23 a 25 e Salmo 107","I Crônicas 1 a 3 e Salmo 108",
                "I Crônicas 4 a 6 e Salmo 109","I Crônicas 7 a 9 e Salmo 110","I Crônicas 10 a 12 e Salmo 111",
                "I Crônicas 13 a 15 e Salmo 112","I Crônicas 16 a 18 e Salmo 113","I Crônicas 19 a 21 e Salmo 114",
                "I Crônicas 22 a 24 e Salmo 115","I Crônicas 25 a 27 e Salmo 116","I Crônicas 28 e II Crônicas 1 a 2",
                "II Crônicas 3 a 5 e Sl 117 e Sl 118","II Crônicas 6 e Salmo 119","II Crônicas 7 a 9 e Salmo 120",
                "II Crônicas 10 a 12 e Salmo 121","II Crônicas 13 a 15 e Salmo 122","II Crônicas 16 a 18 e Salmo 123",
                "II Crônicas 19 a 21 e Salmo 124","II Crônicas 22 a 24 e Salmo 125","II Crônicas 25 a 27 e Salmo 126",
                "II Crônicas 28 a 30 e Salmo 127","II Crônicas 31 a 33 e Salmo 128","II Crônicas 34 a 36 e Salmo 129",
                "Esdras 1 a 5","Esdras 6 a 10","Neemias 1 a 3","Neemias 4 a 6","Neemias 7 a 9","Neemias 10 a 13",
                "Ester 1 a 6","Ester 7 a 10","Jó 1 a 5","Jó 6 a 10","Jó 11 a 15","Jó 16 a 20","Jó 21 a 25","Jó 26 a 30",
                "Jó 31 a 35","Jó 36 a 40","Isaías 1 a 3 e Salmo 130","Isaías 4 a 6 e Salmo 131","Isaías 7 a 9 e Salmo 132",
                "Isaías 10 a 12 e Salmo 133","Isaías 13 a 15 e Salmo 134","Isaías 16 a 18 e Salmo 135","Isaías 19 a 21 e Salmo 136",
                "Isaías 22 a 24 e Salmo 137","Isaías 25 a 27 e Salmo 138","Isaías 28 a 30 e Salmo 139","Isaías 31 a 33 e Salmo 140",
                "Isaías 34 a 36 e Salmo 141","Isaías 37 a 39 e Salmo 142","Isaías 40 a 42 e Salmo 143","Isaías 43 a 45 e Salmo 144",
                "Isaías 46 a 49 e Salmo 145","Isaías 50 a 52 e Salmo 146","Isaías 53 a 55 e Salmo 147","Isaías 56 a 58 e Salmo 148",
                "Isaías 59 a 61 e Salmo 149","Isaías 62 a 64 e Salmo 150","Isaías 65 e 66 e Jeremias 1 e 2",
                "Jeremias 3 a 5 e Provérbios 1","Jeremias 6 a 8 e Provérbios 2","Jeremias 9 a 11 e Provérbios 3",
                "Jeremias 12 a 14 e Provérbios 4","Jeremias 15 a 17 e Provérbios 5","Jeremias 18 a 20 e Provérbios 6",
                "Jeremias 21 a 23 e Provérbios 7","Jeremias 24 a 26 e Provérbios 8","Jeremias 27 a 29 e Provérbios 9",
                "Jeremias 30 a 32 e Provérbios 10","Jeremias 33 a 35 e Provérbios 11","Jeremias 36 a 38 e Provérbios 12",
                "Jeremias 39 a 42 e Provérbios 13","Jeremias 43 a 44 e Provérbios 14","Jeremias 45 a 47 e Provérbios 15",
                "Jeremias 48 a 50 e Provérbios 16","Jeremias 51 e 52 e Lm 1 e 2","Lamentaçõs 3 a 5 e Provérbios 17",
                "Ezequiel 1 a 3 e Provérbios 18","Ezequiel 4 a 6 e Provérbios 19","Ezequiel 7 a 9 e Provérbios 20",
                "Ezequiel 10 a 12 e Provérbios 21","Ezequiel 13 a 15 e Provérbios 22","Ezequiel 16 a 18 e Provérbios 23",
                "Ezequiel 19 a 21 e Provérbios 24","Ezequiel 22 a 24 e Provérbios 25","Ezequiel 25 a 26",
                "Ezequiel 27 e Provérbios 26","Ezequiel 28 a 29 e Provérbios 27","Ezequiel 30 a 31 e Provérbios 28",
                "Ezequiel 32 a 33 e Provérbios 29","Ezequiel 34 a 35 e Provérbios 30","Ezequiel 36 a 37 e Provérbios 31",
                "Ezequiel 38 a 39 e Eclesiastes 1","Ezequiel 40 e 41","Ezequiel 42 e 44","Ezequiel 45 e 46","Ezequiel 47 e 48",
                "Daniel 1 a 2 e Eclesiastes 2","Daniel 3 a 4 e Eclesiastes 3","Daniel 5 a 6 e Eclesiastes 4",
                "Daniel 7 a 8 e Eclesiastes 5","Daniel 9 a 10 e Eclesiastes 6","Daniel 11 a 12 e Eclesiastes 7",
                "Oséias 1 a 2 e Eclesiastes 8","Oséias 3 a 4 e Eclesiastes 9","Oséias 5 a 6 e Eclesiastes 10",
                "Oséias 7 a 8 e Eclesiastes 11","Oséias 9 a 10 e Eclesiastes 12","Oséias 11 a 12 ","Oséias 13 a 14",
                "Joel 1 a 3","Amós 1 e Cânticos 1 ","Amós 2 e Cânticos 2 ","Amós 3 e Cânticos 3 ","Amós 4 e Cânticos 4 ",
                "Amós 5 e Cânticos 5 ","Amós 6 e Cânticos 6 ","Amós 7 e Cânticos 7 ","Amós 8 e 9","Obadias e Cânticos 8",
                "Jonas 1 e 2","Jonas 3 e 4","Miquéias 1 e 2","Miquéias 3 e 4","Miquéias 5 e 7","Naum 1 a 2",
                "Naum 3 e Habacuque 1","Habacuque 2 e 3","Sofonias 1 e 2","Sofonoias 3 e Zacarias 1","Zacarias 2 e 3",
                "Zacarias 4 e 5","Zacarias 6 e 7","Zacarias 8 e 9","Zacarias 10 e 11","Zacarias 12 e 13",
                "Zacarias 14 e Malaquias 1","Malaquias 2 a 4"};



        ContentValues contentValues = new ContentValues();

        for(int i = 0; i < idLeituraReferencias.length; i++){


            contentValues.put("id", idLeituraReferencias[i]);
            contentValues.put("mes", diaLeituraReferencia[i]);
            contentValues.put("dia", mesLeituraReferencia[i]);
            contentValues.put("referencia", tituloLeituraReferencia[i]);

            db.insert("leitura_referencias", null,contentValues);

        }


    }

}