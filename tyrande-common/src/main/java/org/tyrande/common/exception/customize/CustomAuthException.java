package org.tyrande.common.exception.customize;

import lombok.Getter;

/**
 * 自定义认证异常
 *
 * @author Tyrande
 */
@Getter
public class CustomAuthException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Long code;

    private final String message;

    public CustomAuthException(String message) {
        this.message = message;
    }

    public CustomAuthException(String message, Long code) {
        this.message = message;
        this.code = code;
    }

    public CustomAuthException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }
}
