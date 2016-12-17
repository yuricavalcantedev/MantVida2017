package com.heavendevelopment.mantvida2017.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.heavendevelopment.mantvida2017.Dominio.Devocional;
import com.heavendevelopment.mantvida2017.Dominio.Palavra;
import com.heavendevelopment.mantvida2017.R;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Yuri on 08/12/2016.
 */

public class AdapterPalavra extends BaseAdapter {

    private ArrayList<Palavra> listAuxPalavras;
    private ArrayList<Palavra> listPalavras;
    private Context context;

    public AdapterPalavra (Context context, ArrayList<Palavra> listPalavras){

        this.context = context;
        this.listPalavras = listPalavras;
        listAuxPalavras = new ArrayList<>();
        listAuxPalavras.addAll(listPalavras);

    }

    @Override
    public int getCount() {
        return listPalavras.size();
    }

    @Override
    public Object getItem(int position) {
        return listPalavras.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listPalavras.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        if(convertView == null){

            convertView = LayoutInflater.from(context).
                    inflate(R.layout.item_listview_palavas,viewGroup,false);


            Palavra palavra = listPalavras.get(position);

            String id = palavra.getId()+"";
            String titulo = palavra.getTitulo()+"";
            String autor = palavra.getAutor()+"";
            String data = palavra.getData()+"";

            TextView tvId = (TextView) convertView.findViewById(R.id.tv_item_id_palavra);
            TextView tvTitulo = (TextView)convertView.findViewById(R.id.tv_item_titulo_palavra);
            TextView tvAutor = (TextView)convertView.findViewById(R.id.tv_item_autor_palavra);
            TextView tvData = (TextView)convertView.findViewById(R.id.tv_item_data_palavra);

            tvId.setText(id);
            tvTitulo.setText(titulo);
            tvAutor.setText(autor);
            tvData.setText(data);

        }

        return convertView;
    }

    public void filter(String charText) {

        charText = charText.toLowerCase(Locale.getDefault());

        listPalavras.clear();
        if (charText.length() == 0) {
            listPalavras.addAll(listAuxPalavras);

        } else {
            for (Palavra palavra : listAuxPalavras)
                if (palavra.getTitulo().toLowerCase(Locale.getDefault()).contains(charText)) {
                    listPalavras.add(palavra);
                }
        }

        notifyDataSetChanged();
    }

}

