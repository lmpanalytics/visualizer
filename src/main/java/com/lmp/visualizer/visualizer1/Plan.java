/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lmp.visualizer.visualizer1;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import static java.util.stream.Collectors.toCollection;

/**
 * Converts the numericalValue lists into a calendar based plan
 *
 * @author SEPALMM
 */
public class Plan {

    private List<Plan> Pairs = new LinkedList<>();
    private static long dayCounter;
    private LocalDate date;
    private int year;
    private double numericalValue;

    public Plan() {
    }

    public Plan(LocalDate date, double numericalValue) {
        this.date = date;
        this.numericalValue = numericalValue;
    }

    /**
     * Makes pairs of numerical values and dates
     *
     * @param numericalValueList List of numerical values from the base model
     * @param startDate The t_0 starting date
     */
    public void assignDates(List<Double> numericalValueList, LocalDate startDate) {
        Pairs.addAll(collectPairs(numericalValueList, startDate));
    }

    private static List<Plan> collectPairs(List<Double> numericalValueList, LocalDate startDate) {
        return numericalValueList.stream().map(makePair(startDate)).collect(toCollection(LinkedList::new));
    }

    private static Function<Double, Plan> makePair(LocalDate startDate) {
        dayCounter = 0;
        return v -> {
            Plan p = new Plan();
            p.setDate(startDate.plusDays(dayCounter));
            p.setNumericalValue(v);
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

    public double getNumericalValue() {
        return numericalValue;
    }

    public void setNumericalValue(double numericalValue) {
        this.numericalValue = numericalValue;
    }

    public int getYear() {
        return year = getDate().getYear();
    }

    public void setYear(int year) {
        this.year = year;
    }

}
