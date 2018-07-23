/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lmp.visualizer.visualizer1;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Models the Profit and Loss Statement
 *
 * @author SEPALMM
 */
public class ProfitLoss {

    private Map<LocalDate, ProfitLoss> PL_Map = new TreeMap<>();
    ;
    private double revenue;
    private double directMaterialAndLabourCost;
    private double factoryOverheadExpense;
    private double depreciation;
    private double grossProfit;

    public ProfitLoss() {
    }

    public ProfitLoss(double revenue, double directMaterialAndLabourCost, double factoryOverheadExpense, double depreciation, double grossProfit) {
        this.revenue = revenue;
        this.directMaterialAndLabourCost = directMaterialAndLabourCost;
        this.factoryOverheadExpense = factoryOverheadExpense;
        this.depreciation = depreciation;
        this.grossProfit = grossProfit;
    }

    /**
     * Collects sales, cost, and other lists containing Date and Value pairs.
     * Then sums values by final day of each year, calculates and produces the
     * P&L Statement.
     *
     * @param salesRevenuePlan The planned sales
     * @param productionCostPlan The planned direct labor and raw material costs
     */
    public void makeProfitLossStatement(List<Plan> salesRevenuePlan, List<Plan> productionCostPlan) {
        PL_Map.putAll(ProfitLoss.processPlans(salesRevenuePlan, productionCostPlan));
    }

    private static Map<LocalDate, ProfitLoss> processPlans(List<Plan> salesRevenuePlan, List<Plan> productionCostPlan) {
        Map<LocalDate, ProfitLoss> masterMap = new HashMap<>();

        populateMasterMap(masterMap,
                convertToEndOfYearDateMap(collectDataToAnnualMap(productionCostPlan)),
                convertToEndOfYearDateMap(collectDataToAnnualMap(salesRevenuePlan))
        );

        return masterMap;
    }

    private static void populateMasterMap(
            Map<LocalDate, ProfitLoss> masterMap,
            Map<LocalDate, Double> productionMap,
            Map<LocalDate, Double> revenueMap
    ) {
        productionMap.forEach((productionDate, directMaterialAndLabourCost) -> masterMap.put(productionDate,
                new ProfitLoss(0d, directMaterialAndLabourCost, 0d, 0d, calculateGrossProfit(0d, directMaterialAndLabourCost, 0d, 0d))
        ));

//        Add the Revenue Map data to the Master Map
        revenueMap.forEach((revenueDate, revenue) -> masterMap.putIfAbsent(revenueDate,
                new ProfitLoss(revenue, 0d, 0d, 0d, calculateGrossProfit(revenue, 0d, 0d, 0d))
        ));

//        If master map already contains key, then:
//        (a) set Revenue
        revenueMap.forEach((revenueDate, revenue)
                -> masterMap.entrySet().stream().filter(e -> e.getKey().equals(revenueDate)).
                        forEach(e -> e.getValue().setRevenue(revenue)));

//        (b) recalculate and set the Gross Profit
        revenueMap.forEach((revenueDate, revenue)
                -> masterMap.entrySet().stream().filter(e -> e.getKey().equals(revenueDate)).
                        forEach(e -> e.getValue().setGrossProfit(
                        calculateGrossProfit(revenue,
                                e.getValue().getDirectMaterialAndLabourCost(),
                                e.getValue().getFactoryOverheadExpense(),
                                e.getValue().getDepreciation())
                )));

    }

    private static Map<Integer, Double> collectDataToAnnualMap(List<Plan> plan) {
        Map<Integer, Double> annualMap = plan.stream()
                .collect(Collectors.groupingBy(Plan::getYear,
                        Collectors.summingDouble(Plan::getNumericalValue)));
        return annualMap;
    }

    private static Map<LocalDate, Double> convertToEndOfYearDateMap(Map<Integer, Double> annualMap) {

        Map<LocalDate, Double> endOfYearDateMap = annualMap.entrySet().stream().
                collect(Collectors.toMap(
                        entry -> LocalDate.of(entry.getKey(), 12, 31), // The key
                        entry -> entry.getValue() // The value
                ));
        return endOfYearDateMap;
    }

    private static double calculateGrossProfit(double revenue, double directMaterialAndLabourCost, double factoryOverheadExpense, double depreciation) {
        return (revenue + directMaterialAndLabourCost + factoryOverheadExpense + depreciation);
    }

//    Getters and Setters
    public Map<LocalDate, ProfitLoss> getPL_Map() {
        return PL_Map;
    }

    public void setPL_Map(Map<LocalDate, ProfitLoss> PL_Map) {
        this.PL_Map = PL_Map;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public double getDirectMaterialAndLabourCost() {
        return directMaterialAndLabourCost;
    }

    public void setDirectMaterialAndLabourCost(double directMaterialAndLabourCost) {
        this.directMaterialAndLabourCost = directMaterialAndLabourCost;
    }

    public double getFactoryOverheadExpense() {
        return factoryOverheadExpense;
    }

    public void setFactoryOverheadExpense(double factoryOverheadExpense) {
        this.factoryOverheadExpense = factoryOverheadExpense;
    }

    public double getDepreciation() {
        return depreciation;
    }

    public void setDepreciation(double depreciation) {
        this.depreciation = depreciation;
    }

    public double getGrossProfit() {
        return grossProfit;
    }

    public void setGrossProfit(double grossProfit) {
        this.grossProfit = grossProfit;
    }

}
