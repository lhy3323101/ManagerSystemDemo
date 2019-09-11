package com.lhy.management.student.controller;

import com.lhy.management.common.result.Result;
import com.lhy.management.student.model.StudentInfoModel;
import com.lhy.management.student.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author Angel
 * @since 2019/9/7 16:04
 */
@RestController
@Slf4j
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @ResponseBody
    @RequestMapping("/saveNewStudent")
    public Result saveNewStudent(@RequestBody StudentInfoModel param){
        try {
            Long id = studentService.saveNewStudent(param);
        }catch (Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
            return Result.err("添加异常");
        }
        return Result.suc("添加成功");
    }
}
