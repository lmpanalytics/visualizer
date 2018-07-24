/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lmp.visualizer.visualizer1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author SEPALMM
 */
public class PlanTest {

    public PlanTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of assignDates method, of class Plan.
     */
    @Test
    public void testAssignDates() {
        System.out.println("Testing assignDates");
        Model model = new Model();
        model.generateLinearModel(1d, 10d);

        List<Double> numericalValueList = model.getListOfNumericalValues();;
        LocalDate startDate = LocalDate.of(1963, 05, 22);
        Plan plan = new Plan();
        plan.assignDates(numericalValueList, startDate);
        List<Plan> dateList = plan.getPairs();
        Plan expPair = new Plan(startDate.plusDays(3L), 13);
        Plan actual = dateList.get(3);
//        Test dates
        assertThat(actual.getDate(), is(equalTo(expPair.getDate())));
        assertThat(actual.getDate(), is(equalTo(LocalDate.of(1963, 05, 22 + 3))));

//        Test numerical values
        assertThat(actual.getNumericalValue(), is(equalTo(expPair.getNumericalValue())));
        assertThat(actual.getNumericalValue(), is(equalTo(13d)));
    }

    /**
     * Test of getDate method, of class Plan.
     */
    @Test
    public void testGetDate() {
        System.out.println("Testing getDate");
        Plan instance = new Plan();
        LocalDate expResult = LocalDate.now();
        instance.setDate(LocalDate.now());
        LocalDate result = instance.getDate();
        assertThat(result, is(expResult));

    }

    /**
     * Test of setDate method, of class Plan.
     */
    @Test
    public void testSetDate() {
        System.out.println("Testing setDate");
        LocalDate date = LocalDate.now();
        Plan instance = new Plan();
        instance.setDate(date);
        assertThat(instance.getDate(), is(date));

    }

    /**
     * Test of getNumericalValue method, of class Plan.
     */
    @Test
    public void testGetNumericalValue() {
        System.out.println("Testing getNumericalValue");
        Plan instance = new Plan();
        double expResult = 0.0;
        double result = instance.getNumericalValue();
        assertThat(result, is(expResult));
    }

    /**
     * Test of setNumericalValue method, of class Plan.
     */
    @Test
    public void testSetNumericalValue() {
        System.out.println("Testing setNumericalValue");
        double numericalValue = 0.0;
        Plan instance = new Plan();
        instance.setNumericalValue(numericalValue);
        assertThat(instance.getNumericalValue(), is(numericalValue));

    }

    /**
     * Test of getPairs method, of class Plan.
     */
    @Test
    public void testGetPairs() {
        System.out.println("Testing getPairs");
        Plan instance = new Plan();
        List<Plan> expResult = new ArrayList();
        expResult.add(new Plan(LocalDate.of(1963, 05, 22), 10d));
        instance.setPairs(expResult);
        List<Plan> result = instance.getPairs();
        assertThat(result, is(expResult));

    }

    /**
     * Test of setPairs method, of class Plan.
     */
    @Test
    public void testSetPairs() {
        System.out.println("Testing setPairs");
        List<Plan> Pairs = new ArrayList<>();
        Pairs.add(new Plan(LocalDate.of(1963, 05, 22), 10d));
        Pairs.add(new Plan(LocalDate.of(1963, 05, 23), 11d));
//        List<Plan> Pairs = null;
        Plan instance = new Plan();
        instance.setPairs(Pairs);

        assertThat(LocalDate.of(1963, 05, 22), is(instance.getPairs().get(0).getDate()));
        assertThat(10d, is(equalTo(instance.getPairs().get(0).getNumericalValue())));

        assertThat(LocalDate.of(1963, 05, 23), is(instance.getPairs().get(1).getDate()));
        assertThat(11d, is(equalTo(instance.getPairs().get(1).getNumericalValue())));
    }

    /**
     * Test of getYear method, of class Plan.
     */
    @Test
    public void testGetYear() {
        System.out.println("Testing getYear");
        Plan instance = new Plan();
        instance.setDate(LocalDate.of(2018, 07, 18));
        int expResult = 2018;
        int actual = instance.getYear();
        assertThat(actual, is(expResult));
    }
}
