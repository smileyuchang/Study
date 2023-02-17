package io.renren.api.user.controller;

import com.google.common.base.Strings;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.UUID;

/**
 * @auth kolt.yu
 * @时间： 2023/2/17 3:40 PM
 */
@RestController
@RequestMapping("/api")
public class RedisStock {
    @Resource
    private RedisTemplate stringRedisTemplate;

    /**
     * 第二种实现，使用synchronized加锁
     * 可以只启动一个进程测试
     */
    @RequestMapping("/deduct_stock2")
    public void deductStock2(){
        synchronized (this){
            String stock = (String) stringRedisTemplate.opsForValue().get("stock");
            int stockNum = Integer.parseInt(Strings.isNullOrEmpty(stock) ? "0":stock);
            if(stockNum > 0){
                //设置库存减1
                int realStock = stockNum - 1;
                stringRedisTemplate.opsForValue().set("stock",realStock + "");
                System.out.println("设置库存" + realStock);
            }else{
                System.out.println("库存不足");
            }
        }

    }

    /**
     * 使用redis中的setIfAbsent(setnx命令)实现分布式锁
     */
    @RequestMapping("/deduct_stock3")
    public void deductStock3(){
        //在获取到锁的时候，给锁分配一个id
        String opId = UUID.randomUUID().toString();
        Boolean stockLock = stringRedisTemplate
                .opsForValue().setIfAbsent("stockLock", opId, Duration.ofSeconds(30));

        if(stockLock){

            try{
                String stock = (String) stringRedisTemplate.opsForValue().get("stock");
                int stockNum = Integer.parseInt(Strings.isNullOrEmpty(stock) ? "0":stock );
                if(stockNum > 0){
                    //设置库存减1
                    int realStock = stockNum - 1;
                    stringRedisTemplate.opsForValue().set("stock",realStock + "");
                    System.out.println("设置库存" + realStock);
                }else{
                    System.out.println("库存不足");
                }

            }catch(Exception e){
                e.printStackTrace();
            }finally {
                if(opId.equals(stringRedisTemplate
                        .opsForValue().get("stockLock"))){
                    stringRedisTemplate.delete("stockLock");
                }
            }

        }

    }





}
