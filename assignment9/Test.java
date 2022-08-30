package com.shpp.p2p.cs.shevchuk.assignment9;

import java.util.LinkedList;
import java.util.function.Consumer;

public class Test {
    LinkedList<String> list = new LinkedList<>();
    ArrayList<String> myArray = new ArrayList<>();
    public static void main(String[] args) {
        Test test = new Test();
//        test.list.add("1");
//        test.list.add("1");
//        test.list.add(null);
//        test.list.add("1");
//        test.list.add(null);
//
//        System.out.println(test.list.size());


        test.myArray.add("Dog");
        test.myArray.add("Mister");
        test.myArray.add(null);
        test.myArray.add("Pussy");
        test.myArray.add(null);
        test.myArray.add("Zalypa psa");
        test.myArray.add(null);
        test.myArray.set(3,"Lol");
       test.myArray.remove(0);
 //       System.out.println(test.myArray.get(7));

        test.myArray.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
        test.myArray.forEach((s) -> System.out.println(s));
        test.myArray.forEach(System.out::println);

        System.out.println(test.myArray.size());
        for(String s: test.myArray){
            System.out.println(s);
        }

    }
}
