package com.entersekt.gcmorrison.sdk.api.model;

import com.entersekt.gcmorrison.sdk.RandomTestData;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShopTest implements RandomTestData {
    private Shop classUnderTest;

    @Test
    public void canBuildShop() {
        // GIVEN
        int id = randomInt();
        String name = randomString();

        // WHEN
        classUnderTest = new Shop.Builder()
                .setId(id)
                .setName(name)
                .build();

        // THEN
        assertEquals(id, classUnderTest.getId());
        assertEquals(name, classUnderTest.getName());
    }

    @Test
    public void canBuildShopFromShop() {
        // GIVEN
        Shop shop = new Shop.Builder()
                .setId(randomInt())
                .setName(randomString())
                .build();

        // WHEN
        classUnderTest = new Shop.Builder().from(shop).build();

        // THEN
        assertEquals(shop, classUnderTest);
    }
}