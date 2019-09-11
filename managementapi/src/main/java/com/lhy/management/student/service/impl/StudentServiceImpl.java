package com.lhy.management.student.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lhy.management.student.mapper.StudentMapper;
import com.lhy.management.student.model.StudentInfoModel;
import com.lhy.management.student.service.StudentService;
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
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    /**
     * 新建学生信息
     * @param param 学生信息
     * @return 数据库主键id
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Long saveNewStudent(StudentInfoModel param) {
        return studentMapper.saveNewStudent(param);
    }

    /**
     * 获取符合条件的学生列表
     * @param param 筛选条件
     * @return 符合条件的列表
     */
    @Override
    public PageInfo<StudentInfoModel> getStudentList(StudentInfoModel param) {
        PageHelper.startPage(param.getPageStart(),param.getPageSize());
        // 1.从数据库获取符合筛选条件的数据
        List<StudentInfoModel> list = studentMapper.getStudentList(param);
        // 2.返回分页数据
        return new PageInfo<>(list);
    }
}
