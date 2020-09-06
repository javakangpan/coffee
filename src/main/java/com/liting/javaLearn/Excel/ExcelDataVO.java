package com.liting.javaLearn.Excel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelDataVO {
    private String name;
    private String sex;
    private String age;
    private List<String> cons;
    private List<String> nums;
    private String remark;
}
