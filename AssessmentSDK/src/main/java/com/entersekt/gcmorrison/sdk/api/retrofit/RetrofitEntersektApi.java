package com.entersekt.gcmorrison.sdk.api.retrofit;

import com.entersekt.gcmorrison.sdk.api.EntersektApi;
import com.entersekt.gcmorrison.sdk.api.model.City;
import com.entersekt.gcmorrison.sdk.api.response.GetCitiesResponse;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitEntersektApi implements EntersektApi {

    private final EntersektService service;

    public RetrofitEntersektApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.localhost.com") // Not used in this example
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        service = retrofit.create(EntersektService.class);
    }

    @Override
    public Single<List<City>> getCities() {
        return service.cities("http://www.mocky.io/v2/5b7e8bc03000005c0084c210")
                .map(GetCitiesResponse::getCities);
    }
}
