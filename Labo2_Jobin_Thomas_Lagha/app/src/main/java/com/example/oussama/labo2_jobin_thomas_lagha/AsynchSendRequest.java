package com.example.oussama.labo2_jobin_thomas_lagha;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AsynchSendRequest extends AsyncTask<String,String,String> {
    private CommunicationEventListener listener;
    public AsynchSendRequest(){
    }
    OkHttpClient client = new OkHttpClient();
    @Override
    protected String doInBackground(String... params) {
        Request.Builder builder=new Request.Builder()
                .url(params[1]).post(RequestBody.create(MediaType.parse("text/plain"),params[0]));
        Request request=builder.build();
        try{
            Response response =client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    @Override
    protected  void onPostExecute(String s){
        super.onPostExecute(s);
        listener.handleServerResponse(s);
    }

    @Override
    protected  void onPreExecute(){
        super.onPreExecute();
    }

    public void setCommunicationEventListener(CommunicationEventListener l) {
      this.listener =l;
    }
}
