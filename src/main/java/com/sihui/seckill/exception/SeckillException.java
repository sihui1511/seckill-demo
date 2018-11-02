package com.sihui.seckill.exception;

/**
 * 秒杀相关业务异常
 * @author sihui.sha
 * @date 2018/11/2
 */
public class SeckillException extends RuntimeException {

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
