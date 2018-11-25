package com.example.oussama.labo2_jobin_thomas_lagha;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class  Serialized extends Activity implements View.OnClickListener {

    //Test variable
    private User user;

    //UI
    private Button btn_login;
    private EditText  et_usename,et_password, et_response;

    //Conection
    private final String URL_SERVER = "http://sym.iict.ch/rest/json";

    //Serialization/deserialization
    private final Gson gson = new GsonBuilder().create();


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);

        //liaison des composants avec la vue
        et_usename=findViewById(R.id.et_username);
        et_password=findViewById(R.id.et_password);
        btn_login=findViewById(R.id.btn_login);
        et_response=findViewById(R.id.responseText);
        btn_login.setOnClickListener(this);


    }


    /** Check weather or not the server response was right or not and display a toast indicating it
     * @param res the server response
     * @return a boolean indicating if the server response was correct or not
     */
    public boolean verifyServerResponse(String res){

        User tmp = gson.fromJson(res, User.class);

        if(tmp.equals(user)){
            Toast.makeText(getApplicationContext(),"Le traitement de sérialisation est terminé: succès",Toast.LENGTH_LONG).show();
            return true;
        }else{
            Toast.makeText(getApplicationContext(),"Le traitement de sérialisation est terminé: echec",Toast.LENGTH_LONG).show();
            return false;
        }
    }


    public String sendRequest(String request,String url){
        AsynchSendRequest asycSendHandler=new AsynchSendRequest();
        asycSendHandler.setCommunicationEventListener(new CommunicationEventListener() {
            @Override
            public boolean handleServerResponse(String response) {
                verifyServerResponse(response);
                et_response.setVisibility(View.VISIBLE);
                et_response.setText(response);
                return false;
            }
        });
        asycSendHandler.execute(request,url, "text/plain");
        return null ;
    }

    /** Send the serialized data if the click was from btn_login
     *  @param v The view on which display data
     */
    @Override
    public void onClick(View v) {
        if(v==btn_login)
        {

            user = new User(et_usename.getText().toString(), et_password.getText().toString());
            String serializedData = gson.toJson(user);

            sendRequest(serializedData, URL_SERVER);
        }
    }
}