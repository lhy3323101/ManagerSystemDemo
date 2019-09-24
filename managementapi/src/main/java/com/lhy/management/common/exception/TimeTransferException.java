package com.lhy.management.common.exception;

/**
 * Description: 时间转换报错异常
 *
 * @author Angel
 * @since 2019/9/17 13:51
 */
public class TimeTransferException extends RuntimeException{

    private static final long serialVersionUID = 1886052197353745949L;

    public TimeTransferException(String msg){
        super(msg);
    }

    public TimeTransferException(){
    }
}
