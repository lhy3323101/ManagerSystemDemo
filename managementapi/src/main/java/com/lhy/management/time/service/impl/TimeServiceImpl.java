package com.lhy.management.time.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lhy.management.common.enums.WeekEnum;
import com.lhy.management.common.exception.TimeTransferException;
import com.lhy.management.common.model.BaseListVo;
import com.lhy.management.common.utils.DateTranslateUtil;
import com.lhy.management.time.mapper.TimeMapper;
import com.lhy.management.time.model.TimeInfoModel;
import com.lhy.management.time.service.TimeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.lhy.management.common.CommonConstant.BAN;
import static com.lhy.management.common.CommonConstant.START;

/**
 * Description:时间页面实现逻辑类
 *
 * @author Angel
 * @since 2019/9/7 16:15
 */
@Service
@Slf4j
public class TimeServiceImpl implements TimeService {

    @Resource
    private TimeMapper timeMapper;

    /**
     * 新建学生信息
     * @param param 学生信息
     * @return 数据库主键id
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Long saveNewTime(TimeInfoModel param) {
        // 1.转换时间
        transferTime(param);
        return timeMapper.saveNewTime(param);
    }

    /**
     * 获取符合条件的学生列表
     * @param param 筛选条件
     * @return 符合条件的列表
     */
    @Override
    public PageInfo<TimeInfoModel> getTimeList(TimeInfoModel param) {
    try {
        PageHelper.startPage(param.getPageStart(),param.getPageSize());

        // 1.将时间转为相应的时间戳
        transferTime(param);
        // 2.从数据库获取符合筛选条件的数据
        List<TimeInfoModel> list = timeMapper.getTimeList(param);

        for (TimeInfoModel timeInfoModel : list) {
            // 3.1 状态位中文转换
            if (BAN.equals(timeInfoModel.getStatus()) ){
                timeInfoModel.setStatusStr("禁用");
            }else if (START.equals(timeInfoModel.getStatus())){
                timeInfoModel.setStatusStr("启用");
            }
            // 3.2时间拼接
            StringBuilder builder = new StringBuilder();
            builder.append(DateTranslateUtil.dateToString(param.getStartTime(),DateTranslateUtil.HH_MM_SS))
                    .append(" —— ").append(DateTranslateUtil.dateToString(param.getEndTime(),DateTranslateUtil.HH_MM_SS));
            timeInfoModel.setTimeStr(builder.toString());
        }
        // 4.返回分页数据
        return new PageInfo<>(list);
    }catch (TimeTransferException e){
        log.error("时间转换异常");
        throw new TimeTransferException(e.getMessage());
    }catch (Exception e){
        e.printStackTrace();
    }
        return null;
    }

    /**
     * 修改学员信息
     * @param param 更新的信息
     */
    @Override
    public void updateTime(TimeInfoModel param) {
        timeMapper.updateTime(param);
    }

    /**
     * 将时间字符串转为时间戳
     * @param param 转换对象
     */
    private void transferTime(TimeInfoModel param) throws TimeTransferException {
        if (param.getEndTimeStr() != null){
            param.setEndTime(DateTranslateUtil.stringToTime(param.getEndTimeStr(),DateTranslateUtil.HH_MM));
        }
        if (param.getStartTimeStr() != null){
            param.setStartTime(DateTranslateUtil.stringToTime(param.getStartTimeStr(),DateTranslateUtil.HH_MM));
        }
    }

    /**
     * 获取星期列表
     * @return 返回星期列表
     */
    @Override
    public List<BaseListVo> getWeekList() {
        List<BaseListVo> list = new ArrayList<>();
        for (WeekEnum weekEnum : WeekEnum.values()) {
            BaseListVo vo = new BaseListVo();
            vo.setLabel(weekEnum.getLabel());
            vo.setValue(weekEnum.getIndex());
            list.add(vo);
        }
        return list;
    }
}
