package com.todociber.hesidohackeado;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.todociber.hesidohackeado.bd.DaoMaster;
import com.todociber.hesidohackeado.bd.DaoSession;
import com.todociber.hesidohackeado.bd.HackeadoDao;

public class ListadoResultados extends AppCompatActivity {
    ResultadosAdapter adapter;
    Cursor cursorResultados;
    public String email;
    private LayoutInflater inflater;
    public Tracker mTracker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inflater = LayoutInflater.from(ListadoResultados.this);
        setContentView(R.layout.activity_listado_resultados);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Resultados ");
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();

        Bundle extra = ListadoResultados.this.getIntent().getExtras();
        email = (String) extra.get("email");
        ListView listadoResultado = (ListView) findViewById(R.id.ListadoResultado);

        setSupportActionBar(toolbar);
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(ListadoResultados.this, "TodociberBDHackeado", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        HackeadoDao hackeadoDao = daoSession.getHackeadoDao();
        cursorResultados = db.query(hackeadoDao.getTablename(),
                hackeadoDao.getAllColumns(), "CORREO = "+"'"+email+"'", null, null, null,
                null);
        if(cursorResultados.getCount()>0){
            if(cursorResultados.moveToFirst()){
                mTracker.send(new HitBuilders.EventBuilder()
                        .setCategory("Action")
                        .setAction("Busqueda con resultados: "+cursorResultados.getCount())
                        .build());

                listadoResultado.addHeaderView(getView(null),null,false);
                adapter = new ResultadosAdapter(ListadoResultados.this, cursorResultados);
                listadoResultado.setAdapter(adapter);
            }else{
                createSimpleDialog().show();
            }

        }else{
            createSimpleDialog().show();
        }


        assert listadoResultado != null;
        listadoResultado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent a = new Intent(ListadoResultados.this,DetalleResultado.class);
                a.putExtra("position",position-1);
                startActivity(a);
            }
        });

    }


    public AlertDialog createSimpleDialog() {

        mTracker.send(new HitBuilders.EventBuilder()
                .setCategory("Action")
                .setAction("Busqueda sin resultados")
                .build());
        AlertDialog.Builder builder = new AlertDialog.Builder(ListadoResultados.this);

        builder.setTitle("Sin resultados")
                .setMessage("En hora buena no tenemos registros de ataques que te afecten!!")
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });


        return builder.create();
    }

    public View getView( View convertView) {
        View view = convertView;
        ViewHolder holder;

        if (convertView == null) {
            view = inflater.inflate(R.layout.cabecera_resultado, null);
            holder = new ViewHolder();
            holder.ENCABEZADO = (TextView) view.findViewById(R.id.ENCABEZADO);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }



        return view;
    }

    private class ViewHolder {
        public TextView ENCABEZADO;

    }

    @Override
    public void onResume(){
        super.onResume();
        mTracker.setScreenName("Listado de Resultados");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
                return true;

            case R.id.informacion:
                Intent a = new Intent(ListadoResultados.this,informacionApp.class);
                startActivity(a);

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
