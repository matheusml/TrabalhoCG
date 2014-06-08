/*
 * Copyright (C) 2012 - João Vicente P. Reis Filho
 *
 * This file is part of the Pretty Cool Graphics Library (PCGL).
 */
package util.math;

import java.nio.FloatBuffer;

/**
 * A 4x4 Matrix represented by floats in column major order. For instance, the
 * <code>m31</code> field holds the value of the element in the third column of
 * the first row.
 *
 * @author João Vicente P. Reis Filho
 */
public final class Matrix4f {
    public float m11, m21, m31, m41;
    public float m12, m22, m32, m42;
    public float m13, m23, m33, m43;
    public float m14, m24, m34, m44;

    /**
     * Creates a new <code>Matrix4f</code> and sets it to identity.
     */
    public Matrix4f() {
        m11 = 1.0f;
        m22 = 1.0f;
        m33 = 1.0f;
        m44 = 1.0f;
    }

    /**
     * Copy constructor.
     *
     * @param mat another <code>Matrix4f</code>
     */
    public Matrix4f(Matrix4f mat) {
        setTo(mat);
    }

    public void setToIdentity() {
        m11 = 1.0f; m21 = 0.0f; m31 = 0.0f; m41 = 0.0f;
        m12 = 0.0f; m22 = 1.0f; m32 = 0.0f; m42 = 0.0f;
        m13 = 0.0f; m23 = 0.0f; m33 = 1.0f; m43 = 0.0f;
        m14 = 0.0f; m24 = 0.0f; m34 = 0.0f; m44 = 1.0f;
    }

    public void setToZero() {
        m11 = 0.0f; m21 = 0.0f; m31 = 0.0f; m41 = 0.0f;
        m12 = 0.0f; m22 = 0.0f; m32 = 0.0f; m42 = 0.0f;
        m13 = 0.0f; m23 = 0.0f; m33 = 0.0f; m43 = 0.0f;
        m14 = 0.0f; m24 = 0.0f; m34 = 0.0f; m44 = 0.0f;
    }

    public void setTo( float m11, float m12, float m13, float m14,     // first column
                       float m21, float m22, float m23, float m24,     // second column
                       float m31, float m32, float m33, float m34,     // third column
                       float m41, float m42, float m43, float m44 ) {  // fourth column

        this.m11 = m11;  this.m21 = m21;  this.m31 = m31;  this.m41 = m41;
        this.m12 = m12;  this.m22 = m22;  this.m32 = m32;  this.m42 = m42;
        this.m13 = m13;  this.m23 = m23;  this.m33 = m33;  this.m43 = m43;
        this.m14 = m14;  this.m24 = m24;  this.m34 = m34;  this.m44 = m44;
    }

    /**
     * Copies another <code>Matrix4f</code>.
     *
     * @param mat another <code>Matrix4f</code>
     */
    public void setTo(Matrix4f mat) {
        m11 = mat.m11;  m21 = mat.m21;  m31 = mat.m31;  m41 = mat.m41;
        m12 = mat.m12;  m22 = mat.m22;  m32 = mat.m32;  m42 = mat.m42;
        m13 = mat.m13;  m23 = mat.m23;  m33 = mat.m33;  m43 = mat.m43;
        m14 = mat.m14;  m24 = mat.m24;  m34 = mat.m34;  m44 = mat.m44;
    }

    public void add(Matrix4f mat) {
        m11 += mat.m11;  m21 += mat.m21;  m31 += mat.m31;  m41 += mat.m41;
        m12 += mat.m12;  m22 += mat.m22;  m32 += mat.m32;  m42 += mat.m42;
        m13 += mat.m13;  m23 += mat.m23;  m33 += mat.m33;  m43 += mat.m43;
        m14 += mat.m14;  m24 += mat.m24;  m34 += mat.m34;  m44 += mat.m44;
    }

    public void subtract(Matrix4f mat) {
        m11 -= mat.m11;  m21 -= mat.m21;  m31 -= mat.m31;  m41 -= mat.m41;
        m12 -= mat.m12;  m22 -= mat.m22;  m32 -= mat.m32;  m42 -= mat.m42;
        m13 -= mat.m13;  m23 -= mat.m23;  m33 -= mat.m33;  m43 -= mat.m43;
        m14 -= mat.m14;  m24 -= mat.m24;  m34 -= mat.m34;  m44 -= mat.m44;
    }

