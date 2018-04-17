package com.heavendevelopment.mantvida20182.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.heavendevelopment.mantvida20182.Dominio.Devocional;
import com.heavendevelopment.mantvida20182.R;
import com.heavendevelopment.mantvida20182.Service.DevocionalService;

import java.util.GregorianCalendar;

import de.mrapp.android.bottomsheet.BottomSheet;

public class CriarDevocional extends AppCompatActivity {

    private TextInputLayout tilTitulo;
    private TextInputLayout tilTextoChave;
    private TextInputLayout tilMensagemDeus;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_devocional);

        tilTitulo = (TextInputLayout) findViewById(R.id.til_titulo_criar_devocional);
        tilTextoChave = (TextInputLayout) findViewById(R.id.til_textoChave_criar_devocional);
        tilMensagemDeus = (TextInputLayout) findViewById(R.id.til_mensagemDeus_criar_devocional);

        context = this;


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Criar Devocional");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //caso essa activity tenha sido chamada de dentro da leitura.
        Intent intent = getIntent();
        String versiculoCopiado;

        if(intent != null){
            versiculoCopiado = intent.getStringExtra("versiculoCopiado");
            tilTextoChave.getEditText().setText(versiculoCopiado);
        }

        Button btSalvarDevocional = (Button) findViewById(R.id.bt_salvar_devocional);
        btSalvarDevocional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criarDevocional();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_novo_devocional, menu);
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
        }else if(id == R.id.ic_menu_share){
            compartilharDevocional();
        }

        return super.onOptionsItemSelected(item);
    }

    private void criarDevocional(){

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        int dia = gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH);
        int mes = gregorianCalendar.get(GregorianCalendar.MONTH) + 1;
        int ano = gregorianCalendar.get(GregorianCalendar.YEAR);

        String titulo = tilTitulo.getEditText().getText().toString();
        String dataCriacao = dia+"/"+mes+"/"+ano;
        String textoChave = tilTextoChave.getEditText().getText().toString();
        String mensagemDeDeus = tilMensagemDeus.getEditText().getText().toString();

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

        if(mensagemDeDeus.length() < 10){
            tilMensagemDeus.setError("Este campo deve ter no mínimo 10 caracteres");
            camposValidados = false;
        }else{
            tilMensagemDeus.setErrorEnabled(false);
        }

        if(camposValidados){

            Devocional devocional = new Devocional(titulo,dataCriacao,textoChave,mensagemDeDeus);

            DevocionalService devocionalService = new DevocionalService(context);
            boolean devocionalCriado = devocionalService.criarDevocional(devocional);

            if(devocionalCriado) {

                Toast.makeText(this, "Devocional criado com sucesso", Toast.LENGTH_SHORT).show();

                //fecha a activity
                finish();
            }else
                Toast.makeText(this, "Ocorreu algum erro. Tente novamente daqui em alguns segundos.", Toast.LENGTH_SHORT).show();
        }

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
