package com.shpp.p2p.cs.vshevchuk.assignment8;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class stupid {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0;i<5;i++){
            list.add(i);
        }
        Consumer<Integer> syout = stupid::integerVKonsol;
        Consumer<Integer> sss = (n) -> integerVKonsol(n);
        Consumer<Integer> ss = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                integerVKonsol(integer);
            }
        };

       list.forEach(syout);

       // FOR EACH!!!
//        default void forEach(Consumer<? super T> action) {
//            Objects.requireNonNull(action);
//            for (T t : this) {
//                action.accept(t);
//            }
//        }

    }
    private static void integerVKonsol(Integer integer){
        System.out.println(integer);
    }
    private void test(Object object){

    }


//    private void sss(){
//        Runnable runnable = () -> integerVKonsol();
//        runnable.run();
//        Runnable runnable1 = new Runnable() {
//            @Override
//            public void run() {
//                integerVKonsol();
//            }
//        };
//        runnable1.run();
//        Consumer<Object> kk = (o) -> System.out.println(o);
//        kk.accept(new Object());
//
//        Consumer<Integer> integerConsumer = this::test;
//        integerConsumer.accept(1488);
//    }
}
