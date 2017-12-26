package com.heavendevelopment.mantvida2018.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.heavendevelopment.mantvida2018.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Yuri on 20/02/2017.
 */

public class AdapterAjuda extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listaNomeGrupos;
    private HashMap<String, List<String>> listaItensGrupo;

   public AdapterAjuda(Context context, List<String> listaNomeGrupos,
                       HashMap<String, List<String>> listaItensGrupo){

       this.context = context;
       this.listaNomeGrupos = listaNomeGrupos;
       this.listaItensGrupo = listaItensGrupo;
   }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.listaItensGrupo.get(this.listaNomeGrupos.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.item_group_list_expandable_listview, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.tv_item_grupo_expandable_listview);

        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listaItensGrupo.get(this.listaNomeGrupos.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listaNomeGrupos.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.listaNomeGrupos.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group_expandable_listview, null);
        }

        TextView tvNomeGrupo = (TextView) convertView
                .findViewById(R.id.tv_nome_grupo_expandable_listview);
        tvNomeGrupo.setTypeface(null, Typeface.BOLD);
        tvNomeGrupo.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
