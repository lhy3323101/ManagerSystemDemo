package com.lhy.management.student.mapper;

import com.lhy.management.student.model.StudentInfoModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Description:
 *
 * @author Angel
 * @since 2019/9/7 16:16
 */
@Mapper
public interface StudentMapper {

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
    List<StudentInfoModel> getStudentList(StudentInfoModel param);

    /**
     * 修改学员信息
     * @param param 更新的信息
     */
    void updateStudent(StudentInfoModel param);
}
