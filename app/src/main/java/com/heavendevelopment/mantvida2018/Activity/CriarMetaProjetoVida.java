package com.heavendevelopment.mantvida2018.Activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.heavendevelopment.mantvida2018.Dominio.Meta;
import com.heavendevelopment.mantvida2018.R;
import com.heavendevelopment.mantvida2018.Service.MetaService;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CriarMetaProjetoVida extends AppCompatActivity {

    private Spinner spinnerCategorias;

    private int tvEscolherData = 1;
    int categoriaSelecionada = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_projeto_vida);

        spinnerCategorias = (Spinner) findViewById(R.id.spinner_categoria_criar_projetovida);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Criar Meta");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        EditText etDataInicio = ((TextInputLayout)findViewById(R.id.til_dataInicio_criar_meta)).getEditText();
        EditText etDataConclusao = ((TextInputLayout)findViewById(R.id.til_dataConclusao_criar_meta)).getEditText();

        String [] categoriesArray = getResources().getStringArray(R.array.array_categorias);
        spinnerCategorias.setAdapter(new CriarMetaProjetoVida.SpinnerAdapter(this,R.layout.item_spinner_meta,categoriesArray));


        etDataInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvEscolherData = 1;
                escolherData(v);
            }
        });

        etDataConclusao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvEscolherData = 2;
                escolherData(v);
            }
        });

        spinnerCategorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                categoriaSelecionada = position+1;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    public class SpinnerAdapter extends ArrayAdapter<String> {

        String [] categoriesArray;

        public SpinnerAdapter(Context context, int resource, String[] stringArray) {
            super(context, resource, stringArray);
            categoriesArray = stringArray;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = getLayoutInflater();
            View row = inflater.inflate(R.layout.item_spinner_meta, parent, false);

            String categoryTitle = categoriesArray[position];

            TextView tituloCategoria =(TextView)row.findViewById(R.id.tv_item_spinner_meta);
            tituloCategoria.setText(categoryTitle);

            ImageView imgCategoria = (ImageView) row.findViewById(R.id.img_item_spinner_meta);
            imgCategoria.setImageResource(chooseImageResource(categoryTitle));

            return row;
        }

        private int chooseImageResource(String categoryTitle){

            switch (categoryTitle){

                case "Família" : return R.drawable.ic_familia_24px;
                case "Ministério" : return R.drawable.ic_ministerio_24px;
                case "Formação" : return R.drawable.ic_formacao_24px;
                case "Restituição" : return R.drawable.ic_restituicao_24px;
                case "Finanças" : return R.drawable.ic_financas_24px;

            }

            return 0 ;
        }

    }

    //Métodos auxiliares chamados quando o usuário clica para escolher a data das metas
    public void escolherData(View v) {

        Calendar calendarInstance = Calendar.getInstance();
        int mes = calendarInstance.get(Calendar.MONTH);
        int dia = calendarInstance.get(Calendar.DAY_OF_MONTH);
        int ano = calendarInstance.get(Calendar.YEAR);

        //acrescento um mês caso seja a data de conclusão só para não ficar a mesma data
        if(tvEscolherData == 2)
            mes += 1;

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, datePickerListener, ano, mes, dia);
        datePickerDialog.show();


    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            //0 - TextView ligada ao Início.
            //1 - TextView ligada à Conclusão.

            EditText tvData = null;

            //o mês que o usuário seleciona, vem com o valor -1 do mês selecionado
            //porque começa em 0.

            selectedMonth += 1;

            //Se for igual a 1 é porque a textView é a do ínicio
            if (tvEscolherData == 1) {
                tvData = ((TextInputLayout) findViewById(R.id.til_dataInicio_criar_meta)).getEditText();
            }else {
                tvData = ((TextInputLayout) findViewById(R.id.til_dataConclusao_criar_meta)).getEditText();
            }
            String day = "", month = "";

            //se o dia ou mês selecionados forem menor do que 10, eu adiciono um 0 na frente só para ficar mais bonito
            //o formato da data

            if (selectedDay < 10)
                day = "0" + selectedDay;
            else
                day = selectedDay+"";

            if (selectedMonth < 10)
                month = "0" + selectedMonth;
            else
                month = selectedMonth+"";

            String dataSelecionada = day + "/" + month + "/" + selectedYear;

            tvData.setText(dataSelecionada);

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_criar_meta, menu);
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

            criarMeta();

        }

        return super.onOptionsItemSelected(item);
    }

    private String retornaCategoriaByNumero(int idCategoria){

        switch (idCategoria){

            //1 - Família, 2 - Ministério, 3 - Formação, 4 - Restituição, 5 - Finanças
            case 1 : return "Família";
            case 2 : return "Ministério";
            case 3 : return "Formação";
            case 4 : return "Restituição";
            case 5 : return "Finanças";
        }

        return "";
    }

    private void criarMeta(){

        TextInputLayout tilTitulo = (TextInputLayout) findViewById(R.id.til_titulo_criar_meta);
        TextInputLayout tilComo = (TextInputLayout) findViewById(R.id.til_como_criar_meta);
        TextInputLayout tilObjetivo = (TextInputLayout) findViewById(R.id.til_objetivo_criar_meta);
        TextInputLayout tilDataInicio = (TextInputLayout) findViewById(R.id.til_dataInicio_criar_meta);
        TextInputLayout tilDataConclusao = (TextInputLayout) findViewById(R.id.til_dataConclusao_criar_meta);

        String titulo = tilTitulo.getEditText().getText().toString();
        String como = tilComo.getEditText().getText().toString();
        String objetivo = tilObjetivo.getEditText().getText().toString();
        int categoria = categoriaSelecionada;
        String dataInicio = tilDataInicio.getEditText().getText().toString();
        String dataConclusao = tilDataConclusao.getEditText().getText().toString();

        GregorianCalendar calendar = new GregorianCalendar();
        int day = calendar.get(GregorianCalendar.DAY_OF_MONTH);
        int month = calendar.get(GregorianCalendar.MONTH) + 1;
        int year = calendar.get(GregorianCalendar.YEAR);

        String dataCriacao = day + "/" + month + "/" + year;

        //checa se as informações são válidas

        Pattern regexDataInicial = Pattern.compile("\\d{2}/\\d{2}/\\d{2,4}");
        Matcher matcherDataInicial = regexDataInicial.matcher(dataInicio);

        Pattern regexDataConclusao = Pattern.compile("\\d{2}/\\d{2}/\\d{2,4}");
        Matcher matcherDataConclusao = regexDataConclusao.matcher(dataConclusao);

        boolean camposValidados = true;

        if(!matcherDataInicial.matches()){
            tilDataInicio.setError("Data inválida. Ex : " + dataCriacao);
            camposValidados = false;
        }else{
            tilDataInicio.setErrorEnabled(false);
        }

        if(!matcherDataConclusao.matches()){
            tilDataConclusao.setError("Data inválida. Ex : " + dataCriacao);
            camposValidados = false;

        }else{
            tilDataConclusao.setErrorEnabled(false);
        }

        //falta comparar se a data de conclusão é menor do que a do início.

        if(titulo.length() < 5){
            tilTitulo.setError("Este campo deve ter no mínimo 5 letras");
            camposValidados = false;
        }else{
            tilTitulo.setErrorEnabled(false);
        }

        if(como.length() < 5){
            tilComo.setError("Este campo deve ter no mínimo 5 letras");
            camposValidados = false;
        }else{
            tilComo.setErrorEnabled(false);
        }

        if(objetivo.length() < 5){
            tilObjetivo.setError("Este campo deve ter no mínimo 5 letras");
            camposValidados = false;
        }else{
            tilObjetivo.setErrorEnabled(false);
        }


        if(camposValidados){

            Meta meta = new Meta(titulo,como,objetivo,categoria,dataCriacao,dataInicio,dataConclusao);

            MetaService metaService = new MetaService(this);
            boolean metaCriada = metaService.criarMeta(meta);

            if(metaCriada){
                Toast.makeText(this, "Meta criada com sucesso.", Toast.LENGTH_SHORT).show();

                //fecha a activity
                finish();

            }else{
                Toast.makeText(this, "Ocorreu um erro. Tente novamente daqui alguns segundos", Toast.LENGTH_SHORT).show();

            }
        }


    }

}
