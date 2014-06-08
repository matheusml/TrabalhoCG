/*
 * Copyright (C) 2012 - João Vicente P. Reis Filho
 *
 * This file is part of the Pretty Cool Graphics Library (PCGL).
 */
package util.math;

import java.nio.FloatBuffer;

/**
 * A 3x3 Matrix represented by floats in column major order. For instance, the
 * <code>m31</code> field holds the value of the element in the third column of
 * the first row.
 *
 * @author João Vicente P. Reis Filho
 */
public final class Matrix3f {
    public float m11, m21, m31;
    public float m12, m22, m32;
    public float m13, m23, m33;

    /**
     * Creates a new <code>Matrix3f</code> and sets it to identity.
     */
    public Matrix3f() {
        m11 = 1.0f;
        m22 = 1.0f;
        m33 = 1.0f;
    }

    /**
     * Copy constructor.
     *
     * @param mat another <code>Matrix3f</code>
     */
    public Matrix3f(Matrix3f mat) {
        setTo(mat);
    }

    public void setToIdentity() {
        m11 = 1.0f; m21 = 0.0f; m31 = 0.0f;
        m12 = 0.0f; m22 = 1.0f; m32 = 0.0f;
        m13 = 0.0f; m23 = 0.0f; m33 = 1.0f;
    }

    public void setToZero() {
        m11 = 0.0f; m21 = 0.0f; m31 = 0.0f;
        m12 = 0.0f; m22 = 0.0f; m32 = 0.0f;
        m13 = 0.0f; m23 = 0.0f; m33 = 0.0f;
    }

    public void setTo( float m11, float m12, float m13,     // first column
                       float m21, float m22, float m23,     // second column
                       float m31, float m32, float m33 ) {  // third column

        this.m11 = m11;  this.m21 = m21;  this.m31 = m31;
        this.m12 = m12;  this.m22 = m22;  this.m32 = m32;
        this.m13 = m13;  this.m23 = m23;  this.m33 = m33;
    }

    /**
     * Copies another <code>Matrix3f</code>.
     *
     * @param mat another <code>Matrix3f</code>
     */
    public void setTo(Matrix3f mat) {
        m11 = mat.m11;  m21 = mat.m21;  m31 = mat.m31;
        m12 = mat.m12;  m22 = mat.m22;  m32 = mat.m32;
        m13 = mat.m13;  m23 = mat.m23;  m33 = mat.m33;
    }

    public void setToUpperLeftOf(Matrix4f mat4) {
        m11 = mat4.m11;  m21 = mat4.m21;  m31 = mat4.m31;
        m12 = mat4.m12;  m22 = mat4.m22;  m32 = mat4.m32;
        m13 = mat4.m13;  m23 = mat4.m23;  m33 = mat4.m33;
    }

    public void add(Matrix3f mat) {
        m11 += mat.m11;  m21 += mat.m21;  m31 += mat.m31;
        m12 += mat.m12;  m22 += mat.m22;  m32 += mat.m32;
        m13 += mat.m13;  m23 += mat.m23;  m33 += mat.m33;
    }

    public void subtract(Matrix3f mat) {
        m11 -= mat.m11;  m21 -= mat.m21;  m31 -= mat.m31;
        m12 -= mat.m12;  m22 -= mat.m22;  m32 -= mat.m32;
        m13 -= mat.m13;  m23 -= mat.m23;  m33 -= mat.m33;
    }

    public void multiply(float scalar) {
        m11 *= scalar;  m21 *= scalar;  m31 *= scalar;
        m12 *= scalar;  m22 *= scalar;  m32 *= scalar;
        m13 *= scalar;  m23 *= scalar;  m33 *= scalar;
    }

    /**
     * Multiplies this matrix by <code>mat</code> and then stores the result in
     * this matrix.
     *
     * @param mat another <code>Matrix3f</code>
     */
    public void multiply(Matrix3f mat) {
        multiply(mat, this);
    }

