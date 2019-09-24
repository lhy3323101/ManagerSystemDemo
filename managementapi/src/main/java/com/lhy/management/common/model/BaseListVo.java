package com.lhy.management.common.model;

import lombok.Data;

/**
 * Description: 通用列表返回对象
 *
 * @author Angel
 * @since 2019/9/17 15:24
 */
@Data
public class BaseListVo {

    /**
     * 显示名
     */
    private String label;

    /**
     * 对应值
     */
    private Integer value;
}
