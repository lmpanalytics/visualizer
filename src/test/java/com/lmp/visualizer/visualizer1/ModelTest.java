/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lmp.visualizer.visualizer1;

import java.util.Arrays;
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
public class ModelTest {

    Double[] numericalValueArray;

    public ModelTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of generateLinearGrowthModel method, of class Model.
     */
    @Test
    public void testGenerateLinearGrowthModel() {
        System.out.println("Testing generateLinearGrowthModel");
        Model model = new Model();
        int days = 2192;
        double m = 1.0;
        double b = 10.0;
        model.generateLinearGrowthModel(m, b);

        numericalValueArray = new Double[days];
        for (int t = 0; t < days; t++) {
            if (t == 0) {
                numericalValueArray[t] = b;
            } else {
                numericalValueArray[t] = numericalValueArray[t - 1] + m;
            }

        }

        List<Double> expNumericalValueList = Arrays.asList(numericalValueArray);
        List<Double> resultList = model.getListOfNumericalValues();

//        Test of numerical value list contents
        assertThat(resultList, is(expNumericalValueList));

//        Test of numerical value sum after first 365 days
        double result = resultList.get(364);
        double expSum = 1 * (365 - 1) + 10;
        assertThat(result, is(expSum));

    }

    /**
     * Test of generateStraightLineDepreciationModel method, of class Model.
     */
    @Test
    public void testGenerateStraightLineDepreciationModel() {
        System.out.println("generateStraightLineDepreciationModel");
        Model threeYears = new Model();
        threeYears.generateStraightLineDepreciationModel(3);

        assertThat(threeYears.getListOfNumericalValues().stream()
                .reduce(0.0, Double::sum), is(3d * 365d));
        assertThat(threeYears.getListOfNumericalValues().size(), is(2192));

        Model twentyYears = new Model();
        twentyYears.generateStraightLineDepreciationModel(20);

        assertThat(twentyYears.getListOfNumericalValues().stream()
                .reduce(0.0, Double::sum), is(2192d));
        assertThat(twentyYears.getListOfNumericalValues().size(), is(2192));
    }

}
