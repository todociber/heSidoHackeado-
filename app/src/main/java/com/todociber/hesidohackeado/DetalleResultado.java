package com.todociber.hesidohackeado;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.todociber.hesidohackeado.bd.DaoMaster;
import com.todociber.hesidohackeado.bd.DaoSession;
import com.todociber.hesidohackeado.bd.HackeadoDao;

public class DetalleResultado extends AppCompatActivity {
    int posicion;
    Cursor cursorResultados;
    TextView Titulo,FechaRegistro,FechaDeAtaque,NumeroDeEmails,verificado,autor;
    LinearLayout  btnDetalles;
    public Tracker mTracker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_resultado);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Detalles");
        Titulo = (TextView)findViewById(R.id.Titulo);
        FechaRegistro = (TextView)findViewById(R.id.FechaRegistro);
        FechaDeAtaque = (TextView)findViewById(R.id.FechaDeAtaque);
        NumeroDeEmails = (TextView)findViewById(R.id.NumeroDeEmails);
        verificado = (TextView)findViewById(R.id.verificado);
        autor = (TextView)findViewById(R.id.autor);
        btnDetalles = (LinearLayout)findViewById(R.id.btnDetalles);
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();
        Bundle extra = DetalleResultado.this.getIntent().getExtras();
        posicion = (int) extra.get("position");

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(DetalleResultado.this, "TodociberBDHackeado", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        HackeadoDao hackeadoDao = daoSession.getHackeadoDao();
        cursorResultados = db.query(hackeadoDao.getTablename(),
                hackeadoDao.getAllColumns(), null, null, null, null,
                null);
        if(cursorResultados.getCount()>0){
            if(cursorResultados.moveToPosition(posicion)){
                Titulo.setText(cursorResultados.getString(2));
                FechaRegistro.setText(cursorResultados.getString(5));
                FechaDeAtaque.setText(cursorResultados.getString(6));
                NumeroDeEmails.setText(cursorResultados.getString(7));
                if(cursorResultados.getString(4).equals("true")){
                    verificado.setText(R.string.Veracidad_confirmada);
                }else{
                    verificado.setText(R.string.Veracidad_no_confirmada);
                }
                if(cursorResultados.getString(3).equals("anon")){
                    autor.setText(R.string.hacker_desconocido);
                }else{
                    autor.setText(cursorResultados.getString(3));
                }
                btnDetalles.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(cursorResultados.getString(8)));
                        startActivity(intent);
                    }
                });


            }

        }

    }

    @Override
    public void onResume(){
        super.onResume();
        mTracker.setScreenName("Detalle de resultados");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
                return true;

            case R.id.informacion:
                Intent a = new Intent(DetalleResultado.this,informacionApp.class);
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
