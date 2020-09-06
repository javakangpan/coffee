package com.liting.javaLearn;

// 没有方法引用的对象

import java.util.function.Supplier;

class X {
    String f() { return "X::f()"; }
    static X get() {
       return new X();
    }
}

interface MakeString {
    String make();
}

interface TransformX {
    String transform(X x);
}

public class UnboundMethodReference {
    public static void main(String[] args) {
        Supplier s = X::get;
        s.get();
        // MakeString ms = X::f; // [1]
        TransformX sp = X::f;
        X x = new X();
        System.out.println(sp.transform(x)); // [2]
        System.out.println(x.f()); // 同等效果
    }
}
