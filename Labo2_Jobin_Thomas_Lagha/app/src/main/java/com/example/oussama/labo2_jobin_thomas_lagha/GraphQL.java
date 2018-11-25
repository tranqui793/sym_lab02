/**
-----------------------------------------------------------------------------------------
Laboratory  : SYM - Laboratory n°2
File        : GraphQL.java
Author      : Lagha Oussama, Jobin Simon, Thomas Benjamin
Date        : 20.11.2018
Goal        : Implementation of the GraphQL data transfer
Remark(s)   : -
----------------------------------------------------------------------------------------
*/

package com.example.oussama.labo2_jobin_thomas_lagha;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


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

    /** Send a request to the url in paramter for all the authors
     */
    public void sendRequestAllAuthers(){
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
    }

    /** Send a request to the url in paramter for all the Author author posts
     * @param author The author that we want to discover the posts
     */
    public void sendRequestAuthorPosts(Author author){
        AsynchSendRequest asycSendHandler=new AsynchSendRequest();
        asycSendHandler.setCommunicationEventListener(new CommunicationEventListener() {
            @Override
            public boolean handleServerResponse(String response) {

                return false;
            }
        });
        asycSendHandler.execute("{\n" +
                "\"query\": \"{allPostByAuthor(authorId:" + author.getId() +")}}","http://sym.iict.ch/api/graphql","application/json");
    }
}

