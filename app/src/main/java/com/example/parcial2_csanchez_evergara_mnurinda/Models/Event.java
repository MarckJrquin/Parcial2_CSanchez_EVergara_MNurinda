package com.example.parcial2_csanchez_evergara_mnurinda.Models;

public class Event {

    private int image;
    private String name;
    private String description;
    private String place;
    private String contact;

    public Event(int image, String name, String description, String place, String contact) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.place = place;
        this.contact = contact;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
