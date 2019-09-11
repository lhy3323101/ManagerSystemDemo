package com.lhy.management.student.service;

import com.github.pagehelper.PageInfo;
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

    /**
     * 获取符合条件的学生列表
     * @param param 筛选条件
     * @return 符合条件的列表
     */
    PageInfo<StudentInfoModel> getStudentList(StudentInfoModel param);
}
