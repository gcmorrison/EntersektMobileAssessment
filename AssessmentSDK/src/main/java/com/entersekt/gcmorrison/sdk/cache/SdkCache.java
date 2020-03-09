package com.entersekt.gcmorrison.sdk.cache;

import android.content.Context;
import android.content.SharedPreferences;

import com.entersekt.gcmorrison.sdk.api.model.City;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class SdkCache {

    private final String CACHE_PREF_NAME = "sdkCache";
    private final String CITY_CACHE_KEY = "cityCache";
    private final SharedPreferences sharedPreferences;

    private final Gson gson;

    public SdkCache(Context context) {
        gson = new GsonBuilder().create();
        sharedPreferences = context.getSharedPreferences(CACHE_PREF_NAME, Context.MODE_PRIVATE);
    }

    public void store(List<City> result) {
        sharedPreferences.edit()
                .putString(CITY_CACHE_KEY, gson.toJson(result))
                .apply();
    }

    public List<City> get() {
        String cachedJson = sharedPreferences.getString(CITY_CACHE_KEY, null);
        if (cachedJson == null) {
            return null;
        }

        TypeToken<List<City>> typeToken = new TypeToken<List<City>>() {
        };
        return gson.fromJson(cachedJson, typeToken.getType());
    }
}
