package com.sihui.seckill.dao;

import com.sihui.seckill.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author sihui.sha
 * @date 2018/11/1
 */
//配置spring和junit整合，junit启动时加载springIOC容器 spring-test，junit
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class SeckillDaoTest {

    //注入Dao实现类依赖
    @Resource
    private SeckillDao seckillDao;

    @Test
    public void queryById() throws Exception {
        long id = 1000;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill);
    }

    @Test
    public void queryAll() throws Exception {
        List<Seckill> seckillList = seckillDao.queryAll(0,10);
        System.out.println(seckillList);
    }

    @Test
    public void reduceNumber() throws Exception {
        long id = 1001;
        Date date = new Date();
        int updateConut = seckillDao.reduceNumber(id,date);
        System.out.println("updateConut:"+updateConut);
    }

}