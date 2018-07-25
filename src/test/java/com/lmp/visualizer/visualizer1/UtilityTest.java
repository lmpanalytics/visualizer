/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lmp.visualizer.visualizer1;

import static org.hamcrest.CoreMatchers.is;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author SEPALMM
 */
public class UtilityTest {

    public UtilityTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of calculateStraightLineDepreciationCharge method, of class Utility.
     */
    @Test
    public void testCalculateStraightLineDepreciationCharge() {
        System.out.println("Testing calculateStraightLineDepreciationCharge");
        double years = 3.0;
        double currentValue = 1250.0;
        double residualValue = 50.0;
        Double expResult = -400.0 / 365.0;
        Double result = Utility.calculateStraightLineDepreciationCharge(years, currentValue, residualValue);
        assertThat(result, is(expResult));

    }

}
