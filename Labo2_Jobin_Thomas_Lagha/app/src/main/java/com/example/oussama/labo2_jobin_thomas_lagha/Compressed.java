/**
-----------------------------------------------------------------------------------------
Laboratory  : SYM - Laboratory n°2
File        : Compressed.java
Author      : Lagha Oussama, Jobin Simon, Thomas Benjamin
Date        : 20.11.2018
Goal        : Implementation of the compression data transfer
Remark(s)   : -
----------------------------------------------------------------------------------------
*/

package com.example.oussama.labo2_jobin_thomas_lagha;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

import static org.apache.commons.io.IOUtils.copy;

public class  Compressed extends Activity implements View.OnClickListener {

    //Test variable
    private User user;

    //UI
    private Button btn_login;
    private EditText  et_usename,et_password, et_response;

    //Serialization/deserialization
    private final Gson gson = new GsonBuilder().create();

    private String URL_SERVER = "http://sym.iict.ch/rest/txt";

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

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

    }


    /** Compress the string in parameter
     *  @param str the string to compress
     *  @return the compressed string
     */
    public static String compress(String str) throws Exception
    {
        if(str == null)
            return "";

        ByteArrayOutputStream ba = new ByteArrayOutputStream(str.length() * 2 + 20);
        DeflaterOutputStream out = new DeflaterOutputStream(ba);
        byte[] bytes = str.getBytes("UTF8");
        out.write(bytes);
        out.finish();
        out.flush();

        return new String(ba.toByteArray());
    }

    /** Decompress the string in parameter
     *  @param str the string to decompress
     *  @return the decompressed string
     */
    public static String decompress(String str) throws Exception
    {
        if(str == null)
            return null;

        byte[] compressedData = str.getBytes();
        InflaterInputStream in = new InflaterInputStream(new ByteArrayInputStream(compressedData));
        ByteArrayOutputStream out = new ByteArrayOutputStream(compressedData.length * 2);
        copy(in, out);
        byte[] decompressedData= out.toByteArray();
        return new String(decompressedData, "UTF8");
    }



    /** Check weather or not the server response was right or not and display a toast indicating it
     * @param res the server response
     * @return a boolean indicating if the server response was correct or not
     */
    public boolean verifyServerResponse(String res) throws Exception {

        User tmp = gson.fromJson(new String(decompress(res)), User.class);
        
        
        if(tmp.equals(user)){
            Toast.makeText(getApplicationContext(),"Le traitement de compression est terminé: succès",Toast.LENGTH_LONG).show();
            return true;
        }else{
            Toast.makeText(getApplicationContext(),"Le traitement de compression est terminé: echec",Toast.LENGTH_LONG).show();
            return false;
        }
    }


    /** Send a request to the url in paramter
     * @param request the request to send
     * @param url the url to which send the request
     */
    public void sendRequest(String request,String url){
        final AsynchSendRequest asycSendHandler=new AsynchSendRequest();
        asycSendHandler.setCommunicationEventListener(new CommunicationEventListener() {

            @Override
            public boolean handleServerResponse(String response) {

                try {
                    verifyServerResponse(response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                et_response.setVisibility(View.VISIBLE);
                et_response.setText(response);
                return false;
            }
        });
        asycSendHandler.execute(request,url, "text/plain");
    }

    /** Send the serialized data if the click was from btn_login
     *  @param v The view on which display data
     */
    @Override
    public void onClick(View v) {
        if(v==btn_login)
        {

            user = new User(et_usename.getText().toString(), et_password.getText().toString());
            String data = gson.toJson(user);

            try {
                sendRequest(new String(compress(data)), URL_SERVER);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}