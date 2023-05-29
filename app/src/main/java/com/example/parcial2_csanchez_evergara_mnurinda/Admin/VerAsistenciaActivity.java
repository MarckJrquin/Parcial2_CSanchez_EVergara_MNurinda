package com.example.parcial2_csanchez_evergara_mnurinda.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parcial2_csanchez_evergara_mnurinda.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VerAsistenciaActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AsistenciaListAdapter adapter;

    TextView txtViewPlaceEvent, txtViewContactEvent, txtViewNameEvent, txtViewDescEvent;

    ImageView imageViewEventPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_asistencia);
        
        this.initController();
        this.DataMapping();
    }

    private void initController() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        imageViewEventPhoto = (ImageView) findViewById(R.id.imageViewEventPhoto);
        txtViewPlaceEvent = (TextView) findViewById(R.id.txtViewPlaceEvent);
        txtViewContactEvent = (TextView) findViewById(R.id.txtViewContactEvent);
        txtViewNameEvent = (TextView) findViewById(R.id.txtViewNameEvent);
        txtViewDescEvent = (TextView) findViewById(R.id.txtViewDescEvent);
    }

    public void DataMapping(){
        // Obtener el ID pasado de la actividad anterior
        int eventId = getIntent().getIntExtra("eventId", 0);

        List<String[]> data = FileToList2("asistencia.txt"); // Obtén los datos del archivo de texto
        List<String[]> users = FileToList("credenciales.txt"); // Obtén los datos del archivo de texto
        List<String[]> events = FileToList("listaDeEventos.txt"); // Obtén los datos del archivo de texto

        adapter = new AsistenciaListAdapter(data, users, events, eventId);
        recyclerView.setAdapter(adapter);

        for (String[] event : events) {
            // Recorrer los elementos del arreglo con un bucle foreach
            if(String.valueOf(eventId).equals(event[0])){
                imageViewEventPhoto.setImageResource(imageCode(event[1]));
                txtViewNameEvent.setText(event[2]);
                txtViewDescEvent.setText(event[3]);
                txtViewPlaceEvent.setText(event[4]);
                txtViewContactEvent.setText(event[5]);
            }
        }
    }

    public int imageCode(String imageCode) {
        if(Integer.parseInt(imageCode) == 1){
            return R.drawable.d5to5;
        } else if (Integer.parseInt(imageCode) == 2) {
            return R.drawable.casajaguar_logo;
        } else if (Integer.parseInt(imageCode) == 3){
            return R.drawable.teatro_amador;
        }
        return 0;
    }

    private List<String[]> FileToList(String FILE_PATH) {
        List<String[]> dataF = new ArrayList<>();

        try {
            BufferedReader fin = new BufferedReader(new InputStreamReader(openFileInput(FILE_PATH)));
            String line;
            while ((line = fin.readLine()) != null) {
                String[] arrUsers = line.split("~");
                for (String strUser : arrUsers) {
                    String[] userFields = strUser.split("\\|");
                    dataF.add(userFields);
                }
            }
            fin.close();
        } catch (Exception e) {
            this.Notify("Error => " + e.getMessage());
        }

        return dataF;
    }


    private List<String[]> FileToList2(String FILE_PATH) {
        List<String[]> dataF = new ArrayList<>();
        Set<String> uniqueData = new HashSet<>(); // Conjunto para almacenar arreglos únicos

        try {
            BufferedReader fin = new BufferedReader(new InputStreamReader(openFileInput(FILE_PATH)));
            String line;
            while ((line = fin.readLine()) != null) {
                String[] arrUsers = line.split("~");
                for (String strUser : arrUsers) {
                    String[] userFields = strUser.split("\\|");
                    // Verificar si el arreglo ya existe en el conjunto de datos únicos
                    String dataKey = Arrays.toString(userFields);
                    if (!uniqueData.contains(dataKey)) {
                        dataF.add(userFields);
                        uniqueData.add(dataKey);
                    }
                }
            }
            fin.close();
        } catch (Exception e) {
            this.Notify("Error => " + e.getMessage());
        }

        return dataF;
    }


    private void Notify(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}