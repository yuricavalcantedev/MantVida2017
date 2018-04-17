package com.heavendevelopment.mantvida20182.Activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.heavendevelopment.mantvida20182.Dominio.Meta;
import com.heavendevelopment.mantvida20182.R;
import com.heavendevelopment.mantvida20182.Service.MetaService;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VisualizarMetaProjetoVida extends AppCompatActivity {

    private int tvEscolherData = 1;
    String arrayCategorias [];
    Context context;
    private int idMeta;
    private int novaCategoria;
    ImageView imgCategoria;
    TextView tvTituloCategria;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_meta_projeto_vida);

        context = this;
        arrayCategorias = getResources().getStringArray(R.array.array_categorias);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Ver Meta");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        idMeta = bundle.getInt("idMeta");

        MetaService metaService = new MetaService(context);
        Meta meta = metaService.getMetaById(idMeta);

        //depois implementar a função do usuário clicar na imagem e aparecer um alertdialog personalizado
        //com as imagens e checkbox dos tipos de categoria que existem

        novaCategoria = meta.getIdCategoria();

        imgCategoria = (ImageView) findViewById(R.id.img_categoria_visualizar_meta);
        tvTituloCategria = (TextView) findViewById(R.id.tv_titulo_visualizar_meta);
        TextView tvDataCriacaoMeta = (TextView) findViewById(R.id.tv_data_criacao_visualizar_meta);

        imgCategoria.setImageResource(chooseImg96Px(meta.getIdCategoria()));
        tvTituloCategria.setText(chooseTitleMeta(meta.getIdCategoria()));
        tvDataCriacaoMeta.setText("Data de Criação : "+meta.getDataCriacao());

        TextInputLayout tilTitulo = (TextInputLayout) findViewById(R.id.til_titulo_visualizar_meta);
        TextInputLayout tilComo = (TextInputLayout) findViewById(R.id.til_como_visualizar_meta);
        TextInputLayout tilObjetivo = (TextInputLayout) findViewById(R.id.til_objetivo_visualizar_meta);
        TextInputLayout tilDataInicio = (TextInputLayout) findViewById(R.id.til_dataInicio_visualizar_meta);
        TextInputLayout tilDataConclusao = (TextInputLayout) findViewById(R.id.til_dataConclusao_visualizar_meta);

        tilTitulo.getEditText().setText(meta.getTitulo());
        tilComo.getEditText().setText(meta.getComo());
        tilObjetivo.getEditText().setText(meta.getObjetivo());
        tilDataInicio.getEditText().setText(meta.getDataInicio());
        tilDataConclusao.getEditText().setText(meta.getDataConclusao());

        imgCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                criarDialogAtualizarCategoria();
            }
        });

        tilDataInicio.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvEscolherData = 1;
                escolherData(v);
            }
        });

        tilDataConclusao.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvEscolherData = 2;
                escolherData(v);
            }
        });


    }


    //Métodos auxiliares chamados quando o usuário clica para escolher a data das metas
    public void escolherData(View v) {

        Calendar calendarInstance = Calendar.getInstance();
        int mes = calendarInstance.get(Calendar.MONTH);
        int dia = calendarInstance.get(Calendar.DAY_OF_MONTH);
        int ano = calendarInstance.get(Calendar.YEAR);

        //acrescento um mês caso seja a data de conclusão só para não ficar a mesma data
        if(tvEscolherData == 2)
            mes += 1;

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, datePickerListener, ano, mes, dia);
        datePickerDialog.show();


    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            //0 - TextView ligada ao Início.
            //1 - TextView ligada à Conclusão.

            try{

                EditText tvData = null;


                //o mês que o usuário seleciona, vem com o valor -1 do mês selecionado
                //porque começa em 0.

                selectedMonth += 1;

                //Se for igual a 1 é porque a textView é a do ínicio
                if (tvEscolherData == 1) {
                    tvData = ((TextInputLayout) findViewById(R.id.til_dataInicio_visualizar_meta)).getEditText();
                }else {
                    tvData = ((TextInputLayout) findViewById(R.id.til_dataConclusao_visualizar_meta)).getEditText();
                }
                String day = "", month = "";

                //se o dia ou mês selecionados forem menor do que 10, eu adiciono um 0 na frente só para ficar mais bonito
                //o formato da data

                if (selectedDay < 10)
                    day = "0" + selectedDay;
                else
                    day = selectedDay+"";

                if (selectedMonth < 10)
                    month = "0" + selectedMonth;
                else
                    month = selectedMonth+"";

                String dataSelecionada = day + "/" + month + "/" + selectedYear;

                tvData.setText(dataSelecionada);

            }catch(Exception ex ){
                Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();

            }

        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_visualizar_meta, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == android.R.id.home){
            finish();
        }else if (id == R.id.ic_menu_save) {

            atualizarMeta();

        }else if (id == R.id.ic_menu_delete){

            deletarMeta();

        }

        return super.onOptionsItemSelected(item);
    }

    private void criarDialogAtualizarCategoria(){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("Selecione a nova categoria");

        builder.setSingleChoiceItems(arrayCategorias, -1, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {

                novaCategoria = item + 1;
                imgCategoria.setImageResource(chooseImg96Px(novaCategoria));
                tvTituloCategria.setText(chooseTitleMeta(novaCategoria));

                Toast.makeText(context, "Categoria atualizada", Toast.LENGTH_SHORT).show();

                dialog.dismiss();
            }


        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }

    private void atualizarMeta(){


        TextInputLayout tilTitulo = (TextInputLayout) findViewById(R.id.til_titulo_visualizar_meta);
        TextInputLayout tilComo = (TextInputLayout) findViewById(R.id.til_como_visualizar_meta);
        TextInputLayout tilObjetivo = (TextInputLayout) findViewById(R.id.til_objetivo_visualizar_meta);
        TextInputLayout tilDataInicio = (TextInputLayout) findViewById(R.id.til_dataInicio_visualizar_meta);
        TextInputLayout tilDataConclusao = (TextInputLayout) findViewById(R.id.til_dataConclusao_visualizar_meta);

        String titulo = tilTitulo.getEditText().getText().toString();
        String como = tilComo.getEditText().getText().toString();
        String objetivo = tilObjetivo.getEditText().getText().toString();
        String dataInicio = tilDataInicio.getEditText().getText().toString();
        String dataConclusao = tilDataConclusao.getEditText().getText().toString();

        //falta pegar a parte da categoria

        GregorianCalendar calendar = new GregorianCalendar();
        int day = calendar.get(GregorianCalendar.DAY_OF_MONTH);
        int month = calendar.get(GregorianCalendar.MONTH) + 1;
        int year = calendar.get(GregorianCalendar.YEAR);

        //talvez lá para frente, pegar também a informação de quando foi alterado
        //e não somente a a informação da data de criação

        String dataHoje = day + "/" + month + "/" + year;

        //checa se as informações são válidas

        Pattern regexDataInicial = Pattern.compile("\\d{2}/\\d{2}/\\d{2,4}");
        Matcher matcherDataInicial = regexDataInicial.matcher(dataInicio);

        Pattern regexDataConclusao = Pattern.compile("\\d{2}/\\d{2}/\\d{2,4}");
        Matcher matcherDataConclusao = regexDataConclusao.matcher(dataConclusao);

        boolean camposValidados = true;

        if(!matcherDataInicial.matches()){
            tilDataInicio.setErrorEnabled(true);
            tilDataInicio.setError("Data inválida. Ex : " + dataHoje);
            camposValidados = false;
        }else{
            tilDataInicio.setErrorEnabled(false);
        }

        if(!matcherDataConclusao.matches()){
            tilDataConclusao.setError("Data inválida. Ex : " + dataHoje);
            camposValidados = false;

        }else{
            tilDataConclusao.setErrorEnabled(false);
        }

        //falta comparar se a data de conclusão é menor do que a do início.

        if(titulo.length() < 5){
            tilTitulo.setError("Este campo deve ter no mínimo 5 letras");
            camposValidados = false;
        }else{
            tilTitulo.setErrorEnabled(false);
        }

        if(como.length() < 5){
            tilComo.setError("Este campo deve ter no mínimo 5 letras");
            camposValidados = false;
        }else{
            tilComo.setErrorEnabled(false);
        }

        if(objetivo.length() < 5){
            tilObjetivo.setError("Este campo deve ter no mínimo 5 letras");
            camposValidados = false;
        }else{
            tilObjetivo.setErrorEnabled(false);
        }


        if(camposValidados){

            MetaService metaService = new MetaService(this);

            Meta meta = metaService.getMetaById(idMeta);
            meta.setIdCategoria(novaCategoria);
            meta.setTitulo(titulo);
            meta.setComo(como);
            meta.setObjetivo(objetivo);
            meta.setDataInicio(dataInicio);
            meta.setDataConclusao(dataConclusao);

            //falta setar a categoria

            boolean metaAtualizada = metaService.atualizarMeta(meta);

            if(metaAtualizada){
                Toast.makeText(this, "Meta atualizada com sucesso.", Toast.LENGTH_SHORT).show();

                //fecha a activity
                finish();

            }else{

                Toast.makeText(this, "Ocorreu um erro. Tente novamente daqui alguns segundos", Toast.LENGTH_SHORT).show();

            }
        }

    }

    private void deletarMeta(){

        final MetaService metaService = new MetaService(context);
        final Meta metaExcluir = metaService.getMetaById(idMeta);

        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle("Excluir Meta")
                .setMessage("Você realmente deseja excluir a meta : " + metaExcluir.getTitulo())
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                        CoordinatorLayout coordinatorLayoutMeta = (CoordinatorLayout) findViewById(R.id.activity_visualizar_meta_projeto_vida);

                        metaService.deletarMeta(idMeta);

                        Snackbar.make(coordinatorLayoutMeta, "Meta excluída com sucesso", Snackbar.LENGTH_LONG).show();

                        finish();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }

    private int chooseImg96Px(int categoria){

        switch (categoria){

            case 1 : return R.drawable.ic_familia_96px;
            case 2 : return R.drawable.ic_ministerio_96px;
            case 3 : return R.drawable.ic_formacao_96px;
            case 4 : return R.drawable.ic_restituicao_96px;
            case 5 : return R.drawable.ic_financas_96px;

        }

        return 0;
    }

    private String chooseTitleMeta(int categoria) {

        switch (categoria) {

            case 1:
                return "Família";
            case 2:
                return "Ministério";
            case 3:
                return "Formação";
            case 4:
                return "Restituição";
            case 5:
                return "Finanças";

        }

        return "";

    }
}