    public void multiply(float scalar) {
        m11 *= scalar;  m21 *= scalar;  m31 *= scalar;  m41 *= scalar;
        m12 *= scalar;  m22 *= scalar;  m32 *= scalar;  m42 *= scalar;
        m13 *= scalar;  m23 *= scalar;  m33 *= scalar;  m43 *= scalar;
        m14 *= scalar;  m24 *= scalar;  m34 *= scalar;  m44 *= scalar;
    }

    /**
     * Multiplies this matrix by <code>mat</code> and then stores the result in
     * this matrix.
     *
     * @param mat another <code>Matrix4f</code>
     */
    public void multiply(Matrix4f mat) {
        multiply(mat, this);
    }

    /**
     * Multiplies this matrix by <code>mat</code> and then stores the result in
     * <code>result</code>.
     *
     * @param mat another <code>Matrix4f</code>
     * @param result a <code>Matrix4f</code> to store the result
     */
    public void multiply(Matrix4f mat, Matrix4f result) {
        float r11 = m11 * mat.m11 + m21 * mat.m12 + m31 * mat.m13 + m41 * mat.m14;
        float r12 = m12 * mat.m11 + m22 * mat.m12 + m32 * mat.m13 + m42 * mat.m14;
        float r13 = m13 * mat.m11 + m23 * mat.m12 + m33 * mat.m13 + m43 * mat.m14;
        float r14 = m14 * mat.m11 + m24 * mat.m12 + m34 * mat.m13 + m44 * mat.m14;

        float r21 = m11 * mat.m21 + m21 * mat.m22 + m31 * mat.m23 + m41 * mat.m24;
        float r22 = m12 * mat.m21 + m22 * mat.m22 + m32 * mat.m23 + m42 * mat.m24;
        float r23 = m13 * mat.m21 + m23 * mat.m22 + m33 * mat.m23 + m43 * mat.m24;
        float r24 = m14 * mat.m21 + m24 * mat.m22 + m34 * mat.m23 + m44 * mat.m24;

        float r31 = m11 * mat.m31 + m21 * mat.m32 + m31 * mat.m33 + m41 * mat.m34;
        float r32 = m12 * mat.m31 + m22 * mat.m32 + m32 * mat.m33 + m42 * mat.m34;
        float r33 = m13 * mat.m31 + m23 * mat.m32 + m33 * mat.m33 + m43 * mat.m34;
        float r34 = m14 * mat.m31 + m24 * mat.m32 + m34 * mat.m33 + m44 * mat.m34;

        float r41 = m11 * mat.m41 + m21 * mat.m42 + m31 * mat.m43 + m41 * mat.m44;
        float r42 = m12 * mat.m41 + m22 * mat.m42 + m32 * mat.m43 + m42 * mat.m44;
        float r43 = m13 * mat.m41 + m23 * mat.m42 + m33 * mat.m43 + m43 * mat.m44;
        float r44 = m14 * mat.m41 + m24 * mat.m42 + m34 * mat.m43 + m44 * mat.m44;

        result.m11 = r11;  result.m21 = r21;  result.m31 = r31;  result.m41 = r41;
        result.m12 = r12;  result.m22 = r22;  result.m32 = r32;  result.m42 = r42;
        result.m13 = r13;  result.m23 = r23;  result.m33 = r33;  result.m43 = r43;
        result.m14 = r14;  result.m24 = r24;  result.m34 = r34;  result.m44 = r44;
    }

    /**
     * Multiplies this matrix with a <code>Vector4f</code> and stores the result
     * in <code>result</code>.
     *
     * @param vec a <code>Vector4f</code>
     * @param result another <code>Vector4f</code> to store the result
     */
    public void multiply(Vector4f vec, Vector4f result) {
        result.x = m11 * vec.x + m21 * vec.y + m31 * vec.z + m41 * vec.w;
        result.y = m12 * vec.x + m22 * vec.y + m32 * vec.z + m42 * vec.w;
        result.z = m13 * vec.x + m23 * vec.y + m33 * vec.z + m43 * vec.w;
        result.w = m14 * vec.x + m24 * vec.y + m34 * vec.z + m44 * vec.w;
    }

