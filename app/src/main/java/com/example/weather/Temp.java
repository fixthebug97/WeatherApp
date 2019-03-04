package com.example.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class Temp {


    @SerializedName("temp")
    @Expose
    private String temp;


    public Temp(String temp) {
        this.temp = temp;
    }

    public String getTemp() {
        return temp;
    }
}
