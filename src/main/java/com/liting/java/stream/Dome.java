package com.liting.java.stream;

import com.google.common.collect.Lists;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @see java.util.stream.Stream
 */
public class Dome {
    public static void main(String[] args) throws Exception{


        // String counts = new String(Files.readAllBytes(Paths.get("/Dome.java")), StandardCharsets.UTF_8);
        String counts = "1,2,3,4,5,6";
        List<String> countList = Lists.newArrayList(counts.split(","));
        long sum = countList.stream().filter(w -> w.length() > 0).count();
        countList.forEach(System.out::println);
        System.out.println(sum);

        // 流的创建
        Stream<String> words = Stream.of("a,b".split(","));
        Stream<String> emptyStr = Stream.empty();

        List<String> firstElements = words.limit(1).collect(Collectors.toList());

        // Stream<String> stringStream = Stream.generate(()->"kangpan");
        // System.out.println(stringStream.toArray().length);
        // Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        // Object[] powers = Stream.iterate(1.0,p -> p * 2)
        // .peek(e-> System.out.println(e)).limit(10).toArray();
        // List<String> list = Arrays.asList("wello","world","wx");
        // Optional<String> result = list.stream().max(String::compareToIgnoreCase);
        // result = list.stream().filter(s -> s.startsWith("w")).findFirst();
        // System.out.println(result.orElse(""));
        // result = Optional.empty();
        // String temp = result.orElseGet(()-> Locale.getDefault().getDisplayName());
        // Set<String> set = new HashSet<>();
        // result.ifPresent(set::add);
    }
}
