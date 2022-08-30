package com.shpp.p2p.cs.shevchuk.assignment9;


import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ArrayList<T> implements List<T>, Iterable<T> {
    private static final int START_INDEX = 8;
    private Object[] list = new Object[START_INDEX];
    private boolean[] booleans = new boolean[START_INDEX];
    private int currentIndex = 0;


    @Override
    public void add(T t) {


        Object[] newList;
        boolean[] newBoolean;
        if (currentIndex < list.length) {
            booleans[currentIndex] = true;
            list[currentIndex++] = t;
        } else {
            newList = new Object[list.length * 2];
            newBoolean = new boolean[list.length * 2];
            for (int i = 0; i < list.length; i++) {
                newList[i] = list[i];
                newBoolean[i] = booleans[i];
            }
            list = newList;
            booleans = newBoolean;
            add(t);
        }
    }

    @Override
    public void clear() {

        list = new Object[START_INDEX];
        booleans = new boolean[START_INDEX];
    }

    @Override
    public void remove(int index) {
        check(index);
        for (int i = index; i < size(); i++) {
            list[i] = list[i + 1];
            booleans[i] = booleans[i + 1];
        }
        list[size()] = null;
        booleans[size()] = false;
        currentIndex--;
    }

    public void remove(T t){
        remove(indexOf(t));
        }



    @Override
    public void set(int index, T t) {
        check(index);
        list[index] = t;
    }

    @Override
    public int size() {

        int result = 0;
        for (int i = 0; i < list.length; i++) {
            if (booleans[i]) {
                result++;
            }
        }
        return result;
    }

    @Override
    public T get(int index) {
        check(index);
        return (T) list[index];
    }

    private void check(int index) {
        if (index >= size() || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public int indexOf(T t) {
        //функціональний інтерфес метод якого вертає булеан
        // тут тернарний оператор і опреділення методу функціонального інтерфейсу.!ШОК
        Predicate<Object> isTrue = t == null ? Objects::isNull : t::equals;
        for (int i = 0; i < list.length; i++) {
            if (isTrue.test(list[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        String newLine = "";
        for (Object o : list) {
            newLine += "[" + o.toString() + "] ";
        }
        return newLine;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index + 1 <= size();
            }

            @Override
            public T next() {
                T currentElement = (T) list[index++];
                return currentElement;

            }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        for (int i = 0; i < list.length; i++) {
            action.accept((T) list[i]);
        }
    }

    public boolean contains(T t){
        return indexOf(t)!=0;
    }
}

