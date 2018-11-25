/**
 -----------------------------------------------------------------------------------------
 Laboratory  : SYM - Laboratory n°2
 File        : GraphQL.java
 Author      : Lagha Oussama, Jobin Simon, Thomas Benjamin
 Date        : 20.11.2018
 Goal        : Implementation of the delayed data transfer
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DelayedTransmission extends Activity implements View.OnClickListener {

    private static final String serverUrl = "http://sym.iict.ch/rest/txt";

    private EditText textToSend, serverResponse;
    private Button btnSend;

    private String userRequest;

    // Queue de requêtes à envoyer
    private List<String> userRequests;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.delayed);

        // liaison des elements a la vue
        textToSend = findViewById(R.id.textToSend);
        btnSend = findViewById(R.id.btnSend);
        serverResponse = findViewById(R.id.serverResponse);

        btnSend.setOnClickListener(this);

        // creation d'un thread s'occupe d'envoyer
        // les requetes de la liste d'attente tant que celle-ci
        // n'est pas vide (des qu'une nouvelle requete est entree
        // par l'utilisateur)
        userRequests = Collections.synchronizedList(new ArrayList<String>());
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    while (!userRequests.isEmpty()) {
                        sendRequest("Request : " + userRequests.get(0), serverUrl);
                        // Il faudrait ne supprimer l'element
                        // que si ce dernier a pu etre envoyé,
                        // donc tester si on a pu contacter le serveur...
                        userRequests.remove(0);
                    }
                    // Check regulier (on ne cherche pas ici
                    // a envoyer des que le serveur est dispo)
                    try {
                        // On attend suffisemment longtemps
                        // pour simuler une attente de connection
                        Thread.sleep(20000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

    @Override
    public void onClick(View v) {
        // recuperation de la requete de l'utilisateur
        if(v == btnSend) {
            userRequests.add(textToSend.getText().toString());
        }
    }

    /** Send a request to the url in paramter
     * @param request the request to send
     * @param url the url to which send the request
     */
    public void sendRequest(String request, String url) {
        // utilisation de l'envoi utilisé au point 1
        AsynchSendRequest asycSendHandler = new AsynchSendRequest();
        asycSendHandler.setCommunicationEventListener(new CommunicationEventListener() {
            @Override
            public boolean handleServerResponse(String response) {
                serverResponse.setVisibility(View.VISIBLE);
                serverResponse.setText(response);
                return false;
            }
        });
        asycSendHandler.execute(request, url, "text/plain");
    }
}
