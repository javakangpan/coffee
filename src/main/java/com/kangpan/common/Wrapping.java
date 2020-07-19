package com.kangpan.common;


import com.kangpan.enums.CoffeeState;

/**
 * 打折和恢复原价
 */
public class Wrapping {
    private double price;
    private int state;
    public Wrapping(double price,int state) {
        this.price = price;
        this.state = state;
    }
    public double value() {
        if(state == 104) {
            return price * CoffeeState.getDiscount(state);
        } else {
            return price / CoffeeState.getDiscount(state);
        }
    }
}
