/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lmp.visualizer.visualizer1;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author SEPALMM
 */
public class ProfitLossTest {

    public ProfitLossTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of makeProfitLossStatement method, of class ProfitLoss.
     */
    @Test
    public void testMakeProfitLossStatement() {
        System.out.println("Testing makeProfitLossStatement");

        List<Plan> salesRevenuePlan = new LinkedList<>();
        salesRevenuePlan.add(new Plan(LocalDate.of(2018, 02, 01), 100d));
        salesRevenuePlan.add(new Plan(LocalDate.of(2018, 02, 02), 110d));
        salesRevenuePlan.add(new Plan(LocalDate.of(2018, 03, 01), 85d));
        salesRevenuePlan.add(new Plan(LocalDate.of(2020, 11, 01), 200d));

        List<Plan> productionCostPlan = new LinkedList<>();
        productionCostPlan.add(new Plan(LocalDate.of(2018, 01, 01), -60d));
        productionCostPlan.add(new Plan(LocalDate.of(2018, 01, 02), -70d));
        productionCostPlan.add(new Plan(LocalDate.of(2018, 03, 01), -80d));
        productionCostPlan.add(new Plan(LocalDate.of(2020, 10, 01), -180d));

        List<Plan> factoryOverheadCostPlan = new LinkedList<>();
        factoryOverheadCostPlan.add(new Plan(LocalDate.of(2018, 01, 01), -10d));
        factoryOverheadCostPlan.add(new Plan(LocalDate.of(2018, 01, 02), -10d));
        factoryOverheadCostPlan.add(new Plan(LocalDate.of(2018, 03, 01), -10d));
        factoryOverheadCostPlan.add(new Plan(LocalDate.of(2020, 10, 01), -10d));

        ProfitLoss instance = new ProfitLoss();
        instance.makeProfitLossStatement(salesRevenuePlan, productionCostPlan, factoryOverheadCostPlan);

        assertThat(instance.getPL_Map().get(LocalDate.of(2018, 12, 31)).getRevenue(), is(295d));
        assertThat(instance.getPL_Map().get(LocalDate.of(2018, 12, 31)).getDirectMaterialAndLabourCost(), is(-210d));
        assertThat(instance.getPL_Map().get(LocalDate.of(2018, 12, 31)).getFactoryOverheadExpense(), is(-30d));
        assertThat(instance.getPL_Map().get(LocalDate.of(2018, 12, 31)).getGrossProfit(), is(55d));
        assertThat(instance.getPL_Map().get(LocalDate.of(2020, 12, 31)).getGrossProfit(), is(10d));
    }

}
