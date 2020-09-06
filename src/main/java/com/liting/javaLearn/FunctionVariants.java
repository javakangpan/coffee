package com.liting.javaLearn;

import java.util.function.Function;

public class FunctionVariants {
    static Function<Foo,Bar> f1 = f -> new Bar(f);

    public static void main(String[] args) {
        Bar bar = f1.apply(new Foo());
    }
}
class Foo {}

class Bar {
    Foo f;
    Bar(Foo f) { this.f = f; }
}
