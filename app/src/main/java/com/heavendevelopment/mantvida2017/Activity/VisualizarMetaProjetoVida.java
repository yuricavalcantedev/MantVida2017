package com.heavendevelopment.mantvida2017.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.heavendevelopment.mantvida2017.Dominio.Meta;
import com.heavendevelopment.mantvida2017.R;
import com.heavendevelopment.mantvida2017.Service.MetaService;

import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VisualizarMetaProjetoVida extends AppCompatActivity {

    Context context;
    private  int idMeta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_meta_projeto_vida);

        context = this;

        Bundle bundle = getIntent().getExtras();
        idMeta = bundle.getInt("idMeta");

        MetaService metaService = new MetaService(context);
        Meta meta = metaService.getMetaById(idMeta);

        //depois implementar a função do usuário clicar na imagem e aparecer um alertdialog personalizado
        //com as imagens e checkbox dos tipos de categoria que existem

        ImageView imgCategoria = (ImageView) findViewById(R.id.img_categoria_visualizar_meta);
        TextView tvTituloCategria = (TextView) findViewById(R.id.tv_titulo_visualizar_meta);
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


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_visualizar_devocionais_metas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.ic_menu_save) {

            atualizarMeta();

        }else if (id == R.id.ic_menu_delete){

            deletarMeta();

        }else if(id == R.id.ic_menu_share){

            compartilharMeta();
        }

        return super.onOptionsItemSelected(item);
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

        if(titulo.length() < 8){
            tilTitulo.setError("Este campo deve ter no mínimo 8 letras");
            camposValidados = false;
        }else{
            tilTitulo.setErrorEnabled(false);
        }

        if(como.length() < 8){
            tilComo.setError("Este campo deve ter no mínimo 8 letras");
            camposValidados = false;
        }else{
            tilComo.setErrorEnabled(false);
        }

        if(objetivo.length() < 8){
            tilObjetivo.setError("Este campo deve ter no mínimo 8 letras");
            camposValidados = false;
        }else{
            tilObjetivo.setErrorEnabled(false);
        }

        //faz validações


        if(camposValidados){

            MetaService metaService = new MetaService(this);

            Meta meta = metaService.getMetaById(idMeta);
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
                        RelativeLayout relativeLayoutProjetoVidaVisualizar = (RelativeLayout) findViewById(R.id.activity_visualizar_meta_projeto_vida);

                        metaService.deletarMeta(idMeta);

                        Snackbar.make(relativeLayoutProjetoVidaVisualizar, "Meta excluída com sucesso", Snackbar.LENGTH_LONG).show();

                        finish();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }

    private void compartilharMeta(){

        RelativeLayout relativeLayoutProjetoVidaVisualizar = (RelativeLayout) findViewById(R.id.activity_visualizar_meta_projeto_vida);

        Snackbar.make(relativeLayoutProjetoVidaVisualizar, "To implement", Snackbar.LENGTH_LONG).show();
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
