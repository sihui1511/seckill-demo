package com.sihui.seckill.service;

import com.sihui.seckill.dto.Exposer;
import com.sihui.seckill.dto.SeckillExecutetion;
import com.sihui.seckill.entity.Seckill;
import com.sihui.seckill.exception.RepeatKillException;
import com.sihui.seckill.exception.SeckillCloseException;
import com.sihui.seckill.exception.SeckillException;

import java.util.List;

/**
 * @author sihui.sha
 * @date 2018/11/2
 */
public interface SeckillService {

    /**
     * 查询所有秒杀记录
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);

    /**
     * 秒杀开启输出秒杀接口地址
     * 否则输出系统时间和秒杀时间
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExecutetion executeSeckill(long seckillId, String userPhone, String md5)
        throws SeckillException,RepeatKillException,SeckillCloseException;
}
