package android.if4.fst.ayabessmellah;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Acceuil extends AppCompatActivity {

    Button allHistory;
    Button myHistory;
    Button cmd;
    Button dex;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);
        allHistory=(Button) findViewById(R.id.allHistory);
        myHistory=(Button) findViewById(R.id.myHistory);
        cmd=(Button) findViewById(R.id.cmd);
        dex=(Button) findViewById(R.id.cnx);
        Intent intent = getIntent();
        String nom = intent.getStringExtra("nom");
         id=intent.getStringExtra("id");
        System.out.println("nom "+nom);


        allHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AllHistory.class);
                startActivity(i);
            }
        });

        myHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MyHistory.class);
                i.putExtra("id",id);
                startActivity(i);
            }
        });

        cmd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Commande.class);
                i.putExtra("id",id);
                startActivity(i);
            }
        });

        dex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainScreenActivity.class);
                startActivity(i);
            }
        });
    }
}
