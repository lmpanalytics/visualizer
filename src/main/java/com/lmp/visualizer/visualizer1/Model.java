/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lmp.visualizer.visualizer1;

import java.util.LinkedList;
import java.util.List;

/**
 * Models the growth of sales and its derivatives in the value chain, and models
 * depreciation. The model is producing generic units, which can be thought of
 * as hours, days, liters, kg, pieces and etc. A conversion of the units to
 * money is done later by the UnitConverter class. The granularity of the models
 * produced are in days, which are summed and grouped by calendar units (years)
 * in the ProfitLoss class.
 *
 * @author SEPALMM
 */
public class Model {

    private List<Double> listOfNumericalValues = new LinkedList<>();

    /**
     * Generates a model based on a Linear growth function P = m * t + b.
     *
     * The model spans over six years, i.e., 6 * 365.25 = 2192 days. This
     * ensures inclusion of a standard business cycle of 5 years.
     *
     * @param m Absolute constant number of added population at t1, t2, ..., t_n
     * @param b Population at day t0
     */
    public void generateLinearGrowthModel(double m, double b) {

        for (int t = 0; t < 2192; t++) {
            listOfNumericalValues.add(m * t + b);
        }

    }

    /**
     * Generates a depreciation model for the Straight-Line method.
     *
     * The model spans over six years, i.e., 6 * 365.25 = 2192 days. This
     * ensures inclusion of a standard business cycle of 5 years.
     *
     * @param years The number of years (asset's service lifetime) over which
     * the model returns a list of 1 (ones) for each day a charge will be
     * applied. Thereafter the model returns 0 (zeroes), i.e., no further
     * depreciation charges applied.
     */
    public void generateStraightLineDepreciationModel(int years) {
        int lifeTime = years * 365;
        for (int t = 0; t < 2192; t++) {
            listOfNumericalValues.add(t < lifeTime ? 1d : 0d);
        }

    }

    public List<Double> getListOfNumericalValues() {
        return listOfNumericalValues;
    }

    public void setListOfNumericalValues(List<Double> listOfNumericalValues) {
        this.listOfNumericalValues = listOfNumericalValues;
    }

}
