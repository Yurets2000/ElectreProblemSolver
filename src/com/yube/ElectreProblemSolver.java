package com.yube;

import java.util.ArrayList;
import java.util.List;

public class ElectreProblemSolver {

    public ElectreProblemSolution solve(ElectreProblem problem) {
        System.out.println(Utils.getEnvelope("Задача до вирішення методом ELECTRE", problem.toString()));

        int na = problem.getAlternativeNumber();
        int nc = problem.getCriteriaNumber();
        double agreementBound = problem.getAgreementBound();
        double disagreementBound = problem.getDisagreementBound();
        double[] weights = problem.getWeights();
        double[][] values = problem.getValues();
        Double[][] a = new Double[na][na];
        Double[][] b = new Double[na][na];
        String[][] compare = new String[na][na];
        List<Integer> kernel = new ArrayList<>();
        double divider = Utils.arraySum(weights);
        double distance = Utils.distance(values);
        for (int i = 0; i < na; i++) {
            for (int j = 0; j < na; j++) {
                if (i != j) {
                    List<Integer> i1 = new ArrayList<>();
                    List<Integer> i2 = new ArrayList<>();
                    for (int k = 0; k < nc; k++) {
                        if (values[i][k] >= values[j][k]) {
                            i1.add(k);
                        } else {
                            i2.add(k);
                        }
                    }
                    a[i][j] = Utils.arraySum(weights, i1) / divider;

                    double[] bArr = new double[i2.size()];
                    for (int k = 0; k < i2.size(); k++) {
                        bArr[k] = Math.abs((values[j][i2.get(k)] - values[i][i2.get(k)]) / distance);
                    }
                    b[i][j] = Utils.max(bArr);

                    if (a[i][j] >= agreementBound && b[i][j] <= disagreementBound) {
                        compare[i][j] = ">";
                    } else if (a[i][j] < agreementBound && b[i][j] > disagreementBound) {
                        compare[i][j] = "<";
                    } else {
                        compare[i][j] = "-";
                    }
                }
            }
        }

        List<Integer> possibleKernel = new ArrayList<>();
        for (int i = 0; i < na; i++) {
            boolean flag = true;
            for (int j = 0; j < na; j++) {
                if (i != j) {
                    if (compare[i][j].equals("<")) {
                        flag = false;
                    }
                }
            }
            if (flag) {
                possibleKernel.add(i);
            }
        }
        if (!possibleKernel.isEmpty()) {
            for (int i = 0; i < possibleKernel.size(); i++) {
                boolean flag = true;
                for (int j = 0; j < possibleKernel.size(); j++) {
                    if (i != j) {
                        if (!compare[possibleKernel.get(i)][possibleKernel.get(j)].equals("-")) {
                            flag = false;
                        }
                    }
                }
                if (flag) {
                    kernel.add(possibleKernel.get(i));
                }
            }
        }
        ElectreProblemSolution solution = new ElectreProblemSolution();
        solution.setA(a);
        solution.setB(b);
        solution.setCompare(compare);
        solution.setKernel(Utils.integerListToArray(kernel));
        System.out.println(Utils.getEnvelope("Розв'язок задачі методом ELECTRE", solution.toString()));
        return solution;
    }
}
