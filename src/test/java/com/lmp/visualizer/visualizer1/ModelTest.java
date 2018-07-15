/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lmp.visualizer.visualizer1;

import java.util.Arrays;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author SEPALMM
 */
public class ModelTest {

    Double[] unitArray;

    public ModelTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of generateLinearModel method, of class Model.
     */
    @Test
    public void testGenerateLinearModel() {
        System.out.println("Testing generateLinearModel");
        int days = 2192;
        double m = 1.0;
        double b = 10.0;
        Model.generateLinearModel(m, b);

        unitArray = new Double[days];
        for (int t = 0; t < days; t++) {
            if (t == 0) {
                unitArray[t] = b;
            } else {
                unitArray[t] = unitArray[t - 1] + m;
            }

        }

        List<Double> expUnitList = Arrays.asList(unitArray);
        List<Double> resultList = Model.getListOfUnits();

//        Test of unit list contents
        assertEquals(expUnitList, resultList);

//        Test of unit sum after first 365 days
        double result = resultList.get(364);
        double expSum = 1 * (365 - 1) + 10;
        assertEquals(expSum, result, 0.000001);

    }

}
