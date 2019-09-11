package com.lhy.management.student.service;

import com.lhy.management.student.model.StudentInfoModel;

/**
 * Description:学生页面模块的service
 *
 * @author Angel
 * @since 2019/9/7 16:14
 */
public interface StudentService {


    /**
     * 新建学生信息
     * @param param 学生信息
     * @return 数据库主键id
     */
    Long saveNewStudent(StudentInfoModel param);
}
