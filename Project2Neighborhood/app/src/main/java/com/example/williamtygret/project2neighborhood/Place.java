package com.example.williamtygret.project2neighborhood;

/**
 * Created by williamtygret on 2/2/16.
 */
public class Place {
    public int id;
    public String name;
    public String address;
    public String borough;
    public String neighborhood;
    public String type;

    public Place(int id, String name, String address, String borough, String neighborhood, String type) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.borough = borough;
        this.neighborhood = neighborhood;
        this.type = type;
    }
}
