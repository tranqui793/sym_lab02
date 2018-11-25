/**
 -----------------------------------------------------------------------------------------
 Laboratory  : SYM - Laboratory n°2
 File        : GraphQL.java
 Author      : Lagha Oussama, Jobin Simon, Thomas Benjamin
 Date        : 20.11.2018
 Goal        : Implementation of the an Author class for the GraphQL transmission
 Remark(s)   : -
 ----------------------------------------------------------------------------------------
 */

package com.example.oussama.labo2_jobin_thomas_lagha;

public class Author{
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