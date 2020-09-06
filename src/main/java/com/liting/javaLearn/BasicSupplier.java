package com.liting.javaLearn;


import java.util.function.Supplier;

public class BasicSupplier<T> implements Supplier<T> {

    private Class<T> type;

    public BasicSupplier(Class<T> type) {
        this.type = type;
    }

    @Override
    public T get() {
        try {
            return type.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> Supplier<T> create(Class<T> type) {
        return new BasicSupplier<>(type);
    }

    // Integer 没有无参构造方法 newInstance只能初始化无参构造方法
    public static void main(String[] args) throws Exception {
        // Integer integer = BasicSupplier.create(Integer.class).get();
    	// Class c = Class.forName("java.lang.Integer");
        // Integer integer = (Integer)c.newInstance();
        // System.out.println(integer);
        String str = BasicSupplier.create(String.class).get();
        System.out.println(str);
    }
}
