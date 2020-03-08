package com.entersekt.gcmorrison.sdk.api.model;

import com.entersekt.gcmorrison.sdk.RandomTestData;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class CityTest implements RandomTestData {
    private City classUnderTest;

    @Test
    public void canBuildCityWithNoMalls() {
        // GIVEN
        int id = randomInt();
        String name = randomString();

        // WHEN
        classUnderTest = new City.Builder()
                .setId(id)
                .setName(name)
                .build();

        // THEN
        assertEquals(id, classUnderTest.getId());
        assertEquals(name, classUnderTest.getName());
        assertTrue(classUnderTest.getMalls().isEmpty());
    }

    @Test
    public void canBuildCityWithNullMalls() {
        // GIVEN / WHEN
        classUnderTest = new City.Builder()
                .setId(randomInt())
                .setName(randomString())
                .addMalls(null, null, null)
                .build();

        // THEN
        assertTrue(classUnderTest.getMalls().isEmpty());
    }

    @Test
    public void canBuildCityWithMalls() {
        // GIVEN
        Mall[] malls = new Mall[]{mock(Mall.class), mock(Mall.class)};

        // WHEN
        classUnderTest = new City.Builder()
                .setId(randomInt())
                .setName(randomString())
                .addMalls(malls)
                .build();

        // THEN
        assertEquals(malls.length, classUnderTest.getMalls().size());
    }

    @Test
    public void canBuildCityFromCity() {
        // GIVEN
        City rockNRoll = new City.Builder()
                .setId(randomInt())
                .setName(randomString())
                .addMalls(mock(Mall.class)).build();

        // WHEN
        classUnderTest = new City.Builder().from(rockNRoll).build();

        // THEN
        assertEquals(rockNRoll, classUnderTest);
    }
}