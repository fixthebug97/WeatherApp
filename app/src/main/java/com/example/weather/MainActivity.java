package com.example.weather;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
Button getData;
TextView cityname;
TextView desc;
TextView temp;
TextView switchText;
EditText location;
String BASE_URL="https://api.openweathermap.org";
Switch aSwitch;
ImageView icon;
    String imageIcon="";
    String description = "";
    int in;
    String newtemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData=findViewById(R.id.getData);
        cityname=findViewById(R.id.cityName);
        desc=findViewById(R.id.description);
        temp=findViewById(R.id.temperature);
        location=findViewById(R.id.Location);
        aSwitch=findViewById(R.id.switches);
        switchText=findViewById(R.id.switchText);
        icon=findViewById(R.id.icon);
        getdata();
        myswitch();
    }

    public void getdata(){

        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                        .build();
                IApi iApi=retrofit.create(IApi.class);
                final String city=location.getText().toString();

                if(!city.isEmpty()) {
                    Call<DATA> call = iApi.getData(city, "metric", "93f96e3aa7901e542a2a0828b2893f0e");

                    call.enqueue(new Callback<DATA>() {
                        @Override
                        public void onResponse(Call<DATA> call, Response<DATA> response) {
                            String main = "";


                            //Toast.makeText(MainActivity.this, "response code" + response.toString(), Toast.LENGTH_SHORT).show();




                                ArrayList<Weather> weather = response.body().getWeather();
                                for (int i = 0; i < weather.size(); i++) {

                                    main = response.body().getWeather().get(i).getMain();
                                    description = response.body().getWeather().get(i).getDescription();
                                    imageIcon = response.body().getWeather().get(i).getIcon();


                                }

                          //  Toast.makeText(MainActivity.this, ""+imageIcon, Toast.LENGTH_SHORT).show();
                                String name = response.body().getName();
                                String temperature = response.body().getCoord().getTemp();
                                double db = Double.parseDouble(temperature);
                                in = (int) Math.round(db);
                                newtemp = String.valueOf(in);
                                String newpath = "http://openweathermap.org/img/w/" + imageIcon + ".png";


                                Picasso.with(MainActivity.this).load(newpath).resize(250, 250).into(icon);
                                cityname.setText(name);
                                desc.setText(description);
                                aSwitch.setVisibility(View.VISIBLE);
                                switchText.setVisibility(View.VISIBLE);


                                if (aSwitch.isChecked()) {

                                    convert();
                                } else {
                                    temp.setText(newtemp + "\u00b0");
                                }

                                location.setText("");


                            }

                        @Override
                        public void onFailure(Call<DATA> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Something went wrong" + t, Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                else{

                    Toast.makeText(MainActivity.this, "Please Enter the city", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }


    public void myswitch(){


        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    switchText.setText("\u00b0F");
                    convert();
                   // 20°C × 9/5 + 32 = 68 °F
                }

                else{

                    switchText.setText("\u00b0C");
                    temp.setText(newtemp+"\u00b0");
                }
            }
        });
    }

    public void convert(){

        double Fvalue=in*(1.8)+32;
        int newint=(int)Math.round(Fvalue);
        String FstringValue=String.valueOf(newint);
        temp.setText(FstringValue+"\u00b0");
    }
}
