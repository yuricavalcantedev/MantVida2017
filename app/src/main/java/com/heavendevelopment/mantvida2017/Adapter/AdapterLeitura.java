package com.heavendevelopment.mantvida2017.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.heavendevelopment.mantvida2017.Dominio.Versículo;
import com.heavendevelopment.mantvida2017.R;

import java.util.ArrayList;

/**
 * Created by Yuri on 21/12/2016.
 */

public class AdapterLeitura extends BaseAdapter {

    private Context context;
    private ArrayList<Versículo> listVersiculos;

    public AdapterLeitura(Context context, ArrayList<Versículo> listVersiculos) {
        this.context = context;
        this.listVersiculos = listVersiculos;
    }

    @Override
    public int getCount() {
        return listVersiculos.size();
    }

    @Override
    public Object getItem(int position) {
        return listVersiculos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listVersiculos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        ViewHolder holder;

        if(convertView == null){

            convertView = LayoutInflater.from(context).
                    inflate(R.layout.item_listview_leitura_biblica,viewGroup,false);

            holder = new ViewHolder();

            holder.tvNumVerso = (TextView) convertView.findViewById(R.id.tv_item_num_versiculo_leitura);
            holder.tvTextoVerso = (TextView) convertView.findViewById(R.id.tv_item_texto_versiculo_leitura);

            convertView.setTag(holder);
        }else{

            holder = (ViewHolder) convertView.getTag();
        }

        Versículo versículo = listVersiculos.get(position);

        holder.tvNumVerso.setText(versículo.getNumVersiculo()+"");
        holder.tvTextoVerso.setText(versículo.getTexto());

        return convertView;
    }

    private static class ViewHolder{

        TextView tvNumVerso;
        TextView tvTextoVerso;
    }
}
