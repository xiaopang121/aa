package com.insthub.cat.android.utils;

import java.util.Random;

/**
 * Created by linux on 2017/7/13.
 */

public class Enums {

    private static Random rand = new Random(15);

    public static <T extends Enum<T>> T random(Class<T> ec) {
        return random(ec.getEnumConstants());
    }

    public static <T> T random(T[] values) {
        return values[rand.nextInt(values.length)];
    }
}
