package com.heavendevelopment.mantvida20182.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.heavendevelopment.mantvida20182.Dominio.Devocional;
import com.heavendevelopment.mantvida20182.R;
import com.heavendevelopment.mantvida20182.Service.DevocionalService;

import java.util.GregorianCalendar;

import de.mrapp.android.bottomsheet.BottomSheet;

public class VisualizarDevocional extends AppCompatActivity {

    Context context;
    private int idDevocional;
    TextInputLayout tilTitulo;
    TextInputLayout tilTextoChave;
    TextInputLayout tilMensagemDeus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_devocional);

        context = this;

        Bundle bundle = getIntent().getExtras();

        idDevocional = bundle.getInt("idDevocional");

        DevocionalService devocionalService = new DevocionalService(this);

        Devocional devocional = devocionalService.getDevocionalById(idDevocional);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Ver Devocional");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tilTitulo = (TextInputLayout) findViewById(R.id.til_titulo_visualizar_devocional);
        tilTextoChave = (TextInputLayout) findViewById(R.id.til_textoChave_visualizar_devocional);
        tilMensagemDeus = (TextInputLayout) findViewById(R.id.til_mensagemDeus_visualizar_devocional);

        final TextView tvTitulo = (TextView) findViewById(R.id.tv_titulo_visualizar_devocional);
        TextView tvDataCriacao = (TextView) findViewById(R.id.tv_data_criacao_visualizar_devocional);
        final EditText etTítulo = tilTitulo.getEditText();
        EditText etTextoChave = tilTextoChave.getEditText();
        EditText etMensagemDeus = tilMensagemDeus.getEditText();


        tvTitulo.setText(devocional.getTitulo());
        tvDataCriacao.setText("Data de Criação : " + devocional.getDataCriacao());
        etTítulo.setText(devocional.getTitulo());
        etTextoChave.setText(devocional.getTextoChave());
        etMensagemDeus.setText(devocional.getMensagemDeDeus());


        etTítulo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                //atualiza o novo nome do devocional em tempo real
                String novoTitulo = etTítulo.getText().toString();
                tvTitulo.setText(novoTitulo);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_visualizar_devocionais, menu);
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

            atualizarDevocional();

        }else if (id == R.id.ic_menu_delete){

            deletarDevocional();

        }else if(id == R.id.ic_menu_share){

            compartilharDevocional();
        }

        return super.onOptionsItemSelected(item);
    }

    private void atualizarDevocional(){


        TextInputLayout tilTitulo = (TextInputLayout) findViewById(R.id.til_titulo_visualizar_devocional);
        TextInputLayout tilTextoChave = (TextInputLayout) findViewById(R.id.til_textoChave_visualizar_devocional);
        TextInputLayout tilMensagemDeus = (TextInputLayout) findViewById(R.id.til_mensagemDeus_visualizar_devocional);

        String titulo = tilTitulo.getEditText().getText().toString();
        String textoChave = tilTextoChave.getEditText().getText().toString();
        String mensagemDeus = tilMensagemDeus.getEditText().getText().toString();

        //falta pegar a parte da categoria

        GregorianCalendar calendar = new GregorianCalendar();
        int day = calendar.get(GregorianCalendar.DAY_OF_MONTH);
        int month = calendar.get(GregorianCalendar.MONTH) + 1;
        int year = calendar.get(GregorianCalendar.YEAR);

        //talvez lá para frente, pegar também a informação de quando foi alterado
        //e não somente a a informação da data de criação

        String dataHoje = day + "/" + month + "/" + year;

        //checa se as informações são válidas

        boolean camposValidados = true;

        //falta comparar se a data de conclusão é menor do que a do início.

        if(titulo.length() < 8){
            tilTitulo.setError("Este campo deve ter no mínimo 8 caracteres");
            camposValidados = false;
        }else{
            tilTitulo.setErrorEnabled(false);
        }

        if(textoChave.length() < 8){
            tilTextoChave.setError("Este campo deve ter no mínimo 8 caracteres");
            camposValidados = false;
        }else{
            tilTextoChave.setErrorEnabled(false);
        }

        if(mensagemDeus.length() < 10){
            tilMensagemDeus.setError("Este campo deve ter no mínimo 10 caracteres");
            camposValidados = false;
        }else{
            tilMensagemDeus.setErrorEnabled(false);
        }

        //faz validações


        if(camposValidados){

            DevocionalService devocionalService = new DevocionalService(this);

            Devocional devocional = devocionalService.getDevocionalById(idDevocional);
            devocional.setTitulo(titulo);
            devocional.setTextoChave(textoChave);
            devocional.setMensagemDeDeus(mensagemDeus);


            boolean devocionalAtualizado = devocionalService.atualizarDevocional(devocional);

            if(devocionalAtualizado){
                Toast.makeText(this, "Devocional atualizado com sucesso.", Toast.LENGTH_SHORT).show();

                //fecha a activity
                finish();

            }else{
                Toast.makeText(this, "Ocorreu um erro. Tente novamente daqui alguns segundos", Toast.LENGTH_SHORT).show();

            }
        }


    }

    private void deletarDevocional(){

        final DevocionalService devocionalService = new DevocionalService(context);
        final Devocional devocionalExcluir = devocionalService.getDevocionalById(idDevocional);

        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle("Excluir Devocional")
                .setMessage("Você realmente deseja excluir o devocional : " + devocionalExcluir.getTitulo())
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
                        RelativeLayout relativeLayoutDevocionalVisualizar = (RelativeLayout) findViewById(R.id.activity_visualizar_devocional);

                        devocionalService.deletarDevocional(idDevocional);

                        Snackbar.make(relativeLayoutDevocionalVisualizar, "Devocional excluído com sucesso", Snackbar.LENGTH_LONG).show();

                        finish();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }

    private void compartilharDevocional(){

        String titulo = tilTitulo.getEditText().getText().toString();
        String textoChave = tilTextoChave.getEditText().getText().toString();
        String mensagemDeDeus = tilMensagemDeus.getEditText().getText().toString();


        String textoCompartilhar = "Devocional - " + titulo+ " - MANT VIDA \n\n";
        textoCompartilhar += "Escondi a tua Palavra no meu coração para não pecar contra Ti. (Sl 119:11)\n\n";
        textoCompartilhar += "Título:\n" + titulo + "\n\n";
        textoCompartilhar += "Texto chave:\n" + textoChave + "\n\n";
        textoCompartilhar += "Mensagem de Deus para mim:\n" + mensagemDeDeus + "\n\n";

        BottomSheet.Builder builder = new BottomSheet.Builder(this);
        builder.setTitle("Compartilhar via...");
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, textoCompartilhar );
        intent.setType("text/plain");

        builder.setIntent(this, intent);
        builder.setStyle(BottomSheet.Style.GRID);

        BottomSheet bottomSheet = builder.create();
        bottomSheet.show();

    }


}
