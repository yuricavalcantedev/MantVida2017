package com.heavendevelopment.mantvida2017.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.heavendevelopment.mantvida2017.Dominio.Palavra;
import com.heavendevelopment.mantvida2017.R;

import org.w3c.dom.Text;

public class VisualizarPalavra extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_palavra);

        Bundle bundle = getIntent().getExtras();
        int idPalavra = bundle.getInt("idPalavra");

        //carrega o objeto na memória

        Palavra palavra = new Palavra();

        TextView tvTitulo = (TextView) findViewById(R.id.tv_titulo_visualizar_palavra);
        TextView tvAutor = (TextView) findViewById(R.id.tv_autor_visualizar_palavra);
        TextView tvData = (TextView) findViewById(R.id.tv_data_visualizar_palavra);
        TextView tvMensagem = (TextView) findViewById(R.id.tv_mensagem_visualizar_palavra);

        tvTitulo.setText(palavra.getTitulo());
        tvAutor.setText(palavra.getAutor());
        tvData.setText(palavra.getData());
        tvMensagem.setText(palavra.getMensagem());


        //TODO : Implementar nessa activity a parte de compartilhar a palavra pelas redes sociais.


    }
}
