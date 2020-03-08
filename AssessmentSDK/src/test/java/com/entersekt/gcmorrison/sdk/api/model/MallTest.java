package com.entersekt.gcmorrison.sdk.api.model;

import com.entersekt.gcmorrison.sdk.RandomTestData;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class MallTest implements RandomTestData {
    private Mall classUnderTest;

    @Test
    public void canBuildMallWithNoShops() {
        // GIVEN
        int id = randomInt();
        String name = randomString();

        // WHEN
        classUnderTest = new Mall.Builder()
                .setId(id)
                .setName(name)
                .build();

        // THEN
        assertEquals(id, classUnderTest.getId());
        assertEquals(name, classUnderTest.getName());
        assertTrue(classUnderTest.getShops().isEmpty());
    }

    @Test
    public void canBuildMallWithNullShops() {
        // GIVEN / WHEN
        classUnderTest = new Mall.Builder()
                .setId(randomInt())
                .setName(randomString())
                .addShops(null, null, null)
                .build();

        // THEN
        assertTrue(classUnderTest.getShops().isEmpty());
    }

    @Test
    public void canBuildMallWithShops() {
        // GIVEN
        Shop[] shops = new Shop[]{mock(Shop.class), mock(Shop.class)};

        // WHEN
        classUnderTest = new Mall.Builder()
                .setId(randomInt())
                .setName(randomString())
                .addShops(shops)
                .build();

        // THEN
        assertEquals(shops.length, classUnderTest.getShops().size());
    }

    @Test
    public void canBuildMallFromMall() {
        // GIVEN
        Mall mall = new Mall.Builder()
                .setId(randomInt())
                .setName(randomString())
                .addShops(mock(Shop.class)).build();

        // WHEN
        classUnderTest = new Mall.Builder().from(mall).build();

        // THEN
        assertEquals(mall, classUnderTest);
    }
}