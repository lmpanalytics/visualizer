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
//        Set asset's service lifetime for depreciation calculations
        double depreciationYears = 5.0;

// Generate unit lists for production, factory overhead costs, and depreciation
        Model productModel = new Model();
        productModel.generateLinearGrowthModel(1d, 10d);
        List<Double> productUnitList = productModel.getListOfNumericalValues();

        Model factoryOverheadModel = new Model();
        factoryOverheadModel.generateLinearGrowthModel(0d, 16d);
        List<Double> factoryOverheadUnitList = factoryOverheadModel.getListOfNumericalValues();

        Model depreciationModel = new Model();
        depreciationModel.generateStraightLineDepreciationModel((int) depreciationYears);
        List<Double> depreciationUnitList = depreciationModel.getListOfNumericalValues();

//        Generate plans
        Plan productionPlan = new Plan();
        productionPlan.assignDates(productUnitList, LocalDate.of(2017, 01, 01));

        Plan salesPlan = new Plan();
        salesPlan.assignDates(productUnitList, LocalDate.of(2018, 01, 01));

        Plan factoryOverheadPlan = new Plan();
        factoryOverheadPlan.assignDates(factoryOverheadUnitList, LocalDate.of(2017, 01, 01));

        Plan depreciationPlan = new Plan();
        depreciationPlan.assignDates(depreciationUnitList, LocalDate.of(2017, 01, 01));

//        Convert plans to money base
        UnitConverter ucProductionPlan = new UnitConverter();
        ucProductionPlan.makePlanMoneyBased(productionPlan.getPairs(), -1d);
        List<Plan> productionCostPlan = ucProductionPlan.getPairs();

        UnitConverter ucSalesPlan = new UnitConverter();
        ucSalesPlan.makePlanMoneyBased(salesPlan.getPairs(), 1d);
        List<Plan> salesRevenuePlan = ucSalesPlan.getPairs();

        UnitConverter ucFactoryOverheadPlan = new UnitConverter();
        ucFactoryOverheadPlan.makePlanMoneyBased(factoryOverheadPlan.getPairs(), -1d);
        List<Plan> factoryOverheadCostPlan = ucFactoryOverheadPlan.getPairs();

        UnitConverter ucDepreciationPlan = new UnitConverter();
        ucDepreciationPlan.makePlanMoneyBased(depreciationPlan.getPairs(), Utility.calculateStraightLineDepreciationCharge(depreciationYears, 10_000d, 1000d));
        List<Plan> depreciationChargePlan = ucDepreciationPlan.getPairs();

//        Calculate Profit Loss
        ProfitLoss profitLoss = new ProfitLoss();
        profitLoss.makeProfitLossStatement(salesRevenuePlan, productionCostPlan, factoryOverheadCostPlan, depreciationChargePlan);
        Map<LocalDate, ProfitLoss> plMap = profitLoss.getPL_Map();

        printProfitLoss(plMap, 10);
    }

}