    public void transpose() {
        float r21 = m12, r31 = m13, r41 = m14;
        float r12 = m21, r32 = m23, r42 = m24;
        float r13 = m31, r23 = m32, r43 = m34;
        float r14 = m41, r24 = m42, r34 = m43;

        m21 = r21;  m31 = r31;  m41 = r41;
        m12 = r12;  m32 = r32;  m42 = r42;
        m13 = r13;  m23 = r23;  m43 = r43;
        m14 = r14;  m24 = r24;  m34 = r34;
    }

    /**
     * Calculates the determinant of this matrix.
     *
     * @return the determinant of this matrix
     */
    public float determinant() {
        // Note: this implementation is based on the sample code from the book
        //       Mathematics for 3D Game Programming and Computer Graphics (3rd
        //       ed.), which may be freely used in any software.

        // Author: Eric Lengyel - original C++ code
        // Author: João Vicente P. Reis Filho - ported to Java and adapted to use with this class

        return (m11 * (m22 * (m33 * m44 - m34 * m43) + m23 * (m34 * m42 - m32 * m44) + m24 * (m32 * m43 - m33 * m42)) +
                m12 * (m21 * (m34 * m43 - m33 * m44) + m23 * (m31 * m44 - m34 * m41) + m24 * (m33 * m41 - m31 * m43)) +
                m13 * (m21 * (m32 * m44 - m34 * m42) + m22 * (m34 * m41 - m31 * m44) + m24 * (m31 * m42 - m32 * m41)) +
                m14 * (m21 * (m33 * m42 - m32 * m43) + m22 * (m31 * m43 - m33 * m41) + m23 * (m32 * m41 - m31 * m42)));
    }

    /**
     * Inverts this matrix in place.
     *
     * <p><b>Important warning:</b> this method does not check if this matrix is
     * actually invertible. If this matrix is singular (i.e., its determinant is
     * zero), then the result will be garbage (usually including a lot of
     * <tt>NaN</tt>s, etc).
     */
    public void inverse() {
        // Note: this implementation is based on the sample code from the book
        //       Mathematics for 3D Game Programming and Computer Graphics (3rd
        //       ed.), which may be freely used in any software.

        // Author: Eric Lengyel - original C++ code
        // Author: João Vicente P. Reis Filho - ported to Java and adapted to use with this class

        float p11 = m22 * (m33 * m44 - m43 * m34) + m32 * (m43 * m24 - m23 * m44) + m42 * (m23 * m34 - m33 * m24);
	float p12 = m12 * (m43 * m34 - m33 * m44) + m32 * (m13 * m44 - m43 * m14) + m42 * (m33 * m14 - m13 * m34);
	float p13 = m12 * (m23 * m44 - m43 * m24) + m22 * (m43 * m14 - m13 * m44) + m42 * (m13 * m24 - m23 * m14);
	float p14 = m12 * (m33 * m24 - m23 * m34) + m22 * (m13 * m34 - m33 * m14) + m32 * (m23 * m14 - m13 * m24);

	float t = 1.0f / (m11 * p11 + m21 * p12 + m31 * p13 + m41 * p14);

        setTo( p11 * t,
               p12 * t,
               p13 * t,
               p14 * t,
               (m21 * (m43 * m34 - m33 * m44) + m31 * (m23 * m44 - m43 * m24) + m41 * (m33 * m24 - m23 * m34)) * t,
               (m11 * (m33 * m44 - m43 * m34) + m31 * (m43 * m14 - m13 * m44) + m41 * (m13 * m34 - m33 * m14)) * t,
               (m11 * (m43 * m24 - m23 * m44) + m21 * (m13 * m44 - m43 * m14) + m41 * (m23 * m14 - m13 * m24)) * t,
               (m11 * (m23 * m34 - m33 * m24) + m21 * (m33 * m14 - m13 * m34) + m31 * (m13 * m24 - m23 * m14)) * t,
               (m21 * (m32 * m44 - m42 * m34) + m31 * (m42 * m24 - m22 * m44) + m41 * (m22 * m34 - m32 * m24)) * t,
               (m11 * (m42 * m34 - m32 * m44) + m31 * (m12 * m44 - m42 * m14) + m41 * (m32 * m14 - m12 * m34)) * t,
               (m11 * (m22 * m44 - m42 * m24) + m21 * (m42 * m14 - m12 * m44) + m41 * (m12 * m24 - m22 * m14)) * t,
               (m11 * (m32 * m24 - m22 * m34) + m21 * (m12 * m34 - m32 * m14) + m31 * (m22 * m14 - m12 * m24)) * t,
               (m21 * (m42 * m33 - m32 * m43) + m31 * (m22 * m43 - m42 * m23) + m41 * (m32 * m23 - m22 * m33)) * t,
               (m11 * (m32 * m43 - m42 * m33) + m31 * (m42 * m13 - m12 * m43) + m41 * (m12 * m33 - m32 * m13)) * t,
               (m11 * (m42 * m23 - m22 * m43) + m21 * (m12 * m43 - m42 * m13) + m41 * (m22 * m13 - m12 * m23)) * t,
               (m11 * (m22 * m33 - m32 * m23) + m21 * (m32 * m13 - m12 * m33) + m31 * (m12 * m23 - m22 * m13)) * t );
    }

