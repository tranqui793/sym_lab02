/**
-----------------------------------------------------------------------------------------
Laboratory  : SYM - Laboratory n°2
File        : LoginAsynchrone.java
Author      : Lagha Oussama, Jobin Simon, Thomas Benjamin
Date        : 20.11.2018
Goal        : Implementation of the asynchronous login data transfer
Remark(s)   : -
----------------------------------------------------------------------------------------
*/

package com.example.oussama.labo2_jobin_thomas_lagha;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class  LoginAsynchrone extends Activity implements View.OnClickListener {
    //creation des composants
    private EditText et_usename,et_password,et_response;
    private Button btn_login;

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
        et_response=findViewById(R.id.responseText);

        btn_login.setOnClickListener(this );

    }
    @Override
    public void onClick(View v) {
        if(v==btn_login)
        {
            username=et_usename.getText().toString();
            password=et_password.getText().toString();

            sendRequest("username: "+ username+" password: "+password,"http://sym.iict.ch/rest/txt");

        }
    }

    /** Send a request to the url in paramter
     * @param request the request to send
     * @param url the url to which send the request
     */
    public void sendRequest(String request,String url){
        AsynchSendRequest asycSendHandler=new AsynchSendRequest();
        asycSendHandler.setCommunicationEventListener(new CommunicationEventListener() {
            @Override
            public boolean handleServerResponse(String response) {
                Toast.makeText(getApplicationContext(),"Le traitement Asynchrone est terminé",Toast.LENGTH_LONG).show();
                et_response.setVisibility(View.VISIBLE);
                et_response.setText(response);
                return false;
            }
        });
        asycSendHandler.execute(request,url,"text/plain");
    }

}
