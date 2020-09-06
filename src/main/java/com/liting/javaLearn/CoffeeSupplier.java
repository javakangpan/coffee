package com.liting.javaLearn;

import com.kangpan.model.Coffee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CoffeeSupplier extends  ArrayList<Coffee> implements Supplier<Coffee> {

    Random random = new Random(new Date().getTime());

    @Override
    public Coffee get() {
        IntStream.range(0,5).forEach( c -> add(Coffee.builder().id(c).build()));
        return get(random.nextInt(4));
    }


    public static void main(String[] args) {
        // CoffeeSupplier coffees = new CoffeeSupplier();
        // coffees.get();
        // coffees.forEach(c -> System.out.println(c.getId()));
        // Stream.generate(new CoffeeSupplier()).limit(5).forEach(c -> System.out.println("id:" + c.getId()));
        List<Coffee> lists = Stream.generate(new CoffeeSupplier()).limit(5).collect(Collectors.toList());

        CoffeeSupplier coffees = new CoffeeSupplier();
    }
}
