package org.tyrande.common.exception;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.api.IErrorCode;
import com.baomidou.mybatisplus.extension.api.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.tyrande.common.exception.customize.CustomException;

import javax.validation.ConstraintViolationException;
import javax.xml.bind.ValidationException;

/**
 * 异常全局处理类
 *
 * @author Tyrande
 */
@Slf4j
@RestControllerAdvice
public class ExceptionGlobalHandler {

    /**
     * 业务异常
     */
    @ExceptionHandler(CustomException.class)
    public R businessException(CustomException e) {
        log.error("[业务异常 -> {}]", e.getMessage(), e);
        return R.failed(new IErrorCode() {
            @Override
            public long getCode() {
                return ObjectUtil.isNull(e.getCode()) ? -1L : e.getCode();
            }

            @Override
            public String getMsg() {
                return e.getMessage();
            }
        });
    }

    /**
     * 参数绑定异常
     */
    @ExceptionHandler(BindException.class)
    public R validatedBindException(BindException e) {
        log.error("[参数绑定异常 -> {}]", e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return R.failed(message);
    }

    /**
     * 参数校验异常
     */
    @ExceptionHandler(ValidationException.class)
    public R handleValidationException(ValidationException e) {
        log.error("[参数校验异常 -> {}]", e.getMessage(), e);
        return R.failed(e.getMessage());
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object validExceptionHandler(MethodArgumentNotValidException e) {
        log.error("[自定义验证异常 -> {}]", e.getMessage(), e);
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return R.failed(message);
    }

    /**
     * Controller 参数绑定错误
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public R handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("[Controller 参数绑定错误 -> {}]", e.getMessage(), e);
        return R.failed(e.getMessage());
    }

    /**
     * 处理方法不支持异常
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public R handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("[请求类型不支持异常 -> {}]", e.getMessage(), e);
        return R.failed("请求类型不支持");
    }

    /**
     * 约束违例异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public R handleConstraintViolationException(ConstraintViolationException e) {
        log.error("[约束违例异常 -> {}]", e.getMessage(), e);
        return R.failed(e.getMessage());
    }

    /**
     * 其他异常
     */
    @ExceptionHandler(value = Exception.class)
    public R otherErrHandler(Exception e) {
        log.error("[发生异常 -> {}]", e.getMessage(), e);
        return R.failed("系统异常");
    }
}
