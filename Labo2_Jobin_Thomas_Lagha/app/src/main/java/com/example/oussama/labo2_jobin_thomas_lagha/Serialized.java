package com.example.oussama.labo2_jobin_thomas_lagha;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class  Serialized extends Activity implements View.OnClickListener {

    //Test variable
    private Person person;

    //UI
    private Button btn_login;
    //private EditText et_response;

    //Conection
    private BufferedReader in;
    private PrintWriter out;
    private URL serverURL;
    private HttpURLConnection serverConnection;

    //Serialization/deserialization
    private final Gson gson = new GsonBuilder().create();


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);
        btn_login=findViewById(R.id.btn_login);
        //et_response=findViewById(R.id.responseText);
        btn_login.setOnClickListener(this);

        connect("http://sym.iict.ch/rest/txt");

    }

    @Override
    protected void onDestroy() {
        try {
            close();
        } catch (IOException e) {
            System.out.println("Can't close the connection");
            e.printStackTrace();
        }
        super.onDestroy();
    }

    /** Connect to the url passed in argument
     *  @param URL the URL to connect to
     */
    public void connect(final String URL){
        try {
            serverURL = new URL(URL);
            serverConnection = (HttpURLConnection) serverURL.openConnection();

            in = new BufferedReader(new InputStreamReader(serverConnection.getInputStream()));
            out = new PrintWriter(serverConnection.getOutputStream());

        } catch (MalformedURLException e) {
            System.out.println("Error in the URL");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error while creating streamReader/Writer");
            e.printStackTrace();
        }
    }

    /** Close the connection in needed
     */
    public void close() throws IOException {
        if(in != null)
            in.close();
        if(out != null)
            out.close();
    }

    /** Check weather or not the server response was right or not and display a toast indicating it
     * @return a boolean indicating if the server response was correct or not
     */
    public boolean test(){
        person = new Person("Jean", "Paul");
        String serializedData = gson.toJson(person);
        writeServer(serializedData);

        Person tmp = null;
        try {
            tmp = gson.fromJson(readServer(), Person.class);
        } catch (IOException e) {
            System.out.println("Can't read from server");
            e.printStackTrace();
        }

        if(tmp.equals(person)){
            Toast.makeText(getApplicationContext(),"Serialization process is done: success",Toast.LENGTH_LONG).show();
            return true;
        }else{
            Toast.makeText(getApplicationContext(),"Serialization process is done: failure",Toast.LENGTH_LONG).show();
            return false;
        }

        //et_response.setVisibility(View.VISIBLE);
        //et_response.setText(response);
    }

    /** Read the data from the server
     *  @return the data send by the server
     */
    public String readServer() throws IOException{
        return in.readLine();
    }

    /** Send the String in parameter to the server
     *  @param str The String to send
     */
    public void writeServer(String str){
        out.write(str);
        out.write('\n');
        out.flush();
    }

    /** Send the serialized data if the click was from btn_login
     *  @param v The view on which display data
     */
    @Override
    public void onClick(View v) {
        if(v==btn_login)
        {
            test();
        }
    }
}