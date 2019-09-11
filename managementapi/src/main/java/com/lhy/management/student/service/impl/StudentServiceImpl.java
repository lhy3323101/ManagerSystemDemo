package com.lhy.management.student.service.impl;

import com.lhy.management.student.mapper.StudentMapper;
import com.lhy.management.student.model.StudentInfoModel;
import com.lhy.management.student.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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
}
