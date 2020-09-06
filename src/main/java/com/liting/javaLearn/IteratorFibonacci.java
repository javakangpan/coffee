package com.liting.javaLearn;

import java.util.Iterator;

public class IteratorFibonacci extends Fibonacci implements Iterable<Integer> {

    private int i;

    public IteratorFibonacci(int i) {
        this.i = i;
    }

    @Override
    public Integer next() {
        return super.next();
    }

    class FibonacciItertor implements Iterator<Integer> {

        @Override
        public boolean hasNext() {
            return i-- > 0;
        }

        @Override
        public Integer next() {
            return IteratorFibonacci.this.next();
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new FibonacciItertor();
    }

    public static void main(String[] args) {
        IteratorFibonacci integers = new IteratorFibonacci(10);
        for(Integer integer : integers) {
            System.out.println(integer);
        }
    }
}
