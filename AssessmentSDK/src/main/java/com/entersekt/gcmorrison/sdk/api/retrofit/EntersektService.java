package com.entersekt.gcmorrison.sdk.api.retrofit;

import com.entersekt.gcmorrison.sdk.api.response.GetCitiesResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface EntersektService {
    @GET
    Single<GetCitiesResponse> cities(@Url String queryUrl);
}
