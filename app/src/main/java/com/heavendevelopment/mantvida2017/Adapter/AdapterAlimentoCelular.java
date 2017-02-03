package com.heavendevelopment.mantvida2017.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.heavendevelopment.mantvida2017.Dominio.AlimentoCelular;
import com.heavendevelopment.mantvida2017.R;

import java.util.List;

/**
 * Created by Yuri on 25/01/2017.
 */

public class AdapterAlimentoCelular extends BaseAdapter {

    private Context context;
    private List<AlimentoCelular> listAlimentosCelulares;

    public AdapterAlimentoCelular(Context context, List<AlimentoCelular> listAlimentosCelulares){

        this.context = context;
        this.listAlimentosCelulares = listAlimentosCelulares;
    }


    @Override
    public int getCount() {
        return listAlimentosCelulares.size();
    }

    @Override
    public Object getItem(int position) {
        return listAlimentosCelulares.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        AdapterAlimentoCelular.ViewHolder holder;

        if(convertView == null) {

            convertView = LayoutInflater.from(context).
                    inflate(R.layout.item_list_view_alimento_celular, viewGroup, false);

            holder = new AdapterAlimentoCelular.ViewHolder();

            holder.tvNome = (TextView) convertView.findViewById(R.id.tv_item_nome_alimento_celular);
            holder.tvData = (TextView) convertView.findViewById(R.id.tv_item_data_alimento_celular);
            holder.tvNumero = (TextView) convertView.findViewById(R.id.tv_item_numero_alimento_celular);

            convertView.setTag(holder);
        }else{

            holder = (AdapterAlimentoCelular.ViewHolder) convertView.getTag();
        }

        AlimentoCelular alimentoCelular = listAlimentosCelulares.get(position);

        String numeroAlimento = "";

        if(alimentoCelular.getNumero() < 10)
            numeroAlimento = "00"+alimentoCelular.getNumero();
        else
            numeroAlimento = "0"+alimentoCelular.getNumero();

        holder.tvNome.setText(numeroAlimento + " " + alimentoCelular.getNome());
        holder.tvData.setText(alimentoCelular.getData());
        holder.tvNumero.setText(alimentoCelular.getNumero());


        return convertView;

    }

    private static class ViewHolder{

        TextView tvNome;
        TextView tvData;
        TextView tvNumero;

    }


}
