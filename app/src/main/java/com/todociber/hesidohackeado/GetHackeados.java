package com.todociber.hesidohackeado;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.todociber.hesidohackeado.bd.Correos;
import com.todociber.hesidohackeado.bd.CorreosDao;
import com.todociber.hesidohackeado.bd.DaoMaster;
import com.todociber.hesidohackeado.bd.DaoSession;
import com.todociber.hesidohackeado.bd.Hackeado;
import com.todociber.hesidohackeado.bd.HackeadoDao;
import com.todociber.hesidohackeado.util.MyHttpClient;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by imoves on 12-16-15.
 */
public class GetHackeados {

    BufferedReader in = null;


    public String errorString;
    public int result;
    public String correo ;



    public GetHackeados(Context context, String email) {

        String WS = "http://69.87.218.215/getHackeados/";



        try {

            DefaultHttpClient client = new MyHttpClient(context);
            HttpGet request = new HttpGet();
            request.setHeader("Content-Type", "application/json; charset=utf-8");
            Log.i("url", "" + WS+email );
            request.setURI(new URI(WS+email));
            HttpResponse response = client.execute(request);
            Log.i("response", "" + response);
            in = new BufferedReader(new InputStreamReader(response.getEntity()
                    .getContent()));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            in.close();
            String page = sb.toString();

            JSONObject jsonObject = new JSONObject(page);

            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "TodociberBDHackeado", null);
            SQLiteDatabase db = helper.getWritableDatabase();
            DaoMaster daoMaster = new DaoMaster(db);
            DaoSession daoSession = daoMaster.newSession();

            errorString  = jsonObject.getString("status");
            correo = jsonObject.getString("query");

            if (errorString.equals("found")) {

                HackeadoDao hackeadoDao;
                CorreosDao correosDao;
                hackeadoDao = daoSession.getHackeadoDao();
                correosDao = daoSession.getCorreosDao();
                Correos correos = new Correos();
                correos.setEmail(email);

                correosDao.insertInTx(correos);
                Hackeado hackeado = new Hackeado();

             JSONArray data = jsonObject.getJSONArray("data");
                hackeadoDao.deleteAll();
                for (int i=0; i<data.length();i++){
                    JSONObject datosV = data.getJSONObject(i);
                    String title = datosV.getString("title");
                    String author = datosV.getString("author");
                    String is_vrf = datosV.getString("is_vrf");
                    String date_created = datosV.getString("date_created");
                    String date_leaked = datosV.getString("date_leaked");
                    String emails_count = datosV.getString("emails_count");
                    String details = datosV.getString("details");
                    String source_id = datosV.getString("source_id");
                    String source_url = datosV.getString("source_url");
                    String source_lines = datosV.getString("source_lines");
                    String source_size = datosV.getString("source_size");
                    String source_network = datosV.getString("source_network");
                    String source_provider = datosV.getString("source_provider");
                    hackeado.setId(Long.valueOf(i));
                    hackeado.setCorreo(correo);
                    hackeado.setTitle(title);
                    hackeado.setAuthor(author);
                    hackeado.setIs_vrf(is_vrf);
                    hackeado.setDate_created(date_created);
                    hackeado.setDate_leaked(date_leaked);
                    hackeado.setEmails_count(emails_count);
                    hackeado.setDetails(details);
                    hackeado.setSource_id(source_id);
                    hackeado.setSource_url(source_url);
                    hackeado.setSource_lines(source_lines);
                    hackeado.setSource_size(source_size);
                    hackeado.setSource_network(source_network);
                    hackeado.setSource_provider(source_provider);


                    hackeadoDao.insertInTx(hackeado);

                }
                result = 0;
            } else if (errorString.equals("notfound")) {
                HackeadoDao hackeadoDao;
                CorreosDao correosDao;
                hackeadoDao = daoSession.getHackeadoDao();
                correosDao = daoSession.getCorreosDao();
                Correos correos = new Correos();
                correos.setEmail(email);

                correosDao.insertInTx(correos);

                result = 1;
            } else if (errorString.equals("badsintax")) {
                result = 2;
            }


        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            result = 9;
            e.printStackTrace();
            // Toast.makeText(context, context.getResources().getString(R.string.noWS), Toast.LENGTH_LONG).show();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            result = 9;
            e.printStackTrace();
            //  Toast.makeText(context, context.getResources().getString(R.string.noWS), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            result = 9;
            e.printStackTrace();
            //  Toast.makeText(context, context.getResources().getString(R.string.noWS), Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            result = 9;
            //  Toast.makeText(context, context.getResources().getString(R.string.noWS), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            Log.i("eeeeeeeeeeeeee1", "" + e);
            //  Toast.makeText(context, context.getResources().getString(R.string.noWS), Toast.LENGTH_LONG).show();
            result = 15;
        } finally{
            // result = 9;
            //  Toast.makeText(context, context.getResources().getString(R.string.noWS), Toast.LENGTH_LONG).show();

        }
    }

    public HttpParams timeOut(int timeout, int timesocket) {

        HttpParams httpParameters = new BasicHttpParams();

        int timeoutConnection = timeout;
        HttpConnectionParams.setConnectionTimeout(httpParameters,
                timeoutConnection);

        int timeoutSocket = timesocket;
        HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

        return httpParameters;
    }

}

