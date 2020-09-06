package com.liting.javaLearn;


import java.util.function.Supplier;
import java.util.stream.Stream;

public class Coffees implements Supplier<String> {
    @Override
    public String get() {
        return "hello";
    }

    // generate 会调用get()方法
    public static void main(String[] args) {
        Stream.generate(new Coffees()).limit(6).forEach(System.out::println);
    }
}
