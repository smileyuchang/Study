package io.renren.api.user.service;

import io.renren.api.user.entity.OrderInfo;

/**
 * @auth kolt.yu
 * @时间： 2023/2/13 2:31 PM
 */
public interface OrderStrategyService  {

    //预下单
    String  preCreateOrder(OrderInfo orderInfo);

}