/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lmp.visualizer.visualizer1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import static java.util.stream.Collectors.toCollection;

/**
 * Converts the unit lists into a calendar based plan
 *
 * @author SEPALMM
 */
public class Plan {

    private List<Plan> Pairs = new LinkedList<>();
    private static long dayCounter;
    private LocalDate date;
    private double unit;

    public Plan() {
    }

    public Plan(LocalDate date, double unit) {
        this.date = date;
        this.unit = unit;
    }

    /**
     * Makes pairs of item units and dates
     *
     * @param unitList List of unit items from the base model
     * @param startDate The t_0 starting date
     */
    public void assignDates(List<Double> unitList, LocalDate startDate) {
        Pairs.addAll(collectPairs(unitList, startDate));
    }

    private static List<Plan> collectPairs(List<Double> unitList, LocalDate startDate) {
        return unitList.stream().map(makePair(startDate)).collect(toCollection(ArrayList::new));
    }

    private static Function<Double, Plan> makePair(LocalDate startDate) {
        dayCounter = 0;
        return u -> {
            Plan p = new Plan();
            p.setDate(startDate.plusDays(dayCounter));
            p.setUnit(u);
            dayCounter++;
            return p;
        };
    }

    public List<Plan> getPairs() {
        return Pairs;
    }

    public void setPairs(List<Plan> Pairs) {
        this.Pairs = Pairs;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getUnit() {
        return unit;
    }

    public void setUnit(double unit) {
        this.unit = unit;
    }

}
