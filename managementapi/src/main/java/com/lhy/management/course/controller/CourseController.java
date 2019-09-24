package com.lhy.management.course.controller;

import com.github.pagehelper.PageInfo;
import com.lhy.management.common.result.Result;
import com.lhy.management.course.model.CourseInfoModel;
import com.lhy.management.course.model.TestTimeModel;
import com.lhy.management.course.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Description: 课程页Controller
 *
 * @author Angel
 * @since 2019/9/7 16:04
 */
@RestController
@Slf4j
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @ResponseBody
    @RequestMapping("/saveNewCourse")
    public Result saveNewCourse(@RequestBody CourseInfoModel param){
        try {
            Long id = courseService.saveNewCourse(param);
        }catch (Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
            return Result.err("添加异常");
        }
        return Result.suc("添加成功");
    }

   @ResponseBody
    @RequestMapping("/getCourseList")
    public Result getCourseList(@RequestBody CourseInfoModel param){
        try {
            PageInfo<CourseInfoModel> courseList = courseService.getCourseList(param);
            return Result.suc("获取数据成功",courseList);
        }catch (Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
            return Result.err("获取学生信息异常");
        }
    }

    @ResponseBody
    @RequestMapping("/updateCourse")
    public Result updateCourse(@RequestBody CourseInfoModel param){
        try {
            if (param.getId() == null){
                log.info("前端为传入id，无法进行更改");
                return Result.err("更改失败");
            }
            courseService.updateCourse(param);
            return Result.suc();
        }catch (Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
            return Result.err("修改学生信息异常");
        }
    }

    @ResponseBody
    @RequestMapping("/testTime")
    public Result testTime(@RequestBody TestTimeModel param){
        try {
            if (param != null && param.getTestTime() != null){
                DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String time = sdf.format(param.getTestTime());
                System.out.println(time);
            }
            return Result.suc();
        }catch (Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
            return Result.err("接收DatePicker类异常");
        }

    }
}
