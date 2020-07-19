package com.kangpan.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.ToString;
import java.io.Serializable;

/**
 * t_order
 * 订单(订单号id, 客户id, 咖啡id, 订单状态)
 * 1 -> 1 -> n
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Order extends BaseEntity implements Serializable {
    private Customer customer;
    // 已支付 未支付 取消支付
    private int state;
}
