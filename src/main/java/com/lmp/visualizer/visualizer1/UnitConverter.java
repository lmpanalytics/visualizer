/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lmp.visualizer.visualizer1;

import java.util.LinkedList;
import java.util.List;

/**
 * Convert unit quantity based calendars to cost and price based
 *
 * @author SEPALMM
 */
public class UnitConverter {

    private List<Plan> Pairs = new LinkedList<>();

    public UnitConverter() {
    }

    /**
     * Makes a unit based plan money based by converting its units into cost or
     * price. Enter negative amount if Cost conversion (e.g., -56.45), and
     * positive (e.g., 100.50) if Price conversion.
     *
     * @param unitPlan Plan to be converted
     * @param conversionFactor Applied for each unit
     */
    public void makePlanMoneyBased(List<Plan> unitPlan, double conversionFactor) {
        Pairs.addAll(convertPairs(unitPlan, conversionFactor));
    }

    private static List<Plan> convertPairs(List<Plan> unitPlan, double conversionFactor) {
        List<Plan> myPlan = new LinkedList<>();
        unitPlan.forEach((plan) -> {
            myPlan.add(new Plan(plan.getDate(), convertUnit(plan, conversionFactor)));
        });
        return myPlan;
    }

    private static double convertUnit(Plan plan, double conversionFactor) {
        return plan.getNumericalValue() * conversionFactor;
    }

    public List<Plan> getPairs() {
        return Pairs;
    }

    public void setPairs(List<Plan> Pairs) {
        this.Pairs = Pairs;
    }

}
