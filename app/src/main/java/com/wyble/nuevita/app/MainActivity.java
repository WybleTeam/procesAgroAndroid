package com.wyble.nuevita.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.StrictMode;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Button boton1 = (Button) findViewById(R.id.button1);
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent about = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(about);
            }
        });

        Button boton2 = (Button) findViewById(R.id.button2);
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jairo = new Intent(MainActivity.this, MainActivity3.class);
                startActivity(jairo);
            }
        });

        Button boton3 = (Button) findViewById(R.id.button3);
        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jairo = new Intent(MainActivity.this, MainActivity4.class);
                startActivity(jairo);
            }
        });

        Button boton4 = (Button) findViewById(R.id.button4);
        boton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jairo = new Intent(MainActivity.this, MainActivity5.class);
                startActivity(jairo);
            }
        });

        Button boton5 = (Button) findViewById(R.id.button5);
        boton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jairo = new Intent(MainActivity.this, MainActivity6.class);
                startActivity(jairo);
            }
        });

        Button boton6 = (Button) findViewById(R.id.button6);
        boton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jairo = new Intent(MainActivity.this, MainActivity7.class);
                startActivity(jairo);
            }
        });


        Button boton7 = (Button) findViewById(R.id.button7);
        boton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jairo = new Intent(MainActivity.this, MainActivity8.class);
                startActivity(jairo);
            }
        });

        Button boton8 = (Button) findViewById(R.id.button8);
        boton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jairo = new Intent(MainActivity.this, MainActivity9.class);
                startActivity(jairo);
            }
        });


        //Realizamos cualquier otra operación necesaria
        //Creamos una nueva instancia y llamamos al método ejecutar
        //pasándole el string.
        new AccesoRemoto().execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    private class AccesoRemoto extends AsyncTask<Void, Void, String> {

        protected void onPreExecute() {

        }

        protected String doInBackground(Void... arguments) {
            /**
             * Simularemos que descargamos un fichero
             * mediante un sleep
             */



            httpHandler handler = new httpHandler();
            String txt = handler.post("http://181.41.200.108/procesWebService/service.php");
            //TextView t = (TextView) findViewById(R.id.convoca);
            //t.setText(txt);


            // TextView jairo = (TextView) findViewById(R.id.reprueba);


            Handler_sqlite helper = new Handler_sqlite(MainActivity.this);
            //TextView text = (TextView) findViewById(R.id.tramitesppal);
            helper.abrir();

            try {
                JSONArray jsonArray = new JSONArray(txt);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    String idTabla = jsonObject.getString("id");
                    String usuario = jsonObject.getString("usuario_id");
                    String descripcion = jsonObject.getString("descripcion");
                    String url = jsonObject.getString("urlConvocatoria");
                    String descripcionLarga = jsonObject.getString("descripcionLarga");
                    System.out.println(url+"*******************************************");


                    Cursor prueba = helper.findReg(idTabla);
                    //System.out.println(prueba+"....esta es la prueba");
                    if (prueba != null){
                        System.out.println("Ya estaaaaa*******************************");
                    }else{
                        helper.insertarReg(idTabla,usuario,descripcion,url,descripcionLarga);
                        System.out.println("Insertado*******************************");
                    };
                    //helper.findReg(idTabla,null);
                    //
                    //jairo.setText(var2);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //text.setText(helper.leer());
            helper.cerrar();
            String mensaje = "Actualizado";

            return mensaje.toString();
        }

        protected void onProgressUpdate (Float... valores) {

        }

        protected void onPostExecute(String mensaje) {

            Toast.makeText(MainActivity.this, mensaje, Toast.LENGTH_SHORT).show();

        }
    }

}
