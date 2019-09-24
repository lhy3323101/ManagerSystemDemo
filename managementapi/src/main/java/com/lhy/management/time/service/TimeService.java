package com.lhy.management.time.service;

import com.github.pagehelper.PageInfo;
import com.lhy.management.common.model.BaseListVo;
import com.lhy.management.time.model.TimeInfoModel;

import java.util.List;

/**
 * Description:学生页面模块的service
 *
 * @author Angel
 * @since 2019/9/7 16:14
 */
public interface TimeService {


    /**
     * 新建时间信息
     * @param param 时间信息
     * @return 数据库主键id
     */
    Long saveNewTime(TimeInfoModel param);

    /**
     * 获取符合条件的时间列表
     * @param param 筛选条件
     * @return 符合条件的列表
     */
    PageInfo<TimeInfoModel> getTimeList(TimeInfoModel param);

    /**
     * 修改时间信息
     * @param param 更新的信息
     */
    void updateTime(TimeInfoModel param);

    /**
     * 获取星期列表
     * @return 返回星期列表
     */
    List<BaseListVo> getWeekList();
}
