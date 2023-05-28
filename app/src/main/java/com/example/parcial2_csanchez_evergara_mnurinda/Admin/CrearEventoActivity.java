package com.example.parcial2_csanchez_evergara_mnurinda.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.parcial2_csanchez_evergara_mnurinda.IDGenerator.FileManager;
import com.example.parcial2_csanchez_evergara_mnurinda.IDGenerator.IDGenerator;
import com.example.parcial2_csanchez_evergara_mnurinda.IDGenerator.IDVerifier;
import com.example.parcial2_csanchez_evergara_mnurinda.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class CrearEventoActivity extends AppCompatActivity implements View.OnClickListener {

    EditText txtNameEvent, txtDescriptionEvent, txtContactEvent;
    ImageView lastSelectedImage;
    int selectedValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_evento);

        this.iniController();
    }

    private void iniController() {
        txtNameEvent = (EditText) findViewById(R.id.txtNameEvent);
        txtDescriptionEvent = (EditText) findViewById(R.id.txtDescriptionEvent);
        txtContactEvent = (EditText) findViewById(R.id.txtContactEvent);

        ImageView ivOption1 = findViewById(R.id.ivOption1);
        ImageView ivOption2 = findViewById(R.id.ivOption2);
        ImageView ivOption3 = findViewById(R.id.ivOption3);

        ivOption1.setOnClickListener(this);
        ivOption2.setOnClickListener(this);
        ivOption3.setOnClickListener(this);
    }

    public void SaveEvents(View v) {
        try {

            //int image =
            String nameEvent = txtNameEvent.getText().toString();
            String descriptionEvent = txtDescriptionEvent.getText().toString();
            String contactEvent = txtContactEvent.getText().toString();
            String placeEvent = "";

            if (TextUtils.isEmpty(nameEvent)) {
                txtNameEvent.setError("Ingrese el nombre del evento");
                return;
            }

            if (TextUtils.isEmpty(descriptionEvent)) {
                txtDescriptionEvent.setError("Ingrese una descripción del evento");
                return;
            }

            if (TextUtils.isEmpty(contactEvent)) {
                txtContactEvent.setError("Ingrese un contacto del evento");
                return;
            }


            int id;
            boolean isRepeated;
            do {
                id = IDGenerator.generarID();
                isRepeated = IDVerifier.verificarID(id, "eventIDs.txt");
            } while (isRepeated);

            FileManager.guardarID(id, "eventIDs.txt");


            if (selectedValue == 1) {
                placeEvent = "5to5";
            } else if (selectedValue == 2) {
                placeEvent = "Casa jaguar";
            } else if (selectedValue == 3) {
                placeEvent = "Teatro Amador";
            } else {
                this.Notify("Seleccione una imagen");
                return;
            }

            String save =
                    id + "|" +
                    selectedValue + "|" +
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

    @Override
    public void onClick(View view) {
        onImageSelected(view);
    }

    public void onImageSelected(View view) {
        ImageView selectedImage = (ImageView) view;

        // Deseleccionar la última imagen seleccionada
        if (lastSelectedImage != null) {
            lastSelectedImage.setSelected(false);
        }

        // Seleccionar la imagen actual
        selectedImage.setSelected(true);
        lastSelectedImage = selectedImage;

        // Obtener el valor asociado a la imagen seleccionada
        selectedValue = Integer.parseInt(selectedImage.getTag().toString());

        // Acciones a realizar cuando se selecciona una imagen
        // Puedes usar la variable 'selectedImage' para obtener información sobre la imagen seleccionada
    }

    private void Notify(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}