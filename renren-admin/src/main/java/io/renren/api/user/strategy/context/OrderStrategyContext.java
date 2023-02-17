package io.renren.api.user.strategy.context;

import io.renren.api.user.entity.OrderInfo;
import io.renren.api.user.service.OrderStrategyService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author : JCccc
 * @CreateTime : 2020/5/11
 * @Description : 利用Spring的发现机制,将实现了OrderStrategyService的类都put到orderStrategyMap里面。
 *                 后面只需要根据platformId对应好 各个实现类的注解 如： @Component("Domestic") 就可以取出不同的业务实现类
 **/

@Service
public class OrderStrategyContext {

    private final Map<String, OrderStrategyService> orderStrategyMap = new ConcurrentHashMap<>();

    public OrderStrategyContext(Map<String, OrderStrategyService> strategyMap) {
        this.orderStrategyMap.clear();
        strategyMap.forEach((k, v)-> this.orderStrategyMap.put(k, v));
    }

    public OrderStrategyService getResource(OrderInfo orderInfo){
        return orderStrategyMap.get(orderInfo.getPlatFormType());
    }
}