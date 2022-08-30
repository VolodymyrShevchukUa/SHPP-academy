package com.shpp.p2p.cs.shevchuk.assignment9;

import java.util.Iterator;

public class LinkedList<T> implements List<T> {

    private Nodes<T> firstNodes = null;
    private Nodes<T> lastNodes = null;
    private int size = 0;



    public boolean hasNext() {
        return false;
    }

    private Nodes<T> getNodesByIndex(int index){
        Nodes<T> currentNodes = firstNodes;
        for(int i = 0; i<index;i++){
            currentNodes = currentNodes.nextNodes;
        }
        return currentNodes;

    }

    @Override
    public void add(T t) {

        if(firstNodes == null) {
            firstNodes = new Nodes<>(t, null, null);
            lastNodes = firstNodes;
        }else{
            Nodes<T> newLastElement = new Nodes<>(t, lastNodes,null);
            lastNodes.nextNodes = newLastElement;
            lastNodes = newLastElement;
        }
        size++;
    }

    @Override
    public void clear() {
        firstNodes = null;
        lastNodes = null;
        size = 0;
    }

    @Override
    public void remove(int index) {

    }

    @Override
    public void set(int Index, T t) {

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        return getNodesByIndex(index).element;

        // Це на пам'ять =0
//        Nodes<T> currentNodes = firstNodes;
//        Nodes<T> returnedNodes = null;
//        for(int i = 0; i<index;i++){
//            returnedNodes = currentNodes.nextNodes;
//            if(i == index-1){
//                return returnedNodes.element;
//            }
//            currentNodes = returnedNodes;
//        }
//        assert returnedNodes != null;
//        return firstNodes.element;
    }

    @Override
    public int indexOf(T t) {
        return 0;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
//        return new Iterator<T>() {
//            int index = 0;
//
//            @Override
//            public boolean hasNext() {
//                return index + 1 <= size();
//            }
//
//            @Override
//            public T next() {
//                T currentElement = (T) list[index++];
//                return currentElement;
//
//            }
//        };

    }


    private class Nodes<T> {

        T element;
        Nodes<T> previouseNodes;
        Nodes<T> nextNodes;


        Nodes(T element, Nodes<T> previouseNodes, Nodes<T> nextNodes) {
            this.element = element;
            this.previouseNodes = previouseNodes;
            this.nextNodes = nextNodes;
        }

//        @Override
//        public String toString() {
//            return  "element = " + element + " previous element = " + previousElement + " next element = " + nextElement;
//        }
    }
}
