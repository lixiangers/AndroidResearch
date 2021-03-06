package com.lixiangers.androidresearch.feature;

public class Grocery {
    private static final int SIZE = 10000;
    // 属性d使得每个Grocery对象占用较多内存，有80K左右
    private double[] d = new double[SIZE];
    private String id;

    public Grocery(String id) {
        this.id = id;
    }

    public String toString() {
        return id;
    }

    @Override
    public void finalize() {
        try {
            super.finalize();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("Finalizing " + id);
    }
}
