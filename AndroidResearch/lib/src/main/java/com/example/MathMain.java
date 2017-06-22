package com.example;

/**
 * a Math.ceil():12.0
 * b Math.ceil():-11.0
 * a Math.floor():11.0
 * b Math.floor():-12.0
 * a Math.round():12
 * b Math.round():-12
 */
public class MathMain {
    public static void main(String[] args) {
        float a = 11.5f;
        float b = -11.5f;
        float c = -11.6f;
        System.out.println("a Math.ceil():" + Math.ceil(a));
        System.out.println("b Math.ceil():" + Math.ceil(b));

        System.out.println("a Math.floor():" + Math.floor(a));
        System.out.println("b Math.floor():" + Math.floor(b));

        System.out.println("a Math.round():" + Math.round(a));
        System.out.println("b Math.round():" + Math.round(b));
        System.out.println("c Math.round():" + Math.round(c));
    }
}
