package com.example.oussama.labo2_jobin_thomas_lagha;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private final Button asynchroneButt;
    private final Button graphQLButt;
    private final Button serializedButt;

    //private final Button defferedButt;
    //private final Button compressedButt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButtons();
    }

    public void initButtons(){
        asynchroneButt = findViewById(R.id.asynchroneButt_id);
        asynchroneButt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                loginActivity(v);
            }
        });

        graphQLButt = findViewById(R.id.graphQLButt_id);
        graphQLButt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                graphQL(v);
            }
        });

        serializedButt = findViewById(R.id.serializedButt_id);
        serializedButt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                serialized(v);
            }
        });
        /*
        defferedButt = findViewById(R.id.defferedButt_id);
        defferedButt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                deffered(v);
            }
        });

        compressedButt = findViewById(R.id.compressedButt_id);
        compressedButt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                compressed(v);
            }
        });*/
    }

    public void loginActivity(View view){
        Intent intentLogin=new Intent(this,LoginAsynchrone.class);
        startActivity(intentLogin);
    }
    public void graphQL(View view ){
        Intent intentGraphQL=new Intent(this,GraphQL.class);
        startActivity(intentGraphQL);
    }

    public void serialized(View view ) {
        Intent intentSerialized = new Intent(this, Serialized.class);
        startActivity(intentSerialized);
    }

    /*public void deffered(){
        Intent intentDeffered = new Intent(this,Deffered.class);
        startActivity(intentDeffered);
    }

    public void compressed(View view ){
        Intent intentCompressed = new Intent(this,Compressed.class);
        startActivity(intentCompressed);

    }*/

}
