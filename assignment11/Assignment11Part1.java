package com.shpp.p2p.cs.vshevchuk.assignment11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * In this task we must create advanced calculator wich suported
 * brackets, sin, cos, atan, log, sqrt
 */
public class Assignment11Part1 {
    public static String inputFormula;
    public static HashMap<String, String> formulaMap = new HashMap<>();
    private static final PolishInvertNotation polishInvertNotation = new PolishInvertNotation();

    public static void main(String[] args) throws IOException {
        initMap();
        inputFormula = args[0];
        if (formulaMap.containsKey(inputFormula)) {
            polishInvertNotation.getResult(formulaMap.get(inputFormula));
        }
        String startFormula = new FormulaChecker().getFormula(args);
        String firstPriority = new FirstPriority().getFormula(startFormula);
        System.out.println(polishInvertNotation.getResult(firstPriority));
    }

    /**
     * Maybe you want launch the test...
     */
//    static class Test {
//    public static void main(String[] args) throws IOException {
//        System.err.println(new Assignment11Part1().run(new String[]{"2-(-2-2+3)"}).equals(3d));
//        System.err.println(new Assignment11Part1().run(new String[]{"2-(-2)"}).equals(4d));
//        System.err.println(new Assignment11Part1().run(new String[]{"sin(s)+cos(s+90)", "s=90"}).equals(0.8939966636005579 + -0.5984600690578581));
//        System.err.println(new Assignment11Part1().run(new String[]{"s+s", "s=90"}).equals(180d));
//        System.err.println(new Assignment11Part1().run(new String[]{"-s+s", "s=90"}).equals(0d));
//        System.err.println(new Assignment11Part1().run(new String[]{"sin(s)+cos(s)", "s=90"}).equals(0.8939966636005579 + -0.4480736161291701));
//        System.err.println(new Assignment11Part1().run(new String[]{"sin(90)"}).equals(0.8939966636005579));
//        System.err.println(new Assignment11Part1().run(new String[]{"cos(90)"}).equals(-0.4480736161291701));
//        System.err.println(new Assignment11Part1().run(new String[]{"log2(256)"}).equals(8d));
//        System.err.println(new Assignment11Part1().run(new String[]{"log10(1000)"}).equals(3d));
//        System.err.println(new Assignment11Part1().run(new String[]{"-2*5/2.5^2-100^2/100"}).equals(-101.6));
//        System.err.println(new Assignment11Part1().run(new String[]{"2+(-2)"}).equals(0d));
//        System.err.println(new Assignment11Part1().run(new String[]{"4^3^2"}).equals(4096d));//(4^3)^2
//    }
//}
//    public Double run(String[] args) throws IOException {
//        String startFormula = new FormulaChecker().getFormula(args);
//        String firstPriority = new FirstPriority().getFormula(startFormula);
//        return  Double.parseDouble(new PolishInvertNotation().getResult(firstPriority));
//    }
    private static LinkedList<String> readText() throws IOException {
        LinkedList<String> lines = new LinkedList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(FormulaChecker.path));
        String line = bufferedReader.readLine();
        while (line != null) {
            lines.add(line);
            line = bufferedReader.readLine();
        }
        return lines;
    }
    private static void initMap() throws IOException {
        LinkedList<String> strings = readText();
        for(int i = 0; i< strings.size(); i++){
            formulaMap.put(strings.get(i), strings.get(++i));
        }
    }
}








