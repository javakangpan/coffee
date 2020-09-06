package com.liting.javaLearn;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class RandomList<T> extends ArrayList<T> {
    // private List<T> list = Lists.newArrayList();
    Random random = new Random(new Date().getTime());
    public void push(T item) {
        add(item);
    }
    public T select() {
        return get(random.nextInt(size()));
    }

    public static void main(String[] args) {
        RandomList<String> randomList = new RandomList<>();
        String str = "1,2,3,4,5,6,7,8,9,0";
        Splitter.on(",")
                .trimResults()
                .omitEmptyStrings()
                .split(str)
                .forEach(c->randomList.push(c));
        IntStream.range(0,4).forEach(i -> System.out.print(randomList.select()));
    }
}
