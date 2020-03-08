package com.entersekt.gcmorrison.sdk.api.response;

import androidx.annotation.NonNull;

import com.entersekt.gcmorrison.sdk.api.model.City;
import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

/**
 * The response for the GetCities API call. This class will only be created on a successful completion
 * of the call, which means if cities are null there were no cities. In this case, an empty list
 * makes more sense than a null list
 */
public class GetCitiesResponse {
    private static final List<City> noCities = Collections.emptyList();
    @SerializedName("cities")
    private List<City> cities;

    private GetCitiesResponse() {
    }

    @NonNull
    public List<City> getCities() {
        return cities != null ? cities : noCities;
    }
}
