package com.lhy.management.common.enums;

/**
 * Description: 星期枚举
 * @author Angel
 * @since 2019/09/17
 */
public enum WeekEnum {
    /**
     * 兴趣枚举
     */
    MONDAY(1,"星期一"),
    TUESDAY(2,"星期二"),
    WEDNESDAY(3,"星期三"),
    THURSDAY(4,"星期四"),
    FRIDAY(5,"星期五"),
    SATURDAY(6,"星期六"),
    SUNDAY(7,"星期日");

    private String label;

    private Integer index;

    WeekEnum(Integer index, String label){
        this.index = index;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
