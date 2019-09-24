package com.lhy.management.time.model;

import com.lhy.management.common.model.PageModel;
import lombok.Data;

import java.io.Serializable;
import java.sql.Time;

/**
 * Description:时间信息model
 *
 * @author Angel
 * @since 2019/9/7 16:11
 */

@Data
public class TimeInfoModel extends PageModel implements Serializable {

    private static final long serialVersionUID = 5790378395823635687L;
    /**
     * 主键id
     */
    private Long id;

    /**
     * 星期几
     */
    private Integer weekName;

    /**
     * 开始时间 —— 前端接收
     */
    private String startTimeStr;

    /**
     * 结束时间 —— 前端接收
     */
    private String endTimeStr;

    /**
     * 开始时间 —— 存储使用
     */
    private Time startTime;

    /**
     * 结束时间 —— 存储使用
     */
    private Time endTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态 0：禁用 1：启用
     */
    private Integer status;

    /**
     * 时间拼接(开始时间 - 结束时间)
     */
    private String timeStr;

    /**
     * 状态中文
     */
    private String statusStr;


}
