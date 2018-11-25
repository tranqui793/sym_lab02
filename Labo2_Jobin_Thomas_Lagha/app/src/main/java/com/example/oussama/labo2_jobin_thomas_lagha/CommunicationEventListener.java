/**
-----------------------------------------------------------------------------------------
Laboratory  : SYM - Laboratory n°2
File        : CommunicationEventListener.java
Author      : Lagha Oussama, Jobin Simon, Thomas Benjamin
Date        : 20.11.2018
Goal        : Implementation of the communication Listener interface for an HTTP server
Remark(s)   : -
----------------------------------------------------------------------------------------
*/


package com.example.oussama.labo2_jobin_thomas_lagha;

public interface CommunicationEventListener {

    /** Handle the server response
     * @param response the response from the server
     */
    boolean handleServerResponse(String response);
}