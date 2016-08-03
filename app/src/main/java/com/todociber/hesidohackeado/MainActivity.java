package com.todociber.hesidohackeado;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class MainActivity extends AppCompatActivity {
    public ProgressDialog loading;
    public Button btnComprobar;
    public String email;
    public String ErrorString;
    public Context context;
    private EditText textEmailAddress;

    public Tracker mTracker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("he sido hackeado?");

        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();


        textEmailAddress = (EditText)findViewById(R.id.email);
        btnComprobar = (Button)findViewById(R.id.btnComprobar);
        btnComprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context = MainActivity.this;
                email = textEmailAddress.getText().toString();
                if(isEmailValid(email)){

                    new Gethackeos().execute();
                    mTracker.send(new HitBuilders.EventBuilder()
                            .setCategory("Action")
                            .setAction("Busqueda "+email)
                            .build());
                }else{
                    Toast.makeText(MainActivity.this,"Ingresa un email Valido",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

@Override
public void onResume(){
    super.onResume();
    mTracker.setScreenName("Pantalla de Busqueda");
    mTracker.send(new HitBuilders.ScreenViewBuilder().build());

}

    private class Gethackeos extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
                    loading = new ProgressDialog(MainActivity.this);
                    loading.setMessage("Cargando");
                    loading.setCancelable(false);
                    loading.show();
        }
        @Override
        protected Void doInBackground(Void... arg0) {
            // TODO Auto-generated method stub
            try{
                GetHackeados getHackeados = new GetHackeados(MainActivity.this,email);
                ErrorString = getHackeados.errorString;
            }catch (Exception e){

            }

            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
             loading.dismiss();
            Intent i = new Intent (MainActivity.this, ListadoResultados.class);
            i.putExtra("email",email);
            startActivity(i);
        }
    }
    public static boolean isEmailValid(String email) {
        boolean isValid = false;
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.informacion:
                Intent a = new Intent(MainActivity.this,informacionApp.class);
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
