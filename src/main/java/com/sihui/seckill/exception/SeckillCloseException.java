package com.sihui.seckill.exception;

/**
 * 秒杀关闭异常
 * @author sihui.sha
 * @date 2018/11/2
 */
public class SeckillCloseException extends SeckillException {

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
