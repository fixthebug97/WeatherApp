package com.example.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DATA {

    @SerializedName("name")
    @Expose
    private String name;


    @SerializedName("main")
    @Expose
    private Temp coord;

    @SerializedName("weather")
    @Expose
    private ArrayList<Weather> weather;

    public DATA(Temp coord, ArrayList<Weather> weather,String name) {
        this.coord = coord;
        this.weather = weather;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public Temp getCoord() {
        return coord;
    }


    public ArrayList<Weather> getWeather() {
        return weather;
    }


}
