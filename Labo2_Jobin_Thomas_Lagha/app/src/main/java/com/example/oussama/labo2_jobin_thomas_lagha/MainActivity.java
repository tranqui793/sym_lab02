/**
-----------------------------------------------------------------------------------------
Laboratory  : SYM - Laboratory n°2
File        : MainActivity.java
Author      : Lagha Oussama, Jobin Simon, Thomas Benjamin
Date        : 20.11.2018
Goal        : Implementation of the mainActivity that lead to the different transfer types
Remark(s)   : -
----------------------------------------------------------------------------------------
*/


package com.example.oussama.labo2_jobin_thomas_lagha;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private  Button asynchroneButt;
    private  Button graphQLButt;
    private  Button serializedButt;
    private Button defferedButt;
    private Button compressedButt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButtons();
    }

    public void initButtons(){
        asynchroneButt = findViewById(R.id.asynchrone);
        asynchroneButt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                loginActivity(v);
            }
        });

        graphQLButt = findViewById(R.id.GraphQL);
        graphQLButt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                graphQL(v);
            }
        });

        serializedButt = findViewById(R.id.sérialisation);
        serializedButt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                serialized(v);
            }
        });

        defferedButt = findViewById(R.id.différée);
        defferedButt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                deffered(v);
            }
        });

        compressedButt = findViewById(R.id.compressée);
        compressedButt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                compressed(v);
            }
        });
    }


    public void loginActivity(View view){
        Intent intentLogin=new Intent(this, LoginAsynchrone.class);
        startActivity(intentLogin);
    }

    public void delayedActivity(View view){
        Intent intentDelayed = new Intent(this, DelayedTransmission.class);
        startActivity(intentDelayed);
    }

    public void graphQL(View view ){
        Intent intentGraphQL=new Intent(this, GraphQL.class);
        startActivity(intentGraphQL);
    }

    public void serialized(View view) {
        Intent intentSerialized = new Intent(this, Serialized.class);
        startActivity(intentSerialized);
    }

    public void deffered(View view){
        Intent intentDeffered = new Intent(this, DelayedTransmission.class);
        startActivity(intentDeffered);
    }

    public void compressed(View view ){
        Intent intentCompressed = new Intent(this,Compressed.class);
        startActivity(intentCompressed);

    }

}
