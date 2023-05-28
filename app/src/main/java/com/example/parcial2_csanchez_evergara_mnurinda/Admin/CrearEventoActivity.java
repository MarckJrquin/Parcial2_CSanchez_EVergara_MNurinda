package com.example.parcial2_csanchez_evergara_mnurinda.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.parcial2_csanchez_evergara_mnurinda.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class CrearEventoActivity extends AppCompatActivity {

    EditText txtNameEvent, txtDescriptionEvent, txtPlaceEvent, txtContactEvent;
    RadioGroup selectedImageRG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_evento);

        this.iniController();
    }

    private void iniController() {
        txtNameEvent = (EditText) findViewById(R.id.txtNameEvent);
        txtDescriptionEvent = (EditText) findViewById(R.id.txtDescriptionEvent);
        txtPlaceEvent = (EditText) findViewById(R.id.txtPlaceEvent);
        txtContactEvent = (EditText) findViewById(R.id.txtContactEvent);
        selectedImageRG = (RadioGroup) findViewById(R.id.radioGroupOptions);
    }

    public void SaveEvents(View v) {
        try {

            //int image =
            String nameEvent = txtNameEvent.getText().toString();
            String descriptionEvent = txtDescriptionEvent.getText().toString();
            String placeEvent = txtPlaceEvent.getText().toString();
            String contactEvent = txtContactEvent.getText().toString();

            if (TextUtils.isEmpty(nameEvent)) {
                txtNameEvent.setError("Ingrese el nombre del evento");
                return;
            }

            if (TextUtils.isEmpty(descriptionEvent)) {
                txtDescriptionEvent.setError("Ingrese una descripción del evento");
                return;
            }

            if (TextUtils.isEmpty(placeEvent)) {
                txtPlaceEvent.setError("Ingrese el lugar del evento");
                return;
            }

            if (TextUtils.isEmpty(contactEvent)) {
                txtContactEvent.setError("Ingrese un contacto del evento");
                return;
            }


            int imageOption;
            int selectedRadioButtonId = selectedImageRG.getCheckedRadioButtonId();

            if (selectedRadioButtonId == R.id.rbOption1) {
                imageOption = 1;
            } else if (selectedRadioButtonId == R.id.rbOption2) {
                imageOption = 2;
            } else if (selectedRadioButtonId == R.id.rbOption3) {
                imageOption = 3;
            } else {
                this.Notify("Seleccione una imagen");
                return;
            }

            String save =
                    imageOption + "|" +
                    nameEvent + "|" +
                    descriptionEvent + "|" +
                    placeEvent + "|" +
                    contactEvent + "~";

            int res = SaveFile(save);

            if(res == 1){
                this.Notify("Se guardo el evento");
            } else {
                this.Notify("Error, no se guardo el evento");
            }

            startActivity(new Intent(this, AdminHomeActivity.class));

        }catch (Exception e) {
            this.Notify("Error => "+e.getMessage());
        }
    }

    public int SaveFile(String guardar){
        try {
            String OldText = "";

            File file = new File(getFilesDir(), "listaDeEventos.txt");
            if (!file.exists()) {
                file.createNewFile();
            } else {
                BufferedReader bf = new BufferedReader(new InputStreamReader(openFileInput("listaDeEventos.txt")));
                String text = bf.readLine();

                if (!text.isEmpty()) {
                    OldText = text;
                }
            }

            OutputStreamWriter fout = new OutputStreamWriter(openFileOutput("listaDeEventos.txt", Context.MODE_PRIVATE));
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