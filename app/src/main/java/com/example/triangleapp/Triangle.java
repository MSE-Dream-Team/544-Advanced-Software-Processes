package com.example.triangleapp;

public class Triangle {

    // sides of a triangle
    private float side1;
    private float side2;
    private float side3;

    // default constructor
    public Triangle(){

    }

    // constructor takes the array and separates the values
    public Triangle (float[] arr) {
        this.side1 = arr[0];
        this.side2 = arr[1];
        this.side3 = arr[2];
    }

    public String getTriangleType() {

        // check if the sides make up an actual triangle...
        if ( this.side1 + this.side2 <= this.side3 ||
                this.side1 + this.side3 <= this.side2 ||
                this.side2 + this.side3 <= this.side1) {
            return "Not A Triangle";
        }

        // real triangle, determine the type...
        if(this.side1 == this.side2 && this.side2==this.side3) {
            // equilateral
            return "equilateral";
        }

        if(this.side1 == this.side2 ||
                this.side1 == this.side3 ||
                this.side2 == this.side3) {
            // isosceles
            return "isosceles";
        }

        // scalene
        return "scalene";

    }


}
