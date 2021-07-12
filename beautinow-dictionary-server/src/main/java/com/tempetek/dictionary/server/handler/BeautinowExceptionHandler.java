package com.tempetek.dictionary.server.handler;

import com.tempetek.dictionary.server.exception.BeautinowException;
import com.tempetek.maviki.web.AjaxMessage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Data
@RestControllerAdvice
public class BeautinowExceptionHandler {

    @ApiModelProperty("错误类型码")
    private Integer code;
    @ApiModelProperty("错误类型描述信息")
    private String message;
    @ApiModelProperty("接口请求是否成功")
    private Boolean success;

    @ExceptionHandler(BeautinowException.class)
    public static AjaxMessage beautinowExceptionHandler(BeautinowException beautinowException) {
        return AjaxMessage.success(beautinowException.getMessage());
    }
}
