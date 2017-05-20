package android.if4.fst.ayabessmellah;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

//https://www.tutorialspoint.com/android/android_php_mysql.htm

public class Register extends AppCompatActivity {



    private ProgressDialog pDialog;

    EditText nom;
    String nomT;

    EditText prenom;
    String prenomT;

    EditText login;
    String loginT;

    EditText pass;
    String passT;

    EditText age;
    String ageT;


    // url to create new product
    private static String url_create_client = "http://10.0.2.2/android/tuto2/create_client.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        int registrate = R.layout.activity_register;
        nom=(EditText) findViewById(R.id.NomR);
        prenom=(EditText) findViewById(R.id.prenom);
        prenomT=prenom.getText().toString();
         login=(EditText) findViewById(R.id.login);
        loginT=login.getText().toString();
         pass=(EditText) findViewById(R.id.pass);
        passT=pass.getText().toString();
         age=(EditText) findViewById(R.id.age);
        ageT=age.getText().toString();

        Button valider= (Button) findViewById(R.id.cnx);

        valider.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // creating new product in background thread
                nomT=nom.getText().toString();
                prenomT=prenom.getText().toString();
                loginT=login.getText().toString();
                passT=pass.getText().toString();
                ageT=age.getText().toString();
                new CreateNewUser().execute();
            }
        });
    }


    class CreateNewUser extends AsyncTask<String, String, String>
    {

        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Register.this);
            pDialog.setMessage("Creating Product..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }



        /**
         * Creating product
         * */
        @Override
        protected String doInBackground(String... args) {

            try{


                String link="http://10.0.2.2/android/tuto2/create_client.php";
                String data  = URLEncoder.encode("nom", "UTF-8") + "=" +
                        URLEncoder.encode(nomT, "UTF-8");
                data += "&" + URLEncoder.encode("prenom", "UTF-8") + "=" +
                        URLEncoder.encode(prenomT, "UTF-8");
                data+="&"+URLEncoder.encode("login", "UTF-8") + "=" +
                        URLEncoder.encode(loginT, "UTF-8");
                data+="&"+URLEncoder.encode("pass", "UTF-8") + "=" +
                        URLEncoder.encode(passT, "UTF-8");
                data+="&"+URLEncoder.encode("age", "UTF-8") + "=" +
                        URLEncoder.encode(ageT, "UTF-8");

                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                System.out.println("open conncetion URL"+url.toString());

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.write( data );
                wr.flush();

                BufferedReader reader = new BufferedReader(new
                        InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while((line = reader.readLine()) != null) {
                    System.out.println(line);
                    sb.append(line);
                    break;
                }

                return sb.toString();
            } catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }


        }



        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
        }


    }
}
