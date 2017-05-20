package android.if4.fst.ayabessmellah;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Cnx extends AppCompatActivity {

    EditText log;
    String logT;
    EditText pass;
    String passT;

    private ProgressDialog pDialog;
    //private static String url_login = "http://10.0.2.2/android/tuto2/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cnx);
        log=(EditText) findViewById(R.id.log);
        pass=(EditText) findViewById(R.id.pass);
        Button cnx=(Button) findViewById(R.id.cnx);

        cnx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logT=log.getText().toString();
                passT=pass.getText().toString();
                new VerifLogin().execute();
            }
        });

    }

    private class VerifLogin extends AsyncTask<String,String,String>{
        JSONArray users = null;

        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Cnx.this);
            pDialog.setMessage("cnx..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            String login_url = "http://10.0.2.2/android/tuto2/login.php";

                try {

                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    System.out.println("httpURLConnection" + httpURLConnection);
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();

                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("login", "UTF-8") + "=" + URLEncoder.encode(logT, "UTF-8") + "&"
                            + URLEncoder.encode("pass", "UTF-8") + "=" + URLEncoder.encode(passT, "UTF-8");

                    bufferedWriter.write(post_data);

                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();


                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String result = "";
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        result += line;
                    }
                    System.out.println("result " + result);
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();


                    JSONObject jsonObj = new JSONObject(result);
                    //String msg="1";
                    int succ = jsonObj.getInt("success");
                    if (succ == 1) {

                        JSONObject user = jsonObj.getJSONObject("user");
                        String id = user.getString("id");
                        String nom = user.getString("nom");
                        String prenom = user.getString("prenom");
                        Intent i = new Intent(getApplicationContext(), Acceuil.class);
                        i.putExtra("nom",nom);
                        i.putExtra("id",id);
                        i.putExtra("prenom",prenom);
                        startActivity(i);

                    } else
                        System.out.println("errooooooor");
                    //Toast.makeText(Cnx.this, "Invalid login or password", Toast.LENGTH_LONG).show();

                    return result;
                } catch (MalformedURLException e) {
                    System.out.println("catch 1");
                    e.printStackTrace();
                } catch (IOException e) {
                    System.out.println("catch 2");
                    e.printStackTrace();
                }catch (final JSONException e) {
                    System.out.println("catch 3 parsing error: " + e.getMessage());
                    e.printStackTrace();


                }

            return null;
        }

 /*   @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
    }*/

       /* @Override
        protected void onPostExecute(String result) {
            //alertDialog.setMessage(result);
            // alertDialog.show();

        }*/

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done

            pDialog.dismiss();
        }
    }
}
