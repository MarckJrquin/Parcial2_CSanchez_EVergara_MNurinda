package com.example.parcial2_csanchez_evergara_mnurinda.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.parcial2_csanchez_evergara_mnurinda.Cliente.ClienteHomeActivity;
import com.example.parcial2_csanchez_evergara_mnurinda.Models.Event;
import com.example.parcial2_csanchez_evergara_mnurinda.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class VerListaDeEventosActivity extends AppCompatActivity {

    ListView lstEvents;

    EventAdapter adapter;

    private List<Event> eventList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_lista_de_eventos);
        this.initControllers();
        this.DataMapping();
    }

    private void initControllers() {
        lstEvents = (ListView)findViewById(R.id.lstEvents);
    }

    private void DataMapping() {

        adapter = new EventAdapter(getApplicationContext(), FileToList());
        lstEvents.setAdapter(adapter);

        lstEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtener el elemento seleccionado
                Event eventoSeleccionado = (Event) parent.getItemAtPosition(position);

                // Obtener el ID del elemento seleccionado
                int eventId = eventoSeleccionado.getEventID();

                // Crear un Intent para iniciar la siguiente actividad
                Intent intent = new Intent(VerListaDeEventosActivity.this, VerAsistenciaActivity.class);

                // Pasar el ID del elemento seleccionado a la siguiente actividad
                intent.putExtra("eventId", eventId);

                // Iniciar la siguiente actividad
                startActivity(intent);
            }
        });
    }

    private void Notify(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private List<Event> FileToList() {
        List<Event> events = new ArrayList<Event>();

        try {
            BufferedReader fin = new BufferedReader( new InputStreamReader( openFileInput("listaDeEventos.txt") ));
            String data = fin.readLine();

            String[] arrEvents = data.split("~");

            for( String strEvent : arrEvents ){

                String[] userFields = strEvent.split("\\|");

                Event event = new Event(
                        Integer.parseInt(userFields[0]),
                        imageCode(userFields[1]),
                        userFields[2],
                        userFields[3],
                        userFields[4],
                        userFields[5]
                );
                events.add(event);
            }

        }catch (Exception e) {
            this.Notify("No se han creado eventos");
        }

        return events;
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
}