package com.entersekt.gcmorrison.sdk;

import java.util.UUID;

/**
 * A helper interface that generates random primitive data
 */
public interface RandomTestData {
    default int randomInt() {
        return (int) (Math.random() * 100000);
    }

    default String randomString() {
        return UUID.randomUUID().toString();
    }
}
