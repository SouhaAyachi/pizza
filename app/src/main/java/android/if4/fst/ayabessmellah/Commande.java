package android.if4.fst.ayabessmellah;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class Commande extends Activity {



    private ProgressDialog pDialog;

    ////declaration var

    private CheckBox pizza1, pizza2, pizza3, pizza5, pizza6, pizza10, pizza7,pizza11, pizza9;
    private Button Envoyer;
    private Button Quitter;
    private int id;
    String idC;


    // JSON Node names
    private static final String TAG_SUCCESS = "success";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commande);

        ///int registrate = R.layout.activity_commande;
        pizza1 = (CheckBox) findViewById(R.id.pizza1);
        pizza2 = (CheckBox) findViewById(R.id.pizza2);
        pizza3 = (CheckBox) findViewById(R.id.pizza3);

        pizza5 = (CheckBox) findViewById(R.id.pizza5);
        pizza6 = (CheckBox) findViewById(R.id.pizza6);
        pizza7 = (CheckBox) findViewById(R.id.pizza7);

        pizza9 = (CheckBox) findViewById(R.id.pizza9);
        pizza10 = (CheckBox) findViewById(R.id.pizza10);
        pizza11 = (CheckBox) findViewById(R.id.pizza11);

        Envoyer = (Button) findViewById(R.id.Envoyer);
        Quitter = (Button) findViewById(R.id.Quitter);

        Intent intent = getIntent();
        idC=intent.getStringExtra("id");
        System.out.println("ena flCommande id: "+idC);


        Envoyer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                System.out.println("chheeeeeeeeeeeek");
                if(pizza1.isChecked()) id=1;
                if(pizza2.isChecked()) id=2;
                if(pizza3.isChecked()) id=3;
                if(pizza5.isChecked()) id=5;
                if(pizza6.isChecked()) id=6;
                if(pizza7.isChecked()) id=7;
                if(pizza9.isChecked()) id=9;
                if(pizza10.isChecked()) id=10;
                if(pizza11.isChecked()) id=11;
                System.out.println("idcheeeeek: "+id);


                new CreateNewCommande().execute();
            }
        });


    }


    class CreateNewCommande extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(android.if4.fst.ayabessmellah.Commande.this);
            pDialog.setMessage("Creating Command..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }


        /**
         * Creating product
         */
        @Override
        protected String doInBackground(String... args) {

            try {


                String link = "http://10.0.2.2/android/tuto2/create_cmd.php";
                String data = URLEncoder.encode("id", "UTF-8") + "=" +
                        URLEncoder.encode((String.valueOf(id)), "UTF-8");
               data +="&"+ URLEncoder.encode("idC", "UTF-8") + "=" +
                        URLEncoder.encode((String.valueOf(idC)), "UTF-8");


                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                System.out.println("open conncetion URL" + url.toString());

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.write(data);
                wr.flush();

                BufferedReader reader = new BufferedReader(new
                        InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    sb.append(line);
                    break;
                }

                return sb.toString();
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }


        }


        /**
         * After completing background task Dismiss the progress dialog
         **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
        }


    }
    }