package com.heavendevelopment.mantvida2018.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.heavendevelopment.mantvida2018.Dominio.Evento;
import com.heavendevelopment.mantvida2018.R;
import com.heavendevelopment.mantvida2018.Util;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by Yuri on 17/12/2016.
 */

public class AdapterEvento extends BaseAdapter {

    private Context context;
    private ArrayList<Evento> listEventos;
    private int anoAtual = 2018;

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
        return position;
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

        try{

            int situacaoEvento = getSituacaoEvento(evento);

            //1- (nada) ,2- em andamento, 3 - realizado
            if(situacaoEvento == 1) {
                holder.tvEstadoEvento.setText("");
                holder.tvEstadoEvento.setTextColor(Color.WHITE);
            }else if(situacaoEvento == 2){

                holder.tvEstadoEvento.setText("Em andamento");
                holder.tvEstadoEvento.setTextColor(Color.BLUE);
            }else if(situacaoEvento == 3){

                holder.tvEstadoEvento.setText("Realizado");
                holder.tvEstadoEvento.setTextColor(Color.RED);
            }

        }catch (Exception ex){
            Util util = new Util(context);
            util.toast(ex.getMessage());
        }

        return convertView;
    }

    private int getSituacaoEvento(Evento evento){

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        int diaAtual = gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH);
        int mesAtual = gregorianCalendar.get(GregorianCalendar.MONTH) + 1;
        int anoCalendario = gregorianCalendar.get(GregorianCalendar.YEAR);

        int situacao = 0;
        int diaComposto1 = 0, diaComposto2 = 0;
        int diaEvento = 0;

        String []arrayData = evento.getData().split("[.]");

        int mesEvento = Integer.parseInt(arrayData[1]);

        if(arrayData[0].contains("a")){

            String [] arrayDataAux = arrayData[0].split("a");
            diaComposto1 = Integer.parseInt(arrayDataAux[0].replace(" ",""));
            diaComposto2 = Integer.parseInt(arrayDataAux[1].replace(" ",""));

        }else
            diaEvento = Integer.parseInt(arrayData[0]);

        //VERIFICAÇÕES DO ESTADO DA LEITURA

        if(anoAtual != anoCalendario){
            //se entrar aqui é porque ainda é 2017, então o estado do evento é 1 (não realizado)
            situacao = 1;

        }else{ // senão, já é 2018, ai fará as verificações regulares.

            if(mesEvento < mesAtual)
                situacao = 3;
            else if(mesEvento > mesAtual)
                situacao = 0;
            else {
                //se não, o mês atual é o mês do evento, então faço a verificação dos dias

                //se entrar aqui é porque o evento é de um dia só.
                if (diaComposto1 == 0) {
                    if (diaEvento == diaAtual)
                        situacao = 1;
                    else if (diaEvento < diaAtual)
                        situacao = 3;
                    else if (diaEvento > diaAtual)
                        situacao = 0;
                }else{
                    if(diaAtual >= diaComposto1 && diaAtual <= diaComposto2)
                        situacao = 1;
                    else if(diaAtual > diaComposto2)
                        situacao = 3;
                    else if(diaAtual < diaComposto1)
                        situacao = 0;
                }
            }
        }


        return situacao;
    }

    private static class ViewHolder{

        TextView tvId;
        TextView tvNome;
        TextView tvData;
        TextView tvEstadoEvento;

    }
}
