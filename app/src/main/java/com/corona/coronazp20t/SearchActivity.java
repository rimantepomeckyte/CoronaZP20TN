package com.corona.coronazp20t;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;

public class SearchActivity extends AppCompatActivity {

    private static final String URL = "https://covid19-api.weedmark.systems/api/v1/stats";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //sukuriama nauja gija JSON nuskaitymui
        AsyncFetch asyncFetch = new AsyncFetch();
        asyncFetch.execute();
    }

    private class AsyncFetch extends AsyncTask<String, String, JSONObject> {
        ProgressDialog progressDialog = new ProgressDialog(SearchActivity.this);

        @Override
        //onPreExecute veiksmai bus atlikti pries JSON nuskaityma(doInBackground)
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage(getResources().getString(R.string.search_loading_data));
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        //nuskaitys JSON
        protected JSONObject doInBackground(String... strings) {
            try {
                JSONObject json = JSON.readJsonFromUrl(URL);
                return json;
            } catch (IOException e) {
                Toast.makeText(
                        SearchActivity.this,
                        getResources().getString(R.string.search_error_reading_data)+e.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
                return null;
            } catch (JSONException e) {
                Toast.makeText(
                        SearchActivity.this,
                        getResources().getString(R.string.search_error_reading_data)+e.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
                return null;
            }
        }

        @Override
        // Sis metodas bus vykdomas tada kai grazins duomenis doInBackground metodas(kai gausime JSON is API)
        protected void onPostExecute(JSONObject jsonObject) {
            progressDialog.dismiss();
            int statusCode = 0;
            try {
                statusCode = (Integer) jsonObject.get("statusCode");
            } catch (JSONException e) {
                Toast.makeText(
                        SearchActivity.this,
                        getResources().getString(R.string.search_error_reading_data)+e.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
            }
            if (statusCode == HttpURLConnection.HTTP_OK){
                    System.out.println(jsonObject.toString());
                Toast.makeText(SearchActivity.this, jsonObject.toString(), Toast.LENGTH_LONG).show();
            }else{//kazkas ivyko blogai(serveris negrazino http ok status kodo)
                String message = null;
                try {
                    message = jsonObject.getString("message");
                } catch (JSONException e) {
                    Toast.makeText(
                            SearchActivity.this,
                            getResources().getString(R.string.search_error_reading_data)+e.getMessage(),
                            Toast.LENGTH_LONG
                    ).show();
                }
                Toast.makeText(
                        SearchActivity.this,
                        getResources().getString(R.string.search_error_reading_data)+message,
                        Toast.LENGTH_LONG
                ).show();
            }//else uzdaro
        }// onPostExecute uzdaro
    }// uzdaro AsyncFetch klase
}// uzdaro pagrindine klase Search