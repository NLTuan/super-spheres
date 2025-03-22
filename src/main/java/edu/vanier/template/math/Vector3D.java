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
    public void addToCurrentVector3D(Vector3D other){
        this.x += other.x;
        this.y += other.y;
        this.z += other.z;
    }

    public Vector3D scaleVector3D(double scale) {
        return new Vector3D(this.x * scale, this.y * scale, this.z * scale);
    }

    public double getMagnitude() {
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    public void normalizeVector3D() {
        double magnitude = this.getMagnitude();
        if (magnitude> 0) {
            this.x /= magnitude;
            this.y /=magnitude;
            this.z /= magnitude;
        }
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


