package com.sihui.seckill.enums;

/**
 * 使用枚举表述常量
 * @author sihui.sha
 * @date 2018/11/2
 */
public enum SeckillStateEnum {

    SUCCESS(1, "秒杀成功"),
    END(0, "秒杀接数"),
    REPEAT_KILL(-1, "重复秒杀"),
    INNER_ERROR(-2, "系统异常"),
    DATA_REWRITE(-3, "数据异常");

    private int state;

    private String stateInfo;

    SeckillStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static SeckillStateEnum stateOf (int index){
        for (SeckillStateEnum sate : values()){
            if (sate.getState() == index) {
                return sate;
            }
        }
        return null;
    }
}
