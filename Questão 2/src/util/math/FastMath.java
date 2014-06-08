/*
 * Copyright (C) 2012 - João Vicente P. Reis Filho
 *
 * This file is part of the Pretty Cool Graphics Library (PCGL).
 */
package util.math;

import java.util.Random;

/**
 *
 * @author João Vicente P. Reis Filho
 */
public final class FastMath {
    public static final float PI = (float) Math.PI;

    public static final float DEG_TO_RAD = (float) (Math.PI / 180.0);
    public static final float RAD_TO_DEG = (float) (180.0 / Math.PI);

    private static Random random = new Random();

    /** Prevents instantiation. */
    private FastMath() {}


    public static float sin(float a) {
        return (float) Math.sin(a);
    }

    public static float cos(float a) {
        return (float) Math.cos(a);
    }

    public static float sqrt(float a) {
        return (float) Math.sqrt(a);
    }

    public static float floor(float a) {
        return (float) Math.floor(a);
    }

    public static float pow(float base, float exponent) {
        return (float) Math.pow(base, exponent);
    }

    public static float nextRandomFloat() {
        return random.nextFloat();
    }

}
