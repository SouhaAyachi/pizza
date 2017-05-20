package android.if4.fst.ayabessmellah;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/*
http://www.androidhive.info/2012/05/how-to-connect-android-with-php-mysql/

 */
public class MainScreenActivity extends Activity {


    Button inscrire;

    Button allHistory;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

       // Buttons


        inscrire=(Button) findViewById(R.id.inscrire);

        allHistory=(Button) findViewById(R.id.allHistory);
        login=(Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Cnx.class);
                startActivity(i);
            }
        });

        allHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AllHistory.class);
                startActivity(i);
            }
        });



        inscrire.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching create new product activity
                Intent i = new Intent(getApplicationContext(), Register.class);
                startActivity(i);

            }
        });
    }
}
