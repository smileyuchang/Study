package io.renren.api.user.strategy.order;

import io.renren.api.user.entity.OrderInfo;
import io.renren.api.user.service.OrderStrategyService;
import org.springframework.stereotype.Component;

/**
 * @Author : JCccc
 * @CreateTime : 2020/5/11
 * @Description :特殊订单（回扣）
 **/
@Component("Rebate")
public class OrderRebate implements OrderStrategyService {
    @Override
    public String preCreateOrder(OrderInfo orderInfo) {

        System.out.println("***处理国内特殊回扣预下单的相关业务***");
        return orderInfo.getPlatFormType()+"-特殊回扣预下单";


    }
}