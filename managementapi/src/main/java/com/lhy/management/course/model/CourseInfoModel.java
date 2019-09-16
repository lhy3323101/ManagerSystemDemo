package com.lhy.management.course.model;

import com.lhy.management.common.model.PageModel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Description:
 *
 * @author Angel
 * @since 2019/9/7 16:11
 */

@Data
public class CourseInfoModel extends PageModel implements Serializable {


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
     * 价格
     */
    private BigDecimal price;

    /**
     * 课时
     * */
    private Integer stuTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态位  0:禁用，1：启用
     */
    private Integer status;

    /**
     * 状态位中文
     */
    private String statusStr;

}
