/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lmp.visualizer.visualizer1;

/**
 * Various Utility functions
 *
 * @author SEPALMM
 */
public class Utility {

    /**
     * Calculates depreciation charge according to the Straight-Line method
     *
     * @param years The asset's service lifetime
     * @param currentValue The current (book) value of the asset
     * @param residualValue The scrap value of the asset
     * @return The depreciation charge, per day. Negative as it is charged to
     * PL.
     */
    public static Double calculateStraightLineDepreciationCharge(double years, double currentValue, double residualValue) {
        return -(currentValue - residualValue) / (365 * years);
    }

}
