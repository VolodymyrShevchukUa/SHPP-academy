package com.shpp.p2p.cs.shevchuk.assignment9;

public interface List<T> extends Iterable<T> {


    void add(T t);

    void clear();

    void remove(int index);

    void set(int Index,T t);

    int size();

    T get(int index);

    int indexOf(T t);



}
