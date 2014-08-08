package com.wyble.nuevita.app;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wyble.nuevita.app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class HomeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button bot1 = (Button) findViewById(R.id.personaje);
        bot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent about = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(about);
            }
        });
        new Audio().execute();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class Audio extends AsyncTask<Void, Void, String>
    {

        protected void onPreExecute() {

    }

    public String doInBackground(Void... arguments)
    {

            try {
                MediaPlayer mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource("http://181.41.201.152/conexionapi/audioinicio.mp3");
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SecurityException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


          String mensaje ="Audio cargado...";    //return mensaje.toString();
        return mensaje;
    }

        protected void onProgressUpdate (Float... valores) {

        }

        protected void onPostExecute(String mensaje) {

            Toast.makeText(HomeActivity.this, mensaje, Toast.LENGTH_SHORT).show();

        }
    }
}
