/*
 * Copyright (C) 2012 - João Vicente P. Reis Filho
 *
 * This file is part of the Pretty Cool Graphics Library (PCGL).
 */
package util.math;

import java.nio.FloatBuffer;

/**
 * A 4D Vector represented by floats.
 *
 * @author João Vicente P. Reis Filho
 */
public final class Vector4f {
    public float x;
    public float y;
    public float z;
    public float w;

    public Vector4f() {
        setToZero();
    }

    public Vector4f(float x, float y, float z, float w) {
        setTo(x, y, z, w);
    }

    /**
     * Copy constructor.
     *
     * @param vec another <code>Vector4f</code>
     */
    public Vector4f(Vector4f vec) {
        setTo(vec);
    }

    /**
     * Creates a <code>Vector4f</code> from a <code>Vector3f</code> and a
     * w-coordinate.
     *
     * <p> Note that the w-coordinate should be set to zero if <code>vec</code>
     * is a direction vector. On the other hand, if <code>vec</code> is a point
     * then the w-coordinate should probably be set to <code>1.0f</code>.
     *
     * @param vec a <code>Vector3f</code>
     * @param w the w-coordinate
     */
    public Vector4f(Vector3f vec, float w) {
        setTo(vec.x, vec.y, vec.z, w);
    }

    public void setToZero() {
        x = 0.0f;
        y = 0.0f;
        z = 0.0f;
        w = 0.0f;
    }

    public void setTo(Vector4f vec) {
        setTo(vec.x, vec.y, vec.z, vec.w);
    }

    public void setTo(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public float length() {
        return FastMath.sqrt(lengthSquared());
    }

    public float lengthSquared() {
        return (x*x + y*y + z*z + w*w);
    }

    public void add(Vector4f vec) {
        add(vec.x, vec.y, vec.z, vec.w);
    }

    public void add(float x, float y, float z, float w) {
        this.x += x;
        this.y += y;
        this.z += z;
        this.w += w;
    }

    public void subtract(Vector4f vec) {
        add(-vec.x, -vec.y, -vec.z, -vec.w);
    }

    public void subtract(float x, float y, float z, float w) {
        add(-x, -y, -z, -w);
    }

    public void multiply(Vector4f vec) {
        this.x *= vec.x;
        this.y *= vec.y;
        this.z *= vec.z;
        this.w *= vec.w;
    }

    public void multiply(float x, float y, float z, float w) {
        this.x *= x;
        this.y *= y;
        this.z *= z;
        this.w *= w;
    }

    public void multiply(float scalar) {
        this.x *= scalar;
        this.y *= scalar;
        this.z *= scalar;
        this.w *= scalar;
    }

    public void divide(Vector4f vec) {
        this.x /= vec.x;
        this.y /= vec.y;
        this.z /= vec.z;
        this.w /= vec.w;
    }

    public void divide(float x, float y, float z, float w) {
        this.x /= x;
        this.y /= y;
        this.z /= z;
        this.w /= w;
    }

    public void divide(float scalar) {
        this.x /= scalar;
        this.y /= scalar;
        this.z /= scalar;
        this.w /= scalar;
    }

    public void divideByW() {
        divide(w);
    }

    public void negate() {
        setTo(-x, -y, -z, -w);
    }

    public void normalize() {
        float length = length();

        if (length != 0.0f) {
            divide(length);
        }
    }

    public float dotProduct(Vector4f vec) {
        return (x * vec.x + y * vec.y + z * vec.z + w * vec.w);
    }

    /**
     * Loads this <code>Vector4f</code> from a <code>FloatBuffer</code>.
     *
     * @param buffer a <code>FloatBuffer</code> to read from
     */
    public void load(FloatBuffer buffer) {
        x = buffer.get();
        y = buffer.get();
        z = buffer.get();
        w = buffer.get();
    }

    /**
     * Stores this <code>Vector4f</code> in a <code>FloatBuffer</code>.
     *
     * @param buffer a <code>FloatBuffer</code> to store this vector in
     */
    public void store(FloatBuffer buffer) {
        buffer.put(x).put(y).put(z).put(w);
    }

    @Override
    public String toString() {
        return "Vector4f [x=" + x + " y=" + y + " z=" + z + " w=" + w + "]";
    }

}
