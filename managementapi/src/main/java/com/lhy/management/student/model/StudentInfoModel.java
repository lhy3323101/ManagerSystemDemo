package com.lhy.management.student.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Description:
 *
 * @author Angel
 * @since 2019/9/7 16:11
 */

@Data
public class StudentInfoModel implements Serializable {


    private static final long serialVersionUID = -3299680766364267888L;

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
