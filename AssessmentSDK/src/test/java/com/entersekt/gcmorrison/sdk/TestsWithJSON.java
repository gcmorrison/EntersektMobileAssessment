package com.entersekt.gcmorrison.sdk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * A helper interface that enables tests to convert to and from JSON
 */
public interface TestsWithJSON {
    default Gson gson() {
        return new GsonBuilder().create();
    }

    default <D> D fromJson(String json, Class<D> destinationClass) {
        return gson().fromJson(json, destinationClass);
    }

    default String toJson(Object objectToConvert) {
        return gson().toJson(objectToConvert);
    }
}
