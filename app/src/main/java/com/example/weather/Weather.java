package com.example.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class Weather {

    public Weather(String main, String description,String icon) {
        this.main = main;
        this.description = description;
        this.icon=icon;
    }

    @SerializedName("main")
    @Expose
    private String main;

    @SerializedName("icon")
    @Expose
    private String icon;

    @SerializedName("description")
    @Expose
    private String description;

    public String getMain() {
        return main;
    }


    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }
}
