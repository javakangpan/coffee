package com.kangpan.model;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
    @NotBlank(message = "name can not be null") //只能作用在 String 上,不能为null, 而且调用trim()后, 长度必须大于0
    private String name;
    @Max(value = 106, message = "state is not in coffeeState")
    @Min(value = 101, message = "state is not in coffeeState")
    private int state;
    @Min(value = 0, message = "price must be higher or equal to zero")
    private double price;
    @Size(min = 1,max = 200,message = "coffeeDesc length must in 1~200 size")
    private String coffeeDesc;
    // 状态描述
    private String stateDes;
}
