package com.lhy.management.time.controller;

import com.github.pagehelper.PageInfo;
import com.lhy.management.common.model.BaseListVo;
import com.lhy.management.common.result.Result;
import com.lhy.management.time.model.TimeInfoModel;
import com.lhy.management.time.service.TimeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description: 学生页Controller
 *
 * @author Angel
 * @since 2019/9/7 16:04
 */
@RestController
@Slf4j
@RequestMapping("/time")
public class TimeController {

    @Autowired
    private TimeService timeService;

    @ResponseBody
    @RequestMapping("/saveNewTime")
    public Result saveNewTime(@RequestBody TimeInfoModel param){
        try {
            Long id = timeService.saveNewTime(param);
        }catch (Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
            return Result.err("添加异常");
        }
        return Result.suc("添加成功");
    }

    @ResponseBody
    @RequestMapping("/getTimeList")
    public Result getTimeList(@RequestBody TimeInfoModel param){
        try {
            PageInfo<TimeInfoModel> timeList = timeService.getTimeList(param);
            return Result.suc("获取数据成功",timeList);
        }catch (Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
            return Result.err("获取时间信息异常");
        }
    }

   /* @ResponseBody
    @RequestMapping("updateTime")
    public Result updateTime(@RequestBody TimeInfoModel param){
        try {
            timeService.updateStudent(param);
            return Result.suc();
        }catch (Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
            return Result.err("修改时间信息异常");
        }
    }*/

    @ResponseBody
    @RequestMapping("/getWeekList")
    public Result getWeekList(){
        try {
            List<BaseListVo> list = timeService.getWeekList();
            return Result.suc("获取星期列表成功",list);
        }catch (Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
            return Result.err("获取星期列表异常");
        }
    }
}