    /**
     * Multiplies this matrix by <code>mat</code> and then stores the result in
     * <code>result</code>.
     *
     * @param mat another <code>Matrix3f</code>
     * @param result a <code>Matrix3f</code> to store the result
     */
    public void multiply(Matrix3f mat, Matrix3f result) {
        float r11 = m11 * mat.m11 + m21 * mat.m12 + m31 * mat.m13;
        float r12 = m12 * mat.m11 + m22 * mat.m12 + m32 * mat.m13;
        float r13 = m13 * mat.m11 + m23 * mat.m12 + m33 * mat.m13;

        float r21 = m11 * mat.m21 + m21 * mat.m22 + m31 * mat.m23;
        float r22 = m12 * mat.m21 + m22 * mat.m22 + m32 * mat.m23;
        float r23 = m13 * mat.m21 + m23 * mat.m22 + m33 * mat.m23;

        float r31 = m11 * mat.m31 + m21 * mat.m32 + m31 * mat.m33;
        float r32 = m12 * mat.m31 + m22 * mat.m32 + m32 * mat.m33;
        float r33 = m13 * mat.m31 + m23 * mat.m32 + m33 * mat.m33;

        result.m11 = r11;  result.m21 = r21;  result.m31 = r31;
        result.m12 = r12;  result.m22 = r22;  result.m32 = r32;
        result.m13 = r13;  result.m23 = r23;  result.m33 = r33;
    }

    /**
     * Multiplies this matrix with a <code>Vector3f</code> and stores the result
     * in <code>result</code>.
     *
     * @param vec a <code>Vector3f</code>
     * @param result another <code>Vector3f</code> to store the result
     */
    public void multiply(Vector3f vec, Vector3f result) {
        result.x = m11 * vec.x + m21 * vec.y + m31 * vec.z;
        result.y = m12 * vec.x + m22 * vec.y + m32 * vec.z;
        result.z = m13 * vec.x + m23 * vec.y + m33 * vec.z;
    }

    public void transpose() {
        float r21 = m12, r31 = m13;
        float r12 = m21, r32 = m23;
        float r13 = m31, r23 = m32;

        m21 = r21;  m31 = r31;
        m12 = r12;  m32 = r32;
        m13 = r13;  m23 = r23;
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

        return (m11 * (m22 * m33 - m23 * m32) - m12 * (m21 * m33 - m23 * m31) + m13 * (m21 * m32 - m22 * m31));
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

        float p11 = m22 * m33 - m32 * m23;
	float p12 = m32 * m13 - m12 * m33;
	float p13 = m12 * m23 - m22 * m13;

	float t = 1.0f / (m11 * p11 + m21 * p12 + m31 * p13);

        setTo( p11 * t,
               p12 * t,
               p13 * t,
               (m31 * m23 - m21 * m33) * t,
               (m11 * m33 - m31 * m13) * t,
               (m21 * m13 - m11 * m23) * t,
               (m21 * m32 - m31 * m22) * t,
               (m31 * m12 - m11 * m32) * t,
               (m11 * m22 - m21 * m12) * t );
    }

    /**
     * Loads this <code>Matrix3f</code> from a <code>FloatBuffer</code> in
     * column major order.
     *
     * @param buffer a <code>FloatBuffer</code> to read from
     */
    public void load(FloatBuffer buffer) {
        m11 = buffer.get();
        m12 = buffer.get();
        m13 = buffer.get();

        m21 = buffer.get();
        m22 = buffer.get();
        m23 = buffer.get();

        m31 = buffer.get();
        m32 = buffer.get();
        m33 = buffer.get();
    }

    /**
     * Stores this <code>Matrix3f</code> in a <code>FloatBuffer</code> in
     * column major order.
     *
     * @param buffer a <code>FloatBuffer</code> to store this matrix in
     */
    public void store(FloatBuffer buffer) {
        buffer.put(m11).put(m12).put(m13);
        buffer.put(m21).put(m22).put(m23);
        buffer.put(m31).put(m32).put(m33);
    }

    @Override
    public String toString() {
        String s11 = Float.toString(m11);
        String s12 = Float.toString(m12);
        String s13 = Float.toString(m13);
        int width1 = Math.max(s11.length(), Math.max(s12.length(), s13.length()));

        String s21 = Float.toString(m21);
        String s22 = Float.toString(m22);
        String s23 = Float.toString(m23);
        int width2 = Math.max(s21.length(), Math.max(s22.length(), s23.length()));

        String s31 = Float.toString(m31);
        String s32 = Float.toString(m32);
        String s33 = Float.toString(m33);
        int width3 = Math.max(s31.length(), Math.max(s32.length(), s33.length()));

        String lineFormat = "[ %" + width1 + "s %" + width2 + "s %" + width3 + "s ]";

        StringBuilder sb = new StringBuilder();

        sb.append("Matrix3f ").append(String.format(lineFormat, s11, s21, s31)).append('\n');
        sb.append("         ").append(String.format(lineFormat, s12, s22, s32)).append('\n');
        sb.append("         ").append(String.format(lineFormat, s13, s23, s33)).append('\n');

        return sb.toString();
    }

}
