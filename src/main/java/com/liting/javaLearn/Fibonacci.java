package com.liting.javaLearn;
// f(x) = f(x-1) + f(x-2)
public class Fibonacci implements Generator<Integer> {

    private int i;

    public Fibonacci(int i) {
        this.i = i;
    }

    public Fibonacci() {

    }

    @Override
    public Integer next() {
        return getFibonacci(i++);
    }

    public  Integer getFibonacci(int i) {
       if(i < 2 && i >= 0) {
           return 1;
       } else {
           return getFibonacci(i - 1) + getFibonacci(i- 2);
       }
    }

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci(0);
        for(int i = 0; i < 10 ; i++) {
            System.out.println(fibonacci.next());
        }
    }
}
