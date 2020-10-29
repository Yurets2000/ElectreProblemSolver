/*
 * Copyright (c) 2020. Yurii Bezliudnyi.
 * Copying without author notice is prohibited.
 *
 */

package com.yube;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private final Scanner scanner = new Scanner(System.in);
    private final String doubleNumberPatternString = "-?\\d+(\\.\\d+)?";
    private final String vectorPatternString = "\\[((" + doubleNumberPatternString + ",\\s+)+" + doubleNumberPatternString + ")?\\]";
    Pattern vectorPattern = Pattern.compile(vectorPatternString);
    Pattern doubleNumberPattern = Pattern.compile(doubleNumberPatternString);

    public static void main(String[] args) {
        Main main = new Main();
        main.process();
    }

    public void process() {
        ElectreProblem electreProblem = readElectreProblem();
        ElectreProblemSolver electreProblemSolver = new ElectreProblemSolver();
        electreProblemSolver.solve(electreProblem);
    }

    private ElectreProblem readElectreProblem() {
        int na = Integer.parseInt(read("Введіть кількість альтернатив:", "\\d{1,2}"));
        int nc = Integer.parseInt(read("Введіть кількість критеріїв:", "\\d{1,2}"));
        if (na < 2 || nc < 2) {
            throw new IllegalArgumentException("Кількість альтернатив чи кретеріїв повина бути не меншою за 2");
        }
        double agreementBound = Double.parseDouble(read("Введіть порогове значення індексу згоди:",
                doubleNumberPatternString));
        double disagreementBound = Double.parseDouble(read("Введіть порогове значення індексу незгоди:",
                doubleNumberPatternString));
        if (agreementBound < 0 || agreementBound > 1 || disagreementBound < 0 || disagreementBound > 1) {
            throw new IllegalArgumentException("Індекси згоди та незгоди мають бути в діапазоні [0, 1]");
        }
        String[] alternativeLabels = new String[na];
        String[] criteriaLabels = new String[nc];
        for (int i = 0; i < na; i++) {
            alternativeLabels[i] = read(String.format("Введіть назву %d-ї альтернативи:", i),
                    ".{1,50}");
        }
        for (int i = 0; i < nc; i++) {
            criteriaLabels[i] = read(String.format("Введіть назву %d-го критерію:", i),
                    ".{1,50}");
        }
        double[] weights = new double[nc];
        for (int i = 0; i < nc; i++) {
            weights[i] = Double.parseDouble(read(String.format("Введіть вагу критерію '%s':", criteriaLabels[i]),
                    doubleNumberPatternString));
        }
        double[][] values = readMatrix("Числові оцінки критеріїв за альтернативами", na, nc);
        ElectreProblem electreProblem = new ElectreProblem();
        electreProblem.setAlternativeNumber(na);
        electreProblem.setCriteriaNumber(nc);
        electreProblem.setAgreementBound(agreementBound);
        electreProblem.setDisagreementBound(disagreementBound);
        electreProblem.setAlternativeLabels(alternativeLabels);
        electreProblem.setCriteriaLabels(criteriaLabels);
        electreProblem.setWeights(weights);
        electreProblem.setValues(values);
        return electreProblem;
    }

    private String read(String question, String pattern) {
        while (true) {
            System.out.println(question);
            String line = scanner.nextLine().trim();
            if (line.matches(pattern)) return line;
            System.out.println("Неправильно введене значення, спробуйте знову.");
        }
    }

    private double[][] readMatrix(String label, int columns, int rows) {
        double[][] result = new double[columns][rows];
        System.out.printf("Введіть значення матриці '%s'\n", label);
        for (int i = 0; i < columns; i++) {
            System.out.printf("Введіть значення %d-го рядка матриці:\n", i);
            result[i] = readVector(rows);
        }
        return result;
    }

    private double[] readVector(int size) {
        double[] result = new double[size];
        boolean flag = false;
        String vector = null;
        while (!flag) {
            String line = scanner.nextLine();
            if (!line.trim().isEmpty()) {
                if (vectorPattern.matcher(line).matches()) {
                    vector = line;
                    flag = true;
                } else {
                    System.out.println("Ви неправильно ввели значення вектору, спробуйте знову:");
                    flag = false;
                }
            }
        }

        int i = 0;
        Matcher numberMatcher = doubleNumberPattern.matcher(vector);
        while (numberMatcher.find()) {
            String number = numberMatcher.group();
            result[i++] = Double.parseDouble(number);
        }
        return result;
    }
}
