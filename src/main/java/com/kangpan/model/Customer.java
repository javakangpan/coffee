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
 * t_customer(客户id, 账号, 密码, 姓名, 出生日期, 性别, 手机号码)
 */

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Customer extends BaseEntity implements Serializable {
    private String account;
    private String password;
    private String userName;
    private String birthday;
    private String gender;
    private long phone;
    private List<Coffee> items;
}
