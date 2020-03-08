package com.entersekt.gcmorrison.sdk.api;

import com.entersekt.gcmorrison.sdk.api.model.City;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface EntersektApi {
    /**
     * The results of this API call will be a full list of cities. This is a single call with a
     * single response. Therefore <code>Single of a list of cities</code> makes more sense than an
     * <code>Observable stream of cities</code>
     *
     * @return
     */
    Single<List<City>> getCities();
}
