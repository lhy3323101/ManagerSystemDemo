package com.lhy.management.course.service;

import com.github.pagehelper.PageInfo;
import com.lhy.management.course.model.CourseInfoModel;

/**
 * Description:学生页面模块的service
 *
 * @author Angel
 * @since 2019/9/7 16:14
 */
public interface CourseService {

    /**
     * 获取符合条件的学生列表
     * @param param 筛选条件
     * @return 符合条件的列表
     */
    PageInfo<CourseInfoModel> getCourseList(CourseInfoModel param);

    /**
     * 新增课程信息
     * @param param 新增课程信息
     * @return 主键id
     */
    Long saveNewCourse(CourseInfoModel param);

    /**
     * 修改课程信息
     * @param param 新的课程信息
     */
    void updateCourse(CourseInfoModel param);

}
