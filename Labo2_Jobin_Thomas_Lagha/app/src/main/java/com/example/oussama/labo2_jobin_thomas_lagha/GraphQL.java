package com.example.oussama.labo2_jobin_thomas_lagha;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;

public class GraphQL extends Activity {
    Spinner spiner ;
    ArrayList<Author> authors=new ArrayList<>();
    LinearLayout postLayouts;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphql);
        spiner=findViewById(R.id.spinner);
        sendRequestAllAuthers();
        spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                postLayouts = findViewById(R.id.layoutResponse);
                Author author = authors.get((int)id);
                sendRequestAuthorPosts(author);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //nothing
            }
        });
    }
    public String sendRequestAllAuthers(){
        AsynchSendRequest asycSendHandler=new AsynchSendRequest();
        asycSendHandler.setCommunicationEventListener(new CommunicationEventListener() {
            @Override
            public boolean handleServerResponse(String response) {
                JSONObject jsonResponse = null;
                try {
                    jsonResponse = new JSONObject(response);
                    JSONObject data = jsonResponse.getJSONObject("data");
                    JSONArray allAuthors = data.getJSONArray("allAuthors");
                    for (int i=0; i < allAuthors.length(); i++)
                    {
                        JSONObject authorJson = allAuthors.getJSONObject(i);
                        String id = authorJson.getString("id");
                        String firstName = authorJson.getString("first_name");
                        String lastName = authorJson.getString("last_name");
                        Author author = new Author(id,firstName,lastName);
                        authors.add(author);
                    }
                }catch (JSONException e) {
                e.printStackTrace();
            }
                ArrayAdapter arrayAdapter = new ArrayAdapter<>(
                        GraphQL.this,
                        android.R.layout.simple_spinner_item,authors
                );
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spiner.setAdapter(arrayAdapter);
                return false;
            }
        });
        asycSendHandler.execute("{ \"query\": \"{allAuthors{id, first_name, last_name }}\" }","http://sym.iict.ch/api/graphql","application/json");
        return null ;
    }
    public String sendRequestAuthorPosts(Author author){
        AsynchSendRequest asycSendHandler=new AsynchSendRequest();
        asycSendHandler.setCommunicationEventListener(new CommunicationEventListener() {
            @Override
            public boolean handleServerResponse(String response) {

                return false;
            }
        });
        asycSendHandler.execute("{\n" +
                "\"query\": \"{allPostByAuthor(authorId:" + author.getId() +")}}","http://sym.iict.ch/api/graphql","application/json");
        return null ;}
}
class Author{
    String id;
    String first_name,last_name;
    public Author(String id,String first_name,String last_name){
        this.id=id;
        this.first_name=first_name;
        this.last_name=last_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Override
    public String toString() {
        return id +" "+first_name+" "+last_name;
    }
}
