package com.kangpan.model;

import java.io.Serializable;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.ToString;

/**
 *  t_valuation(评论id, 咖啡id, 客户id, 评论, 是否好评)
 *  1 -> 1 -> 1
 *  应用场景: 客户对一款咖啡做出的评价
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Valuation extends BaseEntity implements Serializable {
    private long coffeeId;
    private long customerId;
    private String content;
    private boolean isFavorable;
}
