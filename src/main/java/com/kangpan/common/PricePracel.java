package com.kangpan.common;


import java.text.DecimalFormat;

/**
 * 处理商品价格
 */
public class PricePracel {
    public Wrapping discount(double price,int state) {
        return new Wrapping(price, state) {
            @Override
            public double value() {
                // return Double.parseDouble(String.format("%.2f", super.value()));
                // 0 代表一个数字,如果不存在显示0
                // # 代表一个或者多个数字,如果不存在显示空
                return Double.parseDouble(new DecimalFormat("#.00").format(super.value()));
            }
        };
    }
}
