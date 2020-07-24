package org.tyrande.starter.exception.customize;

import lombok.Getter;

/**
 * 自定义业务异常
 *
 * @author Tyrande
 */
@Getter
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Long code;

    private final String message;

    public CustomException(String message) {
        this.message = message;
    }

    public CustomException(String message, Long code) {
        this.message = message;
        this.code = code;
    }

    public CustomException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }
}
