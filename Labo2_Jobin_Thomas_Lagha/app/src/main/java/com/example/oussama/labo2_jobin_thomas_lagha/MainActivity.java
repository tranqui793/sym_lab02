package com.example.oussama.labo2_jobin_thomas_lagha;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private Button asynchrone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loginActivity(View view){
        Intent intentLogin=new Intent(this,LoginAsynchrone.class);
        startActivity(intentLogin);
    }
}
