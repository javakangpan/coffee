package com.kangpan.model;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.ToString;
import java.io.Serializable;

/**
 *  t_coffee
 *  咖啡(咖啡id, 咖啡名称, 状态码, 价格, 咖啡简介)
 */

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Coffee extends BaseEntity implements Serializable {
    private String name;
    private int state;
    // 状态描述
    private String stateDes;
    private long price;
    private String coffeeDesc;
}
