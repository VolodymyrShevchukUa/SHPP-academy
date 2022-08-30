package com.shpp.p2p.cs.vshevchuk.assignment4;

public class task {

    public static void main(String[] args) {
        System.out.println(isLeapYear(2000));
    }
    public static boolean isLeapYear(int year) {
        if(year%4 == 0 || year%400 == 0){
            return true;
        }
        if(year%100==0){
            return false;
        }
        return false;
    }

}
