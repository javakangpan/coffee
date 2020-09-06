package com.liting.javaLearn;

import java.util.function.Function;

public class CurryingAndPartials {
    // 未柯里化
    static String uncurried(String a, String b) {
        return a + b;
    }

    public static void main(String[] args) {
        Function<String, Function<String, String>> sum =
              a ->  (b -> a + b);
        System.out.println(uncurried("hi","kp"));
        Function<String,String> hi = sum.apply("hi");
        System.out.println(hi.apply("kp"));
    }
}
