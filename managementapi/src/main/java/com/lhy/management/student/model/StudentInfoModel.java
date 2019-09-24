package com.lhy.management.student.model;

import com.lhy.management.common.model.PageModel;
import lombok.Data;

import java.io.Serializable;

/**
 * Description: 学生信息Model
 *
 * @author Angel
 * @since 2019/9/7 16:11
 */

@Data
public class StudentInfoModel extends PageModel implements Serializable {


    private static final long serialVersionUID = -368892806990975112L;
    /**
     * 主键id
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 联系方式
     */
    private String mobile;

    /**
     * 家长姓名
     */
    private String parentName;

    /**
     * 家长联系方式
     */
    private String parentMobile;

    /**
     * 备注
     */
    private String remark;

}
