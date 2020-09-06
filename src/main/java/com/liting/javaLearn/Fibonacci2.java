package com.liting.javaLearn;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Fibonacci2 implements Supplier<Integer>,Iterable<Integer> {

    public int size;

    public int i = 0;

    public Fibonacci2(int size) {
        this.size = size;
    }

    public int getResult(int i) {
        if(i < 2) {
            return 1;
        } else {
            return getResult(i-1) + getResult(i-2);
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Fibona();
    }

    class Fibona implements  Iterator<Integer> {
        @Override
        public void remove() {

        }

        @Override
        public void forEachRemaining(Consumer<? super Integer> action) {

        }

        @Override
        public boolean hasNext() {
            return size > 0;
        }

        @Override
        public Integer next() {
            size--;
            return Fibonacci2.this.get();
        }
    }

    @Override
    public Integer get() {
        return getResult(i++);
    }

    public static void main(String[] args) {
        Stream.generate(new Fibonacci2(-1)).limit(10).map(n -> n + " ").forEach(System.out::print);
        for(Integer i : new Fibonacci2(10)) {
            System.out.print(i + " ");
        }
    }
}
