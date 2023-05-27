package com.example.parcial2_csanchez_evergara_mnurinda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class RegistroActivity extends AppCompatActivity {

    EditText name, ID, age, password, username;

    RadioGroup usertype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        this.initControllers();
    }

    private void initControllers() {
        name = (EditText) findViewById(R.id.txtName);
        ID = (EditText) findViewById(R.id.txtID);
        age = (EditText) findViewById(R.id.txtAge);
        username = (EditText) findViewById(R.id.txtUser);
        password = (EditText) findViewById(R.id.txtPassword);
        usertype = (RadioGroup) findViewById(R.id.rgUsertype);
    }

    public void SaveUser(View v) {
        try {
            String nameEntered = name.getText().toString();
            String IDEntered = ID.getText().toString();
            String ageEntered = age.getText().toString();
            String usernameEntered = username.getText().toString();
            String passwordEntered = password.getText().toString();

            if (TextUtils.isEmpty(usernameEntered)) {
                username.setError("Ingrese el username");
                return;
            }

            if (TextUtils.isEmpty(passwordEntered)) {
                password.setError("Ingrese la contraseña del usuario");
                return;
            }

            if (TextUtils.isEmpty(nameEntered)) {
                name.setError("Ingrese un nombre");
                return;
            }

            if (TextUtils.isEmpty(IDEntered)) {
                ID.setError("Ingrese una cédula");
                return;
            }

            if (TextUtils.isEmpty(ageEntered)) {
                age.setError("Ingrese la edad del usuario");
                return;
            }

            String userType = "";
            int selectedRadioButtonId = usertype.getCheckedRadioButtonId();

            if (selectedRadioButtonId == R.id.rbAdmin) {
                userType = "Administrador";
            } else if (selectedRadioButtonId == R.id.rbCliente) {
                userType = "Cliente";
            } else {
                this.Notify("Seleccione el tipo de usuario");
                return;
            }

            String save =
                    nameEntered + "|" +
                    IDEntered + "|" +
                    ageEntered + "|" +
                    usernameEntered + "|" +
                    passwordEntered + "|" +
                    userType + "~";

            int res = SaveFile(save);

            if(res == 1){
                this.Notify("Usuario guardado");
            } else {
                this.Notify("Hubo un error, no se guardo el usuario");
            }

            startActivity(new Intent(this, MainActivity.class));

        }catch (Exception e) {
            this.Notify("Error => "+e.getMessage());
        }
    }

    public int SaveFile(String guardar){
        try {
            String OldText = "";

            File file = new File(getFilesDir(), "credenciales.txt");
            if (!file.exists()) {
                file.createNewFile();
            } else {
                BufferedReader bf = new BufferedReader(new InputStreamReader(openFileInput("credenciales.txt")));
                String text = bf.readLine();

                if (!text.isEmpty()) {
                    OldText = text;
                }
            }

            OutputStreamWriter fout = new OutputStreamWriter(openFileOutput("credenciales.txt", Context.MODE_PRIVATE));
            fout.write(OldText + guardar);
            fout.close();

            return 1;

        } catch (Exception ex) {
            Log.e("Ficheros", "Error al escribir fichero a memoria interna");
        }

        return 0;
    }

    private void Notify(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}