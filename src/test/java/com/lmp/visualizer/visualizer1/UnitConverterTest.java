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
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author SEPALMM
 */
public class UnitConverterTest {

    public UnitConverterTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of makePlanMoneyBased method, of class UnitConverter.
     */
    @Test
    public void testMakePlanMoneyBased() {
        System.out.println("Testing makePlanMoneyBased");
        List<Plan> unitPlan = new LinkedList<>();
        unitPlan.add(new Plan(LocalDate.of(2018, 07, 16), 1d));
        unitPlan.add(new Plan(LocalDate.of(2018, 07, 17), 2d));
        double conversionFactor = 100.0;

        List<Plan> expPlan = new LinkedList<>();
        expPlan.add(new Plan(LocalDate.of(2018, 07, 16), 100d));
        expPlan.add(new Plan(LocalDate.of(2018, 07, 17), 200d));

        UnitConverter instance = new UnitConverter();
        instance.makePlanMoneyBased(unitPlan, conversionFactor);
        List<Plan> actPlan = instance.getPairs();

        assertThat(actPlan.get(0).getDate(), is(expPlan.get(0).getDate()));
        assertThat(actPlan.get(1).getDate(), is(expPlan.get(1).getDate()));

        assertThat(actPlan.get(0).getNumericalValue(), is(expPlan.get(0).getNumericalValue()));
        assertThat(actPlan.get(1).getNumericalValue(), is(expPlan.get(1).getNumericalValue()));

    }

}
