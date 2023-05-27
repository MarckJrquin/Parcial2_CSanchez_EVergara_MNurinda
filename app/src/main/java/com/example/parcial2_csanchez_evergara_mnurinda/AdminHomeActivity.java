package com.example.parcial2_csanchez_evergara_mnurinda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AdminHomeActivity extends AppCompatActivity {

    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        this.initControllers();
        this.DataMapping();
    }

    private void initControllers() {
        textview = (TextView) findViewById(R.id.textview);
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

            textview.setText(textviewData);
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

    public void CreateEvent(View v){
        startActivity(new Intent(this, CrearEventoActivity.class));
    }

    public void ViewEventList(View v){
        startActivity(new Intent(this, VerListaDeEventosActivity.class));
    }

    private void notify(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}