package com.kangpan.exception;

import com.kangpan.common.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.validation.ValidationException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice(basePackages = "com.kangpan.controller")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    /**
     * 处理所有接口数据验证异常
     * @param e MethodArgumentNotValidException
     * @return ResponseEntity
     */
    @ExceptionHandler({ValidationException.class, BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity handleException(Exception e) {
        printTrace(e);
        return ResponseEntity.builder().time(LocalDateTime.now()).message(e.getMessage()).status(HttpStatus.BAD_REQUEST.value()).build();
    }

    /**
     * 打印错误栈轨迹(对于重写抛出的异常 throw e, 调用fillInStackTrace将丢失原来异常发生点的信息)
     * 参考Java编程思想
     * @param e
     */
    private void printTrace(Throwable e) {
        getStackTrace(e);
        for(StackTraceElement stackTraceElement : e.getStackTrace()) {
            log.info("lineNumber:{}, className:{}, fileName:{}, methodName:{},",stackTraceElement.getLineNumber(),
                    stackTraceElement.getClassName(),
                    stackTraceElement.getFileName(),
                    stackTraceElement.getMethodName());
        }
    }

    /**
     * 获取堆栈信息
     * @param e
     */
    private void getStackTrace(Throwable e) {
        log.info("e.getMessage:{}", e.getMessage());
        StringWriter stringWriter = new StringWriter();
        try(PrintWriter printWriter = new PrintWriter(stringWriter)) {
            e.printStackTrace(printWriter);
            log.info("e.printStackTrace:{}", stringWriter.toString());
        }
    }
}
