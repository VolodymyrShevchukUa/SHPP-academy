package com.shpp.p2p.cs.vshevchuk.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.util.Stack;

public class Assignment5Part2 extends TextProgram {

    StringBuilder totalResult = new StringBuilder();
    /**
     * in the course of program implementation we are change String to char
     */
    char firstCharNumber;
    char secondCharNumber;
    final int CHAR_CONST_NUMBER = 48;


    @Override
    public void run() {
        System.out.println(addNumericStrings(readLine("Please, input first positive integer"), readLine("Please, input second positive integer")));
    }

    /**
     * @param firstNumber  - inputted first number
     * @param secondNumber - inputted second number
     * @return total result
     */
    private String addNumericStrings(String firstNumber, String secondNumber) {

        try {
            test(firstNumber, secondNumber);
        } catch (Exception e) {
            return "You can input ONLY POSITIVE INTEGER MAN!Try one more time";
        }

        Stack<String> generalNumber = new Stack<>();
        String totalResultStr;
        int residue = 0;
        //this integer determines bigger number
        int biggerLength = (firstNumber.length() >= secondNumber.length() ? firstNumber : secondNumber).length();

        splitAndCompareStack(firstNumber, secondNumber, CHAR_CONST_NUMBER, residue, generalNumber, biggerLength);
        //I use StringBuilder because I can use with him method "Append"
        createGeneralStringNumber(totalResult, generalNumber);
        totalResultStr = totalResult.toString();

        if (totalResultStr.charAt(0) == '0') {
            totalResultStr = totalResultStr.substring(1);
        }
        return totalResultStr;
    }

    /**
     * This method splitting two number, and "load" it in Stack
     *
     * @param charNumber our CONST CHAR == 48!
     */
    private void splitAndCompareStack(String firstNumber, String secondNumber, int charNumber, int residue, Stack<String> generalNumber, int biggerLength) {
        for (int a = 0; a < biggerLength + 1; a++) {
            initCharNumbers(firstNumber, secondNumber, a);

            int custedFirstNum = (int) firstCharNumber - charNumber;
            int custedSecondNum = (int) secondCharNumber - charNumber;
            int result = custedFirstNum + custedSecondNum + residue;

            if (Integer.toString(result).length() > 1) {
                char k = Integer.toString(result).charAt(1);
                residue = 1;
                generalNumber.add(Character.toString(k));
            } else {
                residue = 0;
                generalNumber.add(Integer.toString(result));
            }

        }
    }

    /**
     * This method creating char number from String
     *
     * @param a - "for" counter
     */
    private void initCharNumbers(String firstNumber, String secondNumber, int a) {
        try {
            secondCharNumber = secondNumber.charAt(secondNumber.length() - (a + 1));
        } catch (Exception e) {
            secondCharNumber = '0';
        }
        try {
            firstCharNumber = firstNumber.charAt(firstNumber.length() - (a + 1));
        } catch (Exception e) {
            firstCharNumber = '0';
        }
    }

    /**
     * This method creating final number
     */
    private void createGeneralStringNumber(StringBuilder totalResult, Stack<String> generalNumber) {
        while (!generalNumber.empty()) {
            totalResult.append(generalNumber.pop());
        }
    }

    private void test(String firstNumber, String secondNumber) throws Exception {
        int  test = Integer.parseInt(firstNumber);
        if (test < 0){
            throw new Exception();
        }
        test = Integer.parseInt(secondNumber);
        if (test < 0){
            throw new Exception();
        }
    }
}
