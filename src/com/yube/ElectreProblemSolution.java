package com.yube;

import java.util.Arrays;

public class ElectreProblemSolution {

    private Double[][] a;
    private Double[][] b;
    private String[][] compare;
    private int[] kernel;

    public Double[][] getA() {
        return a;
    }

    public void setA(Double[][] a) {
        this.a = a;
    }

    public Double[][] getB() {
        return b;
    }

    public void setB(Double[][] b) {
        this.b = b;
    }

    public String[][] getCompare() {
        return compare;
    }

    public void setCompare(String[][] compare) {
        this.compare = compare;
    }

    public int[] getKernel() {
        return kernel;
    }

    public void setKernel(int[] kernel) {
        this.kernel = kernel;
    }

    @Override
    public String toString() {
        return String.format("Матриця індексів згоди:\n%s\n", Utils.matrixToString(a)) +
                String.format("Матриця індексів незгоди:\n%s\n", Utils.matrixToString(b)) +
                String.format("Матриця попарних порівнянь альтернатив:\n%s\n", Utils.matrixToString(compare, 5)) +
                String.format("Ядро альтернатив: %s\n", Arrays.toString(kernel));
    }
}
