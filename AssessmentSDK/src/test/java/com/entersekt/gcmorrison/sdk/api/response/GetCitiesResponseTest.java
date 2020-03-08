package com.entersekt.gcmorrison.sdk.api.response;

import com.entersekt.gcmorrison.sdk.TestsWithJSON;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class GetCitiesResponseTest implements TestsWithJSON {

    private GetCitiesResponse classUnderTest;

    @Test
    public void emptyResponse() {
        // GIVEN
        String emptyResponse = "{}";

        // WHEN
        classUnderTest = fromJson(emptyResponse, GetCitiesResponse.class);

        // THEN
        assertNotNull(classUnderTest.getCities());
        assertTrue(classUnderTest.getCities().isEmpty());
    }

    @Test
    public void responseWithCities() {
        // GIVEN
        String citiesResponse = "{ \"cities\": [{ \"id\": 1, \"name\": \"Cape Town\"}]}";

        // WHEN
        classUnderTest = fromJson(citiesResponse, GetCitiesResponse.class);

        // THEN
        assertFalse(classUnderTest.getCities().isEmpty());
    }
}