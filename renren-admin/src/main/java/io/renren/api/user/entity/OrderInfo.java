package io.renren.api.user.entity;

import lombok.Data;

/**
 import lombok.Data;

 /**
 * @Author : JCccc
 * @CreateTime : 2020/5/11
 * @Description :
 **/
@Data
public class OrderInfo {

    private String orderId; //订单id
    private String platFormType; //（平台）订单类型
    private Double amount;  //金额
    private String createTime; //创建时间

    //....省略若干业务字段
}