package io.renren.api.user.controller;

import io.renren.api.user.entity.OrderInfo;
import io.renren.api.user.service.OrderStrategyService;
import io.renren.api.user.strategy.context.OrderStrategyContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 策略模式的使用
 * @Author : JCccc
 * @CreateTime : 2020/5/11
 * @Description :
 **/
@RestController
@RequestMapping("/api")
public class OrderTestController {

    @Autowired
    private OrderStrategyContext orderStrategyContext;

    /**
     * platFormType:Rebate...
     * @param orderInfo
     * @return
     */
    @PostMapping("/testStrategy")
    public String testStrategy(@RequestBody OrderInfo orderInfo){
        OrderStrategyService orderServiceImpl = orderStrategyContext.getResource(orderInfo);
        String resultTest = orderServiceImpl.preCreateOrder(orderInfo);
        return resultTest;

    }



}