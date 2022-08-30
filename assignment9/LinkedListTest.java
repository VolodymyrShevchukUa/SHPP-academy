package com.shpp.p2p.cs.shevchuk.assignment9;

public class LinkedListTest {

    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        System.out.println(list.get(3));
    }
}
