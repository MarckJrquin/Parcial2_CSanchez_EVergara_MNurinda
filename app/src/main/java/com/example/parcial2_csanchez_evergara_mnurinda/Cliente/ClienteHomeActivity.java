package com.example.parcial2_csanchez_evergara_mnurinda.Cliente;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parcial2_csanchez_evergara_mnurinda.Cliente.ListaClienteAdapter;
import com.example.parcial2_csanchez_evergara_mnurinda.Admin.EventAdapter;
import com.example.parcial2_csanchez_evergara_mnurinda.IDGenerator.FileManager;
import com.example.parcial2_csanchez_evergara_mnurinda.MainActivity;
import com.example.parcial2_csanchez_evergara_mnurinda.Models.Event;
import com.example.parcial2_csanchez_evergara_mnurinda.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class ClienteHomeActivity extends AppCompatActivity {

    ListView lstEvents;
    ListaClienteAdapter adapter;
    private List<Event> eventList;

    TextView lblClienteName,lblClienteUsername;

    //EventAdapter adapter;
    //private List<Event> eventList;

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
        lstEvents = (ListView)findViewById(R.id.lstEventsC);
    }

    private void DataMapping() {
        try{
            SharedPreferences loggedUser = getSharedPreferences("login", Context.MODE_PRIVATE);

            String userID, name, ID, age, username, usertype;

            userID = loggedUser.getString("userID", "");
            name = loggedUser.getString("name", "");
            ID = loggedUser.getString("ID", "");
            age = loggedUser.getString("age", "");
            username = loggedUser.getString("username", "");
            usertype = loggedUser.getString("usertype", "");

            lblClienteName.setText(name);
            lblClienteUsername.setText(username);

            adapter = new ListaClienteAdapter(getApplicationContext(), FileToList(), userID);
            lstEvents.setAdapter(adapter);

            lstEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Obtener el elemento seleccionado
                    Event eventoSeleccionado = (Event) parent.getItemAtPosition(position);

                    // Obtener los valores específicos del elemento seleccionado
                    String eventId = String.valueOf(eventoSeleccionado.getEventID());
                    String eventName = eventoSeleccionado.getName();

                    Notify(eventId);

                    String save = eventId + "|" + userID + "~" ;
                    int res = SaveFile(save);

                    if(res == 1){
                        Notify("Has asistido al evento "+ eventName);
                    } else {
                        Notify("Error, no se guardo la asistencia");
                    }
                }
            });
        } catch (Exception e){
            this.Notify("Error => " + e.getMessage());
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

    public int SaveFile(String guardar){
        try {
            String OldText = "";

            File file = new File(getFilesDir(), "asistencia.txt");
            if (!file.exists()) {
                file.createNewFile();
            } else {
                BufferedReader bf = new BufferedReader(new InputStreamReader(openFileInput("asistencia.txt")));
                String text = bf.readLine();

                if (!text.isEmpty()) {
                    OldText = text;
                }
            }

            OutputStreamWriter fout = new OutputStreamWriter(openFileOutput("asistencia.txt", Context.MODE_PRIVATE));
            fout.write(OldText + guardar);
            fout.close();

            return 1;

        } catch (Exception ex) {
            Log.e("Ficheros", "Error al escribir fichero a memoria interna");
        }

        return 0;
    }

    private List<Event> FileToList() {
        List<Event> events = new ArrayList<Event>();

        try {
            BufferedReader fin = new BufferedReader( new InputStreamReader( openFileInput("listaDeEventos.txt") ));
            String data = fin.readLine();

            String[] arrEvents = data.split("~");

            for( String strEvent : arrEvents ){

                String[] eventFields = strEvent.split("\\|");

                Event event = new Event(
                        Integer.parseInt(eventFields[0]),
                        imageCode(eventFields[1]),
                        eventFields[2],
                        eventFields[3],
                        eventFields[4],
                        eventFields[5]
                );
                events.add(event);
            }

        }catch (Exception e) {
            this.Notify("Aun no hay eventos");
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

    public void ViewEventList(View v){
        startActivity(new Intent(this, ListaClienteActivity.class));
    }

    private void Notify(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}