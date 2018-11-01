package com.sihui.seckill.dao;

import com.sihui.seckill.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author sihui.sha
 * @date 2018/11/1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {

    @Autowired
    private SuccessKilledDao successKilledDao;

    @Test
    public void insertSuccessKilled() throws Exception {
        long id = 1001;
        String phone = "1521756789";
        int insertCount = successKilledDao.insertSuccessKilled(id,phone);
        System.out.println("insertCount: "+insertCount);
    }

    @Test
    public void queryByIdWithSeckill() throws Exception {
        long id = 1001;
        String phone = "1521756789";
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(id,phone);
        System.out.println("successKilled: "+successKilled);
        System.out.println("getSeckill: "+successKilled.getCreateTime().getTime());
    }

}