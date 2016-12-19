package com.heavendevelopment.mantvida2017.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.heavendevelopment.mantvida2017.Dominio.Evento;
import com.heavendevelopment.mantvida2017.R;

import java.util.ArrayList;

/**
 * Created by Yuri on 17/12/2016.
 */

public class AdapterEvento extends BaseAdapter {

    private Context context;
    private ArrayList<Evento> listEventos;

    public  AdapterEvento(Context context, ArrayList<Evento> listEventos){

        super();
        this.context = context;
        this.listEventos = listEventos;
    }

    @Override
    public int getCount() {
        return listEventos.size();
    }

    @Override
    public Object getItem(int position) {
        return listEventos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listEventos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        ViewHolder holder;

        if(convertView == null) {

            convertView = LayoutInflater.from(context).
                    inflate(R.layout.item_listview_eventos, viewGroup, false);

            holder = new ViewHolder();

            holder.tvId = (TextView) convertView.findViewById(R.id.tv_item_id_evento);
            holder.tvNome = (TextView) convertView.findViewById(R.id.tv_item_nome_evento);
            holder.tvData = (TextView) convertView.findViewById(R.id.tv_item_data_evento);
            holder.tvEstadoEvento = (TextView) convertView.findViewById(R.id.tv_item_estado_evento);
            convertView.setTag(holder);
        }else{

            holder = (ViewHolder) convertView.getTag();
        }

            Evento evento = listEventos.get(position);

            holder.tvId.setText(evento.getId() + " ");
            holder.tvNome.setText(evento.getNome());
            holder.tvData.setText("Data : " + evento.getData());

            //0 - não realizado, 1- em andamento, 2 - próximo evento, 3 - realizado
            setTextoTvEstadoEvento(holder.tvEstadoEvento, evento.getEstadoEvento());

        return convertView;
    }

    private void setTextoTvEstadoEvento(TextView tvRealizado ,int estadoEvento){

        switch (estadoEvento){

            case 1 : tvRealizado.setText("Em andamento");
                tvRealizado.setTextColor(context.getResources().getColor(android.R.color.holo_blue_dark));
                break;
            case 2 : tvRealizado.setText("Próximo evento");
                tvRealizado.setTextColor(context.getResources().getColor(android.R.color.holo_green_light));
                break;
            case 3 : tvRealizado.setText("Já realizado");
                tvRealizado.setTextColor(context.getResources().getColor(android.R.color.holo_red_light));
                break;
            default: tvRealizado.setText("");
                break;
        }

    }

    private static class ViewHolder{

        TextView tvId;
        TextView tvNome;
        TextView tvData;
        TextView tvEstadoEvento;

    }
}
