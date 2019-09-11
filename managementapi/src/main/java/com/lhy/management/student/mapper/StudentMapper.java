package com.lhy.management.student.mapper;

import com.lhy.management.student.model.StudentInfoModel;
import org.apache.ibatis.annotations.Mapper;

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
}
