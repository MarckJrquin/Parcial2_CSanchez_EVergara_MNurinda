package com.example.parcial2_csanchez_evergara_mnurinda.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parcial2_csanchez_evergara_mnurinda.MainActivity;
import com.example.parcial2_csanchez_evergara_mnurinda.R;

public class AdminHomeActivity extends AppCompatActivity {

    TextView lblAdminName,lblAdminUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        this.initControllers();
        this.DataMapping();
    }

    private void initControllers() {
        lblAdminName = (TextView) findViewById(R.id.lblAdminName);
        lblAdminUsername = (TextView) findViewById(R.id.lblAdminUsername);
    }

    private void DataMapping() {
        try{
            SharedPreferences loggedUser = getSharedPreferences("login", Context.MODE_PRIVATE);

            String name, ID, age, username, usertype;

            name = loggedUser.getString("userID", "");
            name = loggedUser.getString("name", "");
            ID = loggedUser.getString("ID", "");
            age = loggedUser.getString("age", "");
            username = loggedUser.getString("username", "");
            usertype = loggedUser.getString("usertype", "");

            lblAdminName.setText(name);
            lblAdminUsername.setText(username);
        } catch (Exception e){
            this.notify("Error => " + e.getMessage());
        }
    }

    public void Logout(View v){
        SharedPreferences loggedUser = getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = loggedUser.edit();

        editor.putString("userID", "");
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