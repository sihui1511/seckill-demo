package com.sihui.seckill.web;

import com.sihui.seckill.dto.Exposer;
import com.sihui.seckill.dto.SeckillExecutetion;
import com.sihui.seckill.dto.SeckillResult;
import com.sihui.seckill.entity.Seckill;
import com.sihui.seckill.enums.SeckillStateEnum;
import com.sihui.seckill.exception.RepeatKillException;
import com.sihui.seckill.exception.SeckillCloseException;
import com.sihui.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author sihui.sha
 * @date 2018/11/8
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model){
        logger.info("this is list");
        List<Seckill> seckillList = seckillService.getSeckillList();
        model.addAttribute("list",seckillList);
        return "list";
    }

    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model){
        if (seckillId == null){
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.getById(seckillId);
        if (seckill == null){
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill",seckill);
        return "detail";
    }

    @RequestMapping(value = "/{seckillId}/exposer",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId){
        SeckillResult<Exposer> result;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<Exposer>(true,exposer);
        } catch (Exception e){
            logger.error(e.getMessage(), e);
            result = new SeckillResult<Exposer>(false, e.getMessage());
        }

        return result;
    }

    @RequestMapping(value = "/{seckillId}/{md5}/execution",
        method = RequestMethod.POST,
        produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExecutetion> execute(@PathVariable("seckillId") Long seckillId,
                                                     @PathVariable("md5") String md5,
                                                     @CookieValue(value = "killPhone", required = false) String phone){
        if (phone == null){
            return new SeckillResult<SeckillExecutetion>(false, "未注册");
        }

        SeckillResult<SeckillExecutetion> result;
        SeckillExecutetion executetion;
        try {
            executetion = seckillService.executeSeckill(seckillId,phone,md5);
            result = new SeckillResult<SeckillExecutetion>(true, executetion);
        } catch (RepeatKillException e){
            executetion = new SeckillExecutetion(seckillId, SeckillStateEnum.REPEAT_KILL);
            result = new SeckillResult<SeckillExecutetion>(true, executetion);
        }catch (SeckillCloseException e){
            executetion = new SeckillExecutetion(seckillId, SeckillStateEnum.END);
            result = new SeckillResult<SeckillExecutetion>(true, executetion);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            executetion = new SeckillExecutetion(seckillId,SeckillStateEnum.INNER_ERROR);
            result = new SeckillResult<SeckillExecutetion>(true, executetion);
        }

        return result;
    }

    @RequestMapping(value = "/time/now", method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Long> time(){
        Date now = new Date();
        return new SeckillResult<Long>(true, now.getTime());
    }
}
