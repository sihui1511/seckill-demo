package com.sihui.seckill.dao;

import com.sihui.seckill.entity.Seckill;
import com.sihui.seckill.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

/**
 * @author sihui.sha
 * @date 2018/10/31
 */
public interface SuccessKilledDao {

    /**
     * 插入购买明细，可过滤重复
     * @param seckillId
     * @param userPhone
     * @return 插入的行数
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") String userPhone);

    /**
     * 根据ID查询SuccessKilled并携带秒杀产品对象实体
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") String userPhone);
}
