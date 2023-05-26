package com.example.parcial2_csanchez_evergara_mnurinda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
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

            String userType = "";
            int selectedRadioButtonId = usertype.getCheckedRadioButtonId();

            if (selectedRadioButtonId == R.id.rbAdmin) {
                userType = "Administrador";
            } else if (selectedRadioButtonId == R.id.rbCliente) {
                userType = "Cliente";
            }

            String save =
                    name.getText().toString() + "|" +
                    ID.getText().toString() + "|" +
                    age.getText().toString() + "|" +
                    username.getText().toString() + "|" +
                    password.getText().toString() + "|" +
                    userType + "~";

            int res = SaveFile(save);

            if(res == 1){
                this.Notify("Cooool, se guardo el Dato io");
            } else {
                this.Notify("Exploto Iooooo, correeeeeeee ");
            }

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