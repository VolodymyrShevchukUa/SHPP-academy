package com.shpp.p2p.cs.shevchuk.assignment9;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class myTest {

    public static void main(String[] args) {
        // Функціональні інтерфейси 4
        Function<String ,Integer> function = new Function() {
            @Override
            public Object apply(Object o) {
                return null;
            }
        };
        Supplier<String> supplier = new Supplier<String>() {
            @Override
            public String get() {
                return null;
            }
        };
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {

            }
        };
        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return false;
            }
        };


        // Женеріки !!!
                myTest myTest = new myTest();

        List<myTest.A> aList = new ArrayList<>();
        List<myTest.B> bList = new ArrayList<>();
        List<myTest.C> cList = new ArrayList<>();
        ArrayList<Object> objects = new ArrayList<>();

        bList.add(myTest.new B());


        aList.add(myTest.new A());
        aList.add(myTest.new B());
        aList.add(myTest.new C());

        myTest.ext(cList);
        myTest.ext(aList);
        myTest.ext(bList);


        myTest.sup(aList);

        objects.add(myTest.new A());


        myTest.sup(aList);
    }
    // Засунути можна лише суперклас
    public void sup(List<? super B> param) {
        // В сам список можна добавляти лише дочірні від B,логічно, бо не знаєш який по касту буде клас,
        // але знаєш що точно B і нижче
        param.add(new B());
        // Доступ маєм тільки до обджект
        for(Object o:param){


        }

     //   param.add(new A());

    }

    private void ext(java.util.List<? extends A> param) {
        B b = new B();
        // Ти не мож передати в ліст ніхуя , бо не знаєш скільки взагалі наслідників є від А
        for(A a:param){
            a.method();
        }



    }

     class A {
        void method() {
            System.out.println("a");
        }

        int a = 1;
    }

    class B extends A {
    void method(){
        System.out.println("b");
    }



    }
    class C extends A{
    }
}
