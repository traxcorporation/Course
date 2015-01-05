package com.example.ytraore.listedecourse;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Bieres extends Activity {

    EditText etResponse;
    TextView tvIsConnected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bieres);

        // get reference to the views
        etResponse = (EditText) findViewById(R.id.etResponse);
        tvIsConnected = (TextView) findViewById(R.id.tvIsConnected);

        // check if you are connected or not
        if(isConnected()){
            tvIsConnected.setBackgroundColor(0xFF00CC00);
            tvIsConnected.setText("You are connected");
        }
        else{
            tvIsConnected.setText("You are NOT conncted");
        }

        // call AsynTask to perform network operation on separate thread
        new HttpAsyncTask().execute("http://binouze.fabrigli.fr/bieres.json");
    }

    public static String GET(String url){
        InputStream inputStream = null;
        String result = "";
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }
    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return GET(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

            try {
                JSONArray js = new JSONArray(result);
                String jstext = js.getJSONObject(0).getString("category");
                String jstext1 = js.getJSONObject(0).getString("name");
                String jstext2 = js.getJSONObject(0).getString("created_at");
                String jstext3 = js.getJSONObject(0).getJSONObject("image").getJSONObject("image").getJSONObject("thumb").getString("url");
                String jstext4 = js.getJSONObject(0).getString("description");
                String jstext5 = js.getJSONObject(0).getString("country");
                String jstextotal = " -"+ jstext + "\n -" + jstext1 + "\n -" + jstext2 + "\n -" + jstext3 + "\n -" + jstext4+ "\n -" + jstext5 ;
                ((TextView) findViewById(R.id.etResponse)).setText(jstextotal);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
            //etResponse.setText(result);
        }
    }
}
