package android.if4.fst.ayabessmellah;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class AllHistory extends ListActivity
{

    private String TAG = MainScreenActivity.class.getSimpleName() + " --> Do in back -->";
    ProgressDialog pDialog ;
    int idUser = 2;
    private static String url_all_history = "http://10.0.2.2/android/tuto2/allHistory.php";
    ArrayList<HashMap<String, String>> myList;

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_Pizza_Tab = "pizza";
    private static final String TAG_PID = "pid";
    private static final String TAG_TAILLE = "taille";
    private static final String TAG_TYPE = "type";
    private static final String TAG_SUPP = "supp";
    private static final String TAG_Date = "date";
    private static final String TAG_prix = "prix";
    private static final String TAG_NOM = "nom";
    private static final String TAG_PRENOM = "prenom";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_history);
        myList=new ArrayList<HashMap<String, String>>();
        new AllHistory.getAllHistory().execute();
        ListView lv = getListView();
    }

    private class getAllHistory extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog= new ProgressDialog(AllHistory.this);
            pDialog.setMessage("Loading products. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            JSONArray pizzas = null;
            HttpHandler sh = new HttpHandler();
            String jsonStr = sh.makeServiceCall(url_all_history);
            System.out.println(jsonStr);
            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    pizzas = jsonObj.getJSONArray(TAG_Pizza_Tab);

                    for (int i = 0; i < pizzas.length(); i++) {
                        JSONObject c = pizzas.getJSONObject(i);

                        String id = c.getString(TAG_PID);
                        String taille = c.getString(TAG_TAILLE);
                        String type = c.getString(TAG_TYPE);
                        String date = c.getString(TAG_Date);
                        String prix = c.getString(TAG_prix);
                        String nom=c.getString(TAG_NOM);
                        String prenom=c.getString(TAG_PRENOM);


                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        map.put(TAG_PID, id);
                        map.put(TAG_TAILLE, taille);
                        map.put(TAG_TYPE, type);
                        map.put(TAG_Date, date);
                        map.put(TAG_prix, prix);
                        map.put(TAG_NOM, nom);
                        map.put(TAG_PRENOM, prenom);


                        // adding HashList to ArrayList
                        myList.add(map);
                    }

                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    e.printStackTrace();

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");


            }


            return null;
        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed JSON data into ListView
                     * */
                    ListAdapter adapter = new SimpleAdapter(
                            AllHistory.this, myList,
                            R.layout.list_item_cmd, new String[]{TAG_PID,
                            TAG_TYPE, TAG_TAILLE, TAG_Date, TAG_prix, TAG_PRENOM,TAG_NOM},
                            new int[]{R.id.pid, R.id.type,R.id.taille,R.id.date,R.id.prix,R.id.nom,R.id.prenom});
                    // updating listview
                    setListAdapter(adapter);
                }
            });
        }
    }
}
