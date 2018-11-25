package com.example.oussama.labo2_jobin_thomas_lagha;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Spinner;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GraphQL extends Activity {

    OkHttpClient client = new OkHttpClient();
    Spinner spiner ;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphql);
        spiner=findViewById(R.id.spinner);
        Request.Builder builder=new Request.Builder()
                .url("http://sym.iict.ch/api/graphql").post(RequestBody.create(MediaType.parse(" application/json"), "{ \"query\": \"{allAuthors{id, first_name, last_name }}\" }" ));
        Request request=builder.build();
        try{
         Response response =client.newCall(request).execute();
           // System.out.print(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
