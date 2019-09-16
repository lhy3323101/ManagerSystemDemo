package com.lhy.management.course.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lhy.management.course.mapper.CourseMapper;
import com.lhy.management.course.model.CourseInfoModel;
import com.lhy.management.course.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description:
 *
 * @author Angel
 * @since 2019/9/7 16:15
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Resource
    private CourseMapper courseMapper;

    /**
     * 禁用
     */
    private static final Integer BAN = 0;

    /**
     * 启用
     */
    private static final Integer START = 1;

    /**
     * 获取符合条件的学生列表
     * @param param 筛选条件
     * @return 符合条件的列表
     */
    @Override
    public PageInfo<CourseInfoModel> getCourseList(CourseInfoModel param) {
        PageHelper.startPage(param.getPageStart(),param.getPageSize());
        // 1.从数据库获取符合筛选条件的数据
        List<CourseInfoModel> list = courseMapper.getCourseList(param);
        for (CourseInfoModel courseInfoModel : list) {
            if (BAN.equals(courseInfoModel.getStatus()) ){
                courseInfoModel.setStatusStr("禁用");
            }else if (START.equals(courseInfoModel.getStatus())){
                courseInfoModel.setStatusStr("启用");
            }
        }
        // 2.返回分页数据
        return new PageInfo<>(list);
    }

    /**
     * 新增课程信息
     * @param param 新增课程信息
     * @return 主键id
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Long saveNewCourse(CourseInfoModel param) {
        return courseMapper.saveNewCourse(param);
    }


    /**
     * 修改课程信息
     * @param param 更新的信息
     */
    @Override
    public void updateCourse(CourseInfoModel param) {
        courseMapper.updateCourse(param);
    }
}
