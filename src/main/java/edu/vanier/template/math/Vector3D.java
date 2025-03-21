/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.template.math;

/**
 *
 * @author letua
 */
public class Vector3D {

    private double x, y, z;

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D addVector3D(Vector3D vector3D) {
        return new Vector3D(this.x + vector3D.x, this.y + vector3D.y, this.z + vector3D.z);

    }

    public Vector3D scaleVector3D(double scale) {
        return new Vector3D(this.x * scale, this.y * scale, this.z * scale);
    }

    public double getMagnitude() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
    }

    public void normalizeVector3D() {
        this.x /= this.getMagnitude();
        this.y /= this.getMagnitude();
        this.z /= this.getMagnitude();
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return "Vector3D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}