    /**
     * Loads this <code>Matrix4f</code> from a <code>FloatBuffer</code> in
     * column major order.
     *
     * @param buffer a <code>FloatBuffer</code> to read from
     */
    public void load(FloatBuffer buffer) {
        m11 = buffer.get();
        m12 = buffer.get();
        m13 = buffer.get();
        m14 = buffer.get();

        m21 = buffer.get();
        m22 = buffer.get();
        m23 = buffer.get();
        m24 = buffer.get();

        m31 = buffer.get();
        m32 = buffer.get();
        m33 = buffer.get();
        m34 = buffer.get();

        m41 = buffer.get();
        m42 = buffer.get();
        m43 = buffer.get();
        m44 = buffer.get();
    }

    /**
     * Stores this <code>Matrix4f</code> in a <code>FloatBuffer</code> in
     * column major order.
     *
     * @param buffer a <code>FloatBuffer</code> to store this matrix in
     */
    public void store(FloatBuffer buffer) {
        buffer.put(m11).put(m12).put(m13).put(m14);
        buffer.put(m21).put(m22).put(m23).put(m24);
        buffer.put(m31).put(m32).put(m33).put(m34);
        buffer.put(m41).put(m42).put(m43).put(m44);
    }

    @Override
    public String toString() {
        String s11 = Float.toString(m11);
        String s12 = Float.toString(m12);
        String s13 = Float.toString(m13);
        String s14 = Float.toString(m14);
        int width1 = Math.max(s11.length(), Math.max(s12.length(), Math.max(s13.length(), s14.length())));

        String s21 = Float.toString(m21);
        String s22 = Float.toString(m22);
        String s23 = Float.toString(m23);
        String s24 = Float.toString(m24);
        int width2 = Math.max(s21.length(), Math.max(s22.length(), Math.max(s23.length(), s24.length())));

        String s31 = Float.toString(m31);
        String s32 = Float.toString(m32);
        String s33 = Float.toString(m33);
        String s34 = Float.toString(m34);
        int width3 = Math.max(s31.length(), Math.max(s32.length(), Math.max(s33.length(), s34.length())));

        String s41 = Float.toString(m41);
        String s42 = Float.toString(m42);
        String s43 = Float.toString(m43);
        String s44 = Float.toString(m44);
        int width4 = Math.max(s41.length(), Math.max(s42.length(), Math.max(s43.length(), s44.length())));

        String lineFormat = "[ %" + width1 + "s %" + width2 + "s %" + width3 + "s %" + width4 + "s ]";

        StringBuilder sb = new StringBuilder();

        sb.append("Matrix4f ").append(String.format(lineFormat, s11, s21, s31, s41)).append('\n');
        sb.append("         ").append(String.format(lineFormat, s12, s22, s32, s42)).append('\n');
        sb.append("         ").append(String.format(lineFormat, s13, s23, s33, s43)).append('\n');
        sb.append("         ").append(String.format(lineFormat, s14, s24, s34, s44)).append('\n');

        return sb.toString();
    }

}
