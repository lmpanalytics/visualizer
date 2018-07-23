/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lmp.visualizer.visualizer1;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 *
 * @author SEPALMM
 */
public class VisualizerMain {

    private static void printProfitLoss(Map<LocalDate, ProfitLoss> plMap, int limit) {
        System.out.printf("%s%20s%15s%15s%15s%15s%n", "Date", "Revenue", "Direct Costs", "Overhead", "Depreciation", "Gross Profit");
        plMap.entrySet().stream().limit(limit).forEach((entry) -> {
            LocalDate key = entry.getKey();
            ProfitLoss v = entry.getValue();
            System.out.printf("%s:%15.2f%15.2f%15.2f%15.2f%15.2f%n", key, v.getRevenue(), v.getDirectMaterialAndLabourCost(), v.getFactoryOverheadExpense(), v.getDepreciation(), v.getGrossProfit());
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Generate a unit list
        Model.generateLinearModel(1d, 10d);
        List<Double> unitList = Model.getListOfNumericalValues();

//        Generate plans
        Plan productionPlan = new Plan();
        productionPlan.assignDates(unitList, LocalDate.of(2016, 02, 27));

        Plan salesPlan = new Plan();
        salesPlan.assignDates(unitList, LocalDate.of(2016, 03, 27));

//        Convert plans to money base
        UnitConverter ucProductionPlan = new UnitConverter();
        ucProductionPlan.makePlanMoneyBased(productionPlan.getPairs(), -100d);
        List<Plan> productionCostPlan = ucProductionPlan.getPairs();

        UnitConverter ucSalesPlan = new UnitConverter();
        ucSalesPlan.makePlanMoneyBased(salesPlan.getPairs(), 120d);
        List<Plan> salesRevenuePlan = ucSalesPlan.getPairs();

//        Calculate Profit Loss
        ProfitLoss profitLoss = new ProfitLoss();
        profitLoss.makeProfitLossStatement(salesRevenuePlan, productionCostPlan);
        Map<LocalDate, ProfitLoss> plMap = profitLoss.getPL_Map();

        printProfitLoss(plMap, 10);
    }

}
