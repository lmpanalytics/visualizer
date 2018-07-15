/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lmp.visualizer.visualizer1;

import java.util.List;

/**
 *
 * @author SEPALMM
 */
public class VisualizerMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Generate a unit list
        Model.generateLinearModel(1d, 10d);
        List<Double> unitList = Model.getListOfUnits();
    }

}
