package com.sihui.seckill.exception;

/**
 * 重复秒杀异常
 * @author sihui.sha
 * @date 2018/11/2
 */
public class RepeatKillException extends SeckillException{

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
