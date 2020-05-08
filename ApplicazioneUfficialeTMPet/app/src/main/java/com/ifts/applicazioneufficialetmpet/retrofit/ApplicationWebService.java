package com.ifts.applicazioneufficialetmpet.retrofit;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApplicationWebService extends Application {
    public static final String BASE_URL = "http://192.168.1.243:8080";
    public static Retrofit retrofit;
    //per deserializzare la data
    Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
        public Date deserialize(JsonElement json, Type typeofT, JsonDeserializationContext context)
                throws JsonParseException {
            return new Date(json.getAsLong());
        }
    }).create();

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))

                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
