package com.yube;

import java.util.Arrays;

public class ElectreProblem {

    private int alternativeNumber;
    private int criteriaNumber;
    private double agreementBound;
    private double disagreementBound;
    private String[] alternativeLabels;
    private String[] criteriaLabels;
    private double[] weights;
    private double[][] values;

    public int getAlternativeNumber() {
        return alternativeNumber;
    }

    public void setAlternativeNumber(int alternativeNumber) {
        this.alternativeNumber = alternativeNumber;
    }

    public int getCriteriaNumber() {
        return criteriaNumber;
    }

    public void setCriteriaNumber(int criteriaNumber) {
        this.criteriaNumber = criteriaNumber;
    }

    public double getAgreementBound() {
        return agreementBound;
    }

    public void setAgreementBound(double agreementBound) {
        this.agreementBound = agreementBound;
    }

    public double getDisagreementBound() {
        return disagreementBound;
    }

    public void setDisagreementBound(double disagreementBound) {
        this.disagreementBound = disagreementBound;
    }

    public String[] getAlternativeLabels() {
        return alternativeLabels;
    }

    public void setAlternativeLabels(String[] alternativeLabels) {
        this.alternativeLabels = alternativeLabels;
    }

    public String[] getCriteriaLabels() {
        return criteriaLabels;
    }

    public void setCriteriaLabels(String[] criteriaLabels) {
        this.criteriaLabels = criteriaLabels;
    }

    public double[] getWeights() {
        return weights;
    }

    public void setWeights(double[] weights) {
        this.weights = weights;
    }

    public double[][] getValues() {
        return values;
    }

    public void setValues(double[][] values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return String.format("Кількість альтернатив: %s\n", alternativeNumber) +
                String.format("Кількість критеріїв: %s\n", criteriaNumber) +
                String.format("Порогове значення індексу згоди: %s\n", agreementBound) +
                String.format("Порогове значення індексу незгоди: %s\n", disagreementBound) +
                String.format("Список альтернатив: %s\n", Arrays.toString(alternativeLabels)) +
                String.format("Список критеріїв: %s\n", Arrays.toString(criteriaLabels)) +
                String.format("Вага критеріїв: %s\n", Utils.arrayToString(weights)) +
                String.format("Числові оцінки критеріїв за альтернативами:\n%s\n", Utils.matrixToString(values));
    }
}
