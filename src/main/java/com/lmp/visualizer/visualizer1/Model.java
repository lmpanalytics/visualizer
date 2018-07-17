/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lmp.visualizer.visualizer1;

import java.util.LinkedList;
import java.util.List;

/**
 * Models the growth of sales and its derivatives in the value chain.
 *
 * @author SEPALMM
 */
public class Model {

    private static List<Double> listOfNumericalValues = new LinkedList<>();

    /**
     * Generates a model based on a Linear growth function P = m * t + b.
     *
     * The model spans over six years, i.e., 6 * 365.25 = 2192 days. This
     * ensures inclusion of a standard business cycle of 5 years.
     *
     * @param m Absolute constant number of added population at t1, t2, ..., t_n
     * @param b Population at day t0
     */
    public static void generateLinearModel(double m, double b) {

        for (int t = 0; t < 2192; t++) {
            listOfNumericalValues.add(m * t + b);
        }

    }

    public static List<Double> getListOfNumericalValues() {
        return listOfNumericalValues;
    }

    public static void setListOfNumericalValues(List<Double> listOfNumericalValues) {
        Model.listOfNumericalValues = listOfNumericalValues;
    }

}
