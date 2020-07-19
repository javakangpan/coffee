package com.kangpan.common;


import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import javax.validation.ValidationException;
import java.util.List;
import java.util.Locale;


/**
 * @date 2020/05/20
 * @author 康盼Java开发工程师
 * @version 1
 *
 * 自定义的断言 用于VO字段校验
 */
public class Assert {

    /**
     * 获取参数及自定义的校验信息
     * @param expression
     * @param bindingResult
     */
    public static void validation(boolean expression, BindingResult bindingResult) {
        String errorInfo = "[%s: %s]; ";
        StringBuilder stringBuilder = new StringBuilder();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        fieldErrors.forEach(fieldError -> stringBuilder.append(String.format(Locale.ROOT, errorInfo, fieldError.getField(), fieldError.getDefaultMessage())));
        if (expression) {
            throw new ValidationException(stringBuilder.toString());
        }
    }
}
