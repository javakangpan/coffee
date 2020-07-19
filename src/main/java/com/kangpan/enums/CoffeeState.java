package com.kangpan.enums;


/**
 * 咖啡的状态
 */
public enum CoffeeState {

    NO_SELL(101,"未销售"),
    STOP_SELL(102,"暂停销售"),
    HOT_SELL(103,"促销"),
    LIMIT_SEL(0.9, 105, "九折限时活动"),
    IS_SELL(0.9, 104, "销售中"),
    END_SEL(106,"销售完");

    private int code;
    private String state;
    private double discount;

    CoffeeState(double discount, int code, String state) {
        this.discount = discount;
        this.code = code;
        this.state = state;
    }

    CoffeeState(int code, String state) {
        this.code = code;
        this.state = state;
    }

   public static String getStateDesc(int code) {
        CoffeeState[] coffeeStateEnums = values();
        for (CoffeeState coffeeState : coffeeStateEnums) {
            if (coffeeState.code == code) {
                return coffeeState.state;
            }
        }
        return "";
    }
    public static double getDiscount(int code) {
        CoffeeState[] coffeeStateEnums = values();
        for (CoffeeState coffeeState : coffeeStateEnums) {
            if (coffeeState.code == code) {
                return coffeeState.discount;
            }
        }
        return 0.0;
    }
}
