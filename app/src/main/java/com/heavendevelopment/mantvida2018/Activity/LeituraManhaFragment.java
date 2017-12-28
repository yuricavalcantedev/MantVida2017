package com.heavendevelopment.mantvida2018.Activity;

import android.content.ClipData;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.heavendevelopment.mantvida2018.Dominio.Leitura;
import com.heavendevelopment.mantvida2018.Dominio.Versículo;
import com.heavendevelopment.mantvida2018.R;
import com.heavendevelopment.mantvida2018.Service.LeituraService;
import com.heavendevelopment.mantvida2018.Util;

import java.util.ArrayList;

import de.mrapp.android.bottomsheet.BottomSheet;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * Created by yuri on 27/12/17.
 */

public class LeituraManhaFragment extends Fragment {

    private ListView lvLeituraManha;
    private LeituraService leituraService;
    private ArrayList<Leitura> listLeitura;
    private int tamFonteLeitura;
    private boolean modoNoturno;
    private int versaoBiblia;
    private int diaLeitura;
    private int mesLeitura;


    public LeituraManhaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.leitura_manha_fragment, container, false);

        Bundle bundle = getArguments();

        tamFonteLeitura = bundle.getInt("tamanhoFonte",20);
        versaoBiblia = bundle.getInt("versaoBiblia",1); // 1 - NVI
        modoNoturno = bundle.getBoolean("modoNoturno",false);
        diaLeitura = bundle.getInt("diaLeitura");
        mesLeitura = bundle.getInt("mesLeitura");


        listLeitura = leituraService.getReadingOfDay(diaLeitura,mesLeitura);

        ArrayList<Versículo> versiculosLeitura;
        for(int i = 0; i < listLeitura.size(); i++) {
            versiculosLeitura = leituraService.getLeituraDiaria(listLeitura.get(i).getIdLivro(), listLeitura.get(i).getCapitulo());
            listLeitura.get(i).setVersiculos(versiculosLeitura);
        }

        lvLeituraManha = (ListView) view.findViewById(R.id.listview_leitura_manha);



        lvLeituraManha.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, int position, long id) {

                PopupMenu popupMenu = new PopupMenu(getContext(), view, Gravity.END);


                popupMenu.getMenuInflater().inflate(R.menu.menu_popup_leitura, popupMenu.getMenu());
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        //TextView tvLivroCapitulo = (TextView) findViewById(R.id.tv_titulo_leitura_biblica);
                        TextView tvNumVersiculo = (TextView) view.findViewById(R.id.tv_item_num_versiculo_leitura);
                        TextView tvTextoVersiculo = (TextView) view.findViewById(R.id.tv_item_texto_versiculo_leitura);

                        //String livro_capitulo = tvLivroCapitulo.getText().toString();
                        String livro_capitulo = "";
                        livro_capitulo = livro_capitulo.substring(0, livro_capitulo.length() - 1);

                        String numVersiculo = tvNumVersiculo.getText().toString();
                        String textoVersiculo = tvTextoVersiculo.getText().toString();
                        String versao = versaoBibliaTexto(versaoBiblia);

                        String versoCompleto = textoVersiculo + " - " + versao + "\n" + livro_capitulo + "." + numVersiculo;

                        if (item.getItemId() == R.id.item_copiar) {

                            android.content.ClipboardManager clipboardManager = (android.content.ClipboardManager) getActivity().getSystemService(CLIPBOARD_SERVICE);

                            ClipData clipData = ClipData.newPlainText("text", versoCompleto);
                            clipboardManager.setPrimaryClip(clipData);

                            Util util = new Util(getContext());
                            util.toast("Versículo copiado");

                        } else if (item.getItemId() == R.id.item_devocional) {

                            Intent intent = new Intent(getContext(), CriarDevocional.class);
                            intent.putExtra("versiculoCopiado", versoCompleto);

                            startActivity(intent);

                        } else {

                            versoCompleto += "\nMANT VIDA 2017";

                            BottomSheet.Builder builder = new BottomSheet.Builder(getContext());
                            builder.setTitle("Compartilhar via...");
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_SEND);
                            intent.putExtra(Intent.EXTRA_TEXT, versoCompleto);
                            intent.setType("text/plain");

                            builder.setIntent(getActivity(), intent);
                            builder.setStyle(BottomSheet.Style.GRID);

                            BottomSheet bottomSheet = builder.create();
                            bottomSheet.show();

                        }

                        return true;
                    }

                });

                return true;
            }
        });


        return view;
    }


    private String versaoBibliaTexto(int versaoBiblia){

        String versaoTexto;

        if(versaoBiblia == 1)
            versaoTexto = "NVI";
        else if(versaoBiblia == 2)
            versaoTexto = "NTLH";
        else if(versaoBiblia == 3)
            versaoTexto = "ACF";
        else
            versaoTexto = "KJV";

        return versaoTexto;
    }

}
