package com.lhy.management.course.mapper;

import com.lhy.management.course.model.CourseInfoModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Description:
 *
 * @author Angel
 * @since 2019/9/7 16:16
 */
@Mapper
public interface CourseMapper {

    /**
     * 获取符合条件的学生列表
     * @param param 筛选条件
     * @return 符合条件的列表
     */
    List<CourseInfoModel> getCourseList(CourseInfoModel param);

    /**
     * 新增课程信息
     * @param param 新增课程信息
     * @return 主键id
     */
    Long saveNewCourse(CourseInfoModel param);

    /**
     * 修改课程信息
     * @param param 更新的信息
     */
    void updateCourse(CourseInfoModel param);
}
