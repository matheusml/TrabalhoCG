/*
 * Copyright (C) 2012 - João Vicente P. Reis Filho
 *
 * This file is part of the Pretty Cool Graphics Library (PCGL).
 */
package util.math;

import java.nio.FloatBuffer;

/**
 * A 3D Vector represented by floats.
 *
 * @author João Vicente P. Reis Filho
 */
public final class Vector3f {
    public float x;
    public float y;
    public float z;

    public Vector3f() {
        setToZero();
    }

    public Vector3f(float x, float y, float z) {
        setTo(x, y, z);
    }

    /**
     * Copy constructor.
     *
     * @param vec another <code>Vector3f</code>
     */
    public Vector3f(Vector3f vec) {
        setTo(vec);
    }

    public void setToZero() {
        x = 0.0f;
        y = 0.0f;
        z = 0.0f;
    }

    public void setTo(Vector3f vec) {
        setTo(vec.x, vec.y, vec.z);
    }

    public void setTo(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float length() {
        return FastMath.sqrt(lengthSquared());
    }

    public float lengthSquared() {
        return (x*x + y*y + z*z);
    }

    public void add(Vector3f vec) {
        add(vec.x, vec.y, vec.z);
    }

    public void add(float x, float y, float z) {
        this.x += x;
        this.y += y;
        this.z += z;
    }

    public void subtract(Vector3f vec) {
        add(-vec.x, -vec.y, -vec.z);
    }

    public void subtract(float x, float y, float z) {
        add(-x, -y, -z);
    }

    public void multiply(Vector3f vec) {
        this.x *= vec.x;
        this.y *= vec.y;
        this.z *= vec.z;
    }

    public void multiply(float x, float y, float z) {
        this.x *= x;
        this.y *= y;
        this.z *= z;
    }

    public void multiply(float scalar) {
        this.x *= scalar;
        this.y *= scalar;
        this.z *= scalar;
    }

    public void divide(Vector3f vec) {
        this.x /= vec.x;
        this.y /= vec.y;
        this.z /= vec.z;
    }

    public void divide(float x, float y, float z) {
        this.x /= x;
        this.y /= y;
        this.z /= z;
    }

    public void divide(float scalar) {
        this.x /= scalar;
        this.y /= scalar;
        this.z /= scalar;
    }

    public void negate() {
        setTo(-x, -y, -z);
    }

    public void normalize() {
        float length = length();

        if (length != 0.0f) {
            divide(length);
        }
    }

    public float dotProduct(Vector3f vec) {
        return (x * vec.x + y * vec.y + z * vec.z);
    }

    /**
     * Calculates the cross product of this vector and <code>vec</code> and then
     * stores the result in this vector.
     *
     * @param vec another <code>Vector3f</code>
     */
    public void crossProduct(Vector3f vec) {
        setTo( (y * vec.z - z * vec.y),
               (z * vec.x - x * vec.z),
               (x * vec.y - y * vec.x) );
    }

    /**
     * Calculates the cross product of this vector and <code>vec</code> and then
     * stores the result in <code>result</code>.
     *
     * @param vec another <code>Vector3f</code>
     * @param result a <code>Vector3f</code> to store the result
     */
    public void crossProduct(Vector3f vec, Vector3f result) {
        result.setTo( (y * vec.z - z * vec.y),
                      (z * vec.x - x * vec.z),
                      (x * vec.y - y * vec.x) );
    }

    /**
     * Loads this <code>Vector3f</code> from a <code>FloatBuffer</code>.
     *
     * @param buffer a <code>FloatBuffer</code> to read from
     */
    public void load(FloatBuffer buffer) {
        x = buffer.get();
        y = buffer.get();
        z = buffer.get();
    }

    /**
     * Stores this <code>Vector3f</code> in a <code>FloatBuffer</code>.
     *
     * @param buffer a <code>FloatBuffer</code> to store this vector in
     */
    public void store(FloatBuffer buffer) {
        buffer.put(x).put(y).put(z);
    }

    @Override
    public String toString() {
        return "Vector3f [x=" + x + " y=" + y + " z=" + z + "]";
    }

}
