package com.kangpan.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.ToString;
import java.io.Serializable;
import java.util.List;

/**
 * t_record
 * 记录(记录id, 咖啡id, 订单id)
 *  1 -> 1 -> n
 * 应用场景: 点击菜单中的 coffee,可以查看此 coffee 的历史订单及评价
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Record extends BaseEntity implements Serializable{
    private Coffee coffee;
    private List<Order> orderList;
}
