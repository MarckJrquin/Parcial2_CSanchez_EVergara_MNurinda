package com.example.parcial2_csanchez_evergara_mnurinda.Cliente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parcial2_csanchez_evergara_mnurinda.Admin.EventAdapter;
import com.example.parcial2_csanchez_evergara_mnurinda.MainActivity;
import com.example.parcial2_csanchez_evergara_mnurinda.Models.Event;
import com.example.parcial2_csanchez_evergara_mnurinda.R;

import java.util.List;

public class ClienteHomeActivity extends AppCompatActivity {

    TextView textView;
    ListView lstEvents;
    EventAdapter adapter;
    private List<Event> eventList;

    TextView lblClienteName,lblClienteUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_home);

        this.initControllers();
        this.DataMapping();
    }

    private void initControllers() {
        lblClienteName = (TextView) findViewById(R.id.lblClienteName);
        lblClienteUsername = (TextView) findViewById(R.id.lblClienteUsername);
    }

    private void DataMapping() {
        try{
            SharedPreferences loggedUser = getSharedPreferences("login", Context.MODE_PRIVATE);

            String name, ID, age, username, usertype;

            name = loggedUser.getString("name", "");
            ID = loggedUser.getString("ID", "");
            age = loggedUser.getString("age", "");
            username = loggedUser.getString("username", "");
            usertype = loggedUser.getString("usertype", "");

            String textviewData = "Nombre: " + name + ", " +
                    "Cedula: " + ID + ", " +
                    "Edad: " + age + ", " +
                    "Usuario: " + username + ", " +
                    "Tipo de usuario: " + usertype;

            lblClienteName.setText(name);
            lblClienteUsername.setText(username);
        } catch (Exception e){
            this.notify("Error => " + e.getMessage());
        }
    }

    public void Logout(View v){
        SharedPreferences loggedUser = getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = loggedUser.edit();

        editor.putString("name", "");
        editor.putString("ID", "");
        editor.putString("age", "");
        editor.putString("username", "");
        editor.putString("password", "");

        editor.commit();

        startActivity(new Intent(this, MainActivity.class));
    }

    public void ViewEventList(View v){
        startActivity(new Intent(this, ListaClienteActivity.class));
    }

    private void notify(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}