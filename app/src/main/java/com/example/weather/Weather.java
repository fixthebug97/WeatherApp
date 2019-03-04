package com.example.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class Weather {

    public Weather(String main, String description) {
        this.main = main;
        this.description = description;
    }

    @SerializedName("main")
    @Expose
    private String main;

    @SerializedName("description")
    @Expose
    private String description;

    public String getMain() {
        return main;
    }


    public String getDescription() {
        return description;
    }


}
