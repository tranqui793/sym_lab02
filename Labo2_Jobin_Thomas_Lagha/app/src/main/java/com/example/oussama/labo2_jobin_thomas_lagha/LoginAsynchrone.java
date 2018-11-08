package com.example.oussama.labo2_jobin_thomas_lagha;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class LoginAsynchrone extends Activity implements View.OnClickListener {
    //creation des composants
    private EditText et_usename,et_password;
    private Button btn_login;
    private ProgressBar pb_login;

    //Variables
    private String username,password;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //liaison des composants avec la vue
        et_usename=findViewById(R.id.et_username);
        et_password=findViewById(R.id.et_password);
        btn_login=findViewById(R.id.btn_login);
        pb_login=findViewById(R.id.pb_login);

        btn_login.setOnClickListener(this );

    }
    @Override
    public void onClick(View v) {
        if(v==btn_login)
        {
            username=et_usename.getText().toString();
            password=et_password.getText().toString();


            pb_login.setVisibility(View.VISIBLE);
        }
    }
}
