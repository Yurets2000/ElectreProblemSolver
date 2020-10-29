package com.yube;

import java.util.List;

public class Utils {

    public static double arraySum(double[] arr) {
        double res = 0;
        for (double v : arr) {
            res += v;
        }
        return res;
    }

    public static double arraySum(double[] arr, List<Integer> indexes) {
        double res = 0;
        for (int i = 0; i < arr.length; i++) {
            if (indexes.contains(i)) {
                res += arr[i];
            }
        }
        return res;
    }

    public static double max(double[][] matrix) {
        double max = matrix[0][0];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                }
            }
        }
        return max;
    }

    static double min(double[][] matrix) {
        double min = matrix[0][0];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] < min) {
                    min = matrix[i][j];
                }
            }
        }
        return min;
    }

    public static double max(double[] arr) {
        double max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public static double min(double[] arr) {
        double min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }

    public static double distance(double[] arr) {
        return max(arr) - min(arr);
    }

    public static double distance(double[][] matrix) {
        return max(matrix) - min(matrix);
    }

    public static int[] integerListToArray(List<Integer> list) {
        return convertIntegerArrBackwards(list.toArray(new Integer[0]));
    }

    public static int[] convertIntegerArrBackwards(Integer[] arr) {
        int[] copy = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            copy[i] = arr[i];
        }
        return copy;
    }

    public static String arrayToString(double[] arr) {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < arr.length - 1; i++) {
            result.append(String.format("%.3f, ", arr[i]));
        }
        if (arr.length > 0) {
            result.append(String.format("%.3f", arr[arr.length - 1]));
        }
        result.append("]");
        return result.toString();
    }

    public static String arrayToString(Double[] arr) {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == null) {
                result.append("*, ");
            } else {
                result.append(String.format("%.3f, ", arr[i]));
            }
        }
        if (arr.length > 0) {
            if (arr[arr.length - 1] == null) {
                result.append("*");
            } else {
                result.append(String.format("%.3f", arr[arr.length - 1]));
            }
        }
        result.append("]");
        return result.toString();
    }

    public static String matrixToString(double[][] matrix) {
        StringBuilder result = new StringBuilder();
        for (double[] row : matrix) {
            for (double element : row) {
                result.append(String.format("%9.3f", element));
            }
            result.append("\n");
        }
        return result.toString();
    }

    public static String matrixToString(Double[][] matrix) {
        StringBuilder result = new StringBuilder();
        for (Double[] row : matrix) {
            for (Double element : row) {
                if (element == null) {
                    result.append(center("*", 9));
                } else {
                    result.append(String.format("%9.3f", element));
                }
            }
            result.append("\n");
        }
        return result.toString();
    }

    public static String matrixToString(String[][] matrix, int space) {
        StringBuilder result = new StringBuilder();
        for (String[] row : matrix) {
            for (String element : row) {
                if (element == null) {
                    result.append(center("*", space));
                } else {
                    result.append(center(element, space));
                }
            }
            result.append("\n");
        }
        return result.toString();
    }

    public static String center(String text, int len) {
        String out = String.format("%" + len + "s%s%" + len + "s", "", text, "");
        float mid = (out.length() / 2);
        float start = mid - (len / 2);
        float end = start + len;
        return out.substring((int) start, (int) end);
    }

    public static String getEnvelope(String header, String content) {
        String envelope = getHeader(header);
        envelope += content + "\n";
        envelope += Utils.getSplitter() + "\n";
        return envelope;
    }

    public static String getHeader(String content) {
        String splitter = getSplitter();
        return splitter + "\n" +
                Utils.center(content, 100) + "\n" +
                splitter + "\n";
    }

    public static String getSplitter() {
        return "----------------------------------------------------------------------------------------------------";
    }
}
