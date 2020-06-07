package com.kangpan.enums;

/**
 * 咖啡的状态
 */
public enum CoffeeState {

    NO_SELL(101,"未销售"),
    STOP_SELL(102,"暂停销售"),
    HOT_SELL(103,"促销"),
    IS_SELL(104,"销售中"),
    END_SEL(105,"销售完");

    private int code;
    private String state;

    CoffeeState(int code,String state) {
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
}
