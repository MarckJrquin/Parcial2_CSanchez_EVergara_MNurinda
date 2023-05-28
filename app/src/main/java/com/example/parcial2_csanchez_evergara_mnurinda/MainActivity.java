package com.example.parcial2_csanchez_evergara_mnurinda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parcial2_csanchez_evergara_mnurinda.Admin.AdminHomeActivity;
import com.example.parcial2_csanchez_evergara_mnurinda.Cliente.ClienteHomeActivity;
import com.example.parcial2_csanchez_evergara_mnurinda.Models.Users;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText user, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initControllers();
        this.ValidateLogin();
    }

    private void initControllers() {
        user = (EditText) findViewById(R.id.txtUser);
        password = (EditText) findViewById(R.id.txtPassword);
    }

    private void ValidateLogin() {
        SharedPreferences loggedUser = getSharedPreferences("login", Context.MODE_PRIVATE);
        String loggedInUser = loggedUser.getString("username", "");
        String loggedInUserType = loggedUser.getString("usertype", "");

        if (!loggedInUser.equals("")) {

            if(loggedInUserType.equals("Administrador")) {
                startActivity(new Intent(this, AdminHomeActivity.class));
            }else if (loggedInUserType.equals("Cliente")) {
                startActivity(new Intent(this, ClienteHomeActivity.class));
            }
        }
    }

    public void LogUser(View view) {
        try {
            String userEntered = user.getText().toString();
            String passwordEntered = password.getText().toString();

            if (TextUtils.isEmpty(userEntered)) {
                user.setError("Ingrese el username");
                return;
            }

            if (TextUtils.isEmpty(passwordEntered)) {
                password.setError("Ingrese una contraseña");
                return;
            }

            List<Users> users = FileToList();

            boolean logged = false;

            for (Users userItem : users){

                if(userEntered.equals(userItem.getUsername()) && passwordEntered.equals(userItem.getPassword()) ){
                    SharedPreferences spUser = getSharedPreferences("login", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = spUser.edit();

                    editor.putString("name", userItem.getName());
                    editor.putString("ID", userItem.getID());
                    editor.putString("age", String.valueOf(userItem.getAge()));
                    editor.putString("username", userItem.getUsername());
                    editor.putString("password", userItem.getPassword());
                    editor.putString("usertype", userItem.getUsertype());

                    editor.commit();

                    logged = true;

                    if(userItem.getUsertype().equals("Administrador")){
                        startActivity(new Intent(this, AdminHomeActivity.class));
                    } else if(userItem.getUsertype().equals("Cliente")){
                        startActivity(new Intent(this, ClienteHomeActivity.class));
                    }
                }
            }

            if(!logged) {
                this.Notify("Xa, tu no existe aqui io, largateeeeeee");
            }

        } catch (Exception e){
            this.Notify("Error => " + e.getMessage());
        }
    }

    private void Notify(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private List<Users> FileToList() {
        List<Users> users = new ArrayList<Users>();

        try {
            BufferedReader fin = new BufferedReader( new InputStreamReader( openFileInput("credenciales.txt") ));
            String data = fin.readLine();

            String[] arrUsers = data.split("~");

            for( String strUser : arrUsers ){

                String[] userFields = strUser.split("\\|");

                Users user = new Users(
                        userFields[0],
                        userFields[1],
                        Integer.parseInt(userFields[2]),
                        userFields[3],
                        userFields[4],
                        userFields[5]
                );

                users.add(user);
            }

        }catch (Exception e) {
            this.Notify("Error => " + e.getMessage());
        }

        return users;
    }

    public void registrarPersona(View v){
        startActivity(new Intent(this, RegistroActivity.class));
    }
}