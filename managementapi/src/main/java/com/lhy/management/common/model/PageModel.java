package com.lhy.management.common.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Description: 分页基本类
 *
 * @author Angel
 * @since 2019/9/11 16:34
 */
@Data
public class PageModel implements Serializable {

    private static final long serialVersionUID = -2768595253999557134L;

    /**
     * 当前页码
     */
    private Integer pageStart;

    private Integer pageEnd;

    /**
     * 单页面条数
     */
    private Integer pageSize;
}
