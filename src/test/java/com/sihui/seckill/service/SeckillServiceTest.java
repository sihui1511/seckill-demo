package com.sihui.seckill.service;

import com.sihui.seckill.dto.Exposer;
import com.sihui.seckill.dto.SeckillExecutetion;
import com.sihui.seckill.entity.Seckill;
import com.sihui.seckill.exception.RepeatKillException;
import com.sihui.seckill.exception.SeckillCloseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author sihui.sha
 * @date 2018/11/2
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() throws Exception {
        List<Seckill> seckillList = seckillService.getSeckillList();
        logger.info("list={}",seckillList);
    }

    @Test
    public void getById() throws Exception {
        long id = 1000;
        Seckill seckill = seckillService.getById(id);
        logger.info("seckill={}",seckill);
    }

    @Test
    public void exportSeckillUrl() throws Exception {
        int id = 1000;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        logger.info("expopse={}",exposer);
        //Exposer{
        // exposed=true,
        // md5='10f8732c102bfd4ed59ee6a7ffdc33ec',
        // seckillId=1000,
        // now=0, start=0, end=0}
    }

    @Test
    public void executeSeckill() throws Exception {
        long id = 1000;
        String phone = "15239583729";
        String md5 = "10f8732c102bfd4ed59ee6a7ffdc33ec";
        try {
            SeckillExecutetion seckillExecutetion = seckillService.executeSeckill(id,phone,md5);
            logger.info("result={}",seckillExecutetion);
        } catch (RepeatKillException e) {
            logger.error(e.getMessage());
        } catch (SeckillCloseException e) {
            logger.error(e.getMessage());
        }
    }

    @Test
    public void testSeckillLogic() throws Exception {
        int id = 1000;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if (exposer.getExposed()) {
            logger.info("expopse={}",exposer);
            String phone = "15239583729";
            String md5 = exposer.getMd5();
            try {
                SeckillExecutetion seckillExecutetion = seckillService.executeSeckill(id,phone,md5);
                logger.info("seckillExecutetion={}",seckillExecutetion);
            } catch (RepeatKillException e) {
                logger.error(e.getMessage());
            } catch (SeckillCloseException e) {
                logger.error(e.getMessage());
            }
        } else {
            logger.warn("expopse={}",exposer);
        }
    }

}