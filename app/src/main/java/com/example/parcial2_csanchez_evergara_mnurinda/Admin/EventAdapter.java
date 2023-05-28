package com.example.parcial2_csanchez_evergara_mnurinda.Admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.parcial2_csanchez_evergara_mnurinda.Models.Event;
import com.example.parcial2_csanchez_evergara_mnurinda.R;

import java.util.List;

public class EventAdapter extends ArrayAdapter<Event> {

    List<Event> eventList;

    public EventAdapter(Context context, List<Event> events){
        super(context, R.layout.listview_events, events);
        eventList = events;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.listview_events, null);

        ImageView image = (ImageView) item.findViewById(R.id.imageViewEventPhoto);
        image.setImageResource(eventList.get(position).getImage());

        TextView eventName = (TextView) item.findViewById(R.id.textViewNameEvent);
        eventName.setText(eventList.get(position).getName());

        TextView eventDescription = (TextView) item.findViewById(R.id.textViewDescEvent);
        eventDescription.setText(eventList.get(position).getDescription());

        TextView eventPlace = (TextView) item.findViewById(R.id.textViewPlaceEvent);
        eventPlace.setText(eventList.get(position).getPlace());

        TextView eventContact = (TextView) item.findViewById(R.id.textViewContactEvent);
        eventContact.setText(eventList.get(position).getContact());

        return item;
    }

}
