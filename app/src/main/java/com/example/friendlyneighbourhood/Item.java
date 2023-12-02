package com.example.friendlyneighbourhood;

public class Item {
    String name, phone, servicesOffered, locality;

    public Item(String name, String phone, String servicesOffered, String locality) {
        this.name = name;
        this.phone = phone;
        this.servicesOffered = servicesOffered;
        this.locality = locality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getServicesOffered() {
        return servicesOffered;
    }

    public void setServicesOffered(String servicesOffered) {
        this.servicesOffered = servicesOffered;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }
}
