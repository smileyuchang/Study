

package io.renren.api.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Strings;
import io.renren.api.user.entity.UserEntity;
import io.renren.api.user.form.LoginForm;
import io.renren.api.user.service.TokenService;
import io.renren.api.user.service.UserService;
import io.renren.common.annotation.Login;
import io.renren.common.annotation.LoginUser;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * 登录接口
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/api")
@Api(tags="登录接口")
public class ApiLoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @RequestMapping("login")
    @ApiOperation("登录")
    public R login(@RequestBody LoginForm form){
        //表单校验
        ValidatorUtils.validateEntity(form);
        //用户登录
        Map<String, Object> map = userService.login(form);

        return R.ok(map);
    }

    @Login
    @PostMapping("logout")
    @ApiOperation("退出")
    public R logout(@ApiIgnore @RequestAttribute("userId") long userId){
        tokenService.expireToken(userId);
        return R.ok();
    }


    /**
     * 用户个人信息
     * @param user
     * @return
     */
    @Login
    @RequestMapping("info")
    public R info(@LoginUser UserEntity user){
        return R.ok().put("result",userService.getBaseMapper().selectOne(new QueryWrapper<UserEntity>().eq("user_id", user.getUserId())));
    }


    /**
     * 修改个人信息
     * @param 'user'
     * @return
     */
    @Login
    @RequestMapping("updateUserInfo")
    public R updateUserInfo(Long userId,String mobile,String nikeName,String password){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userId);
        if(!Strings.isNullOrEmpty(mobile)){
            userEntity.setMobile(mobile);
        }
        if(!Strings.isNullOrEmpty(password)){
            userEntity.setPassword(DigestUtils.sha256Hex(password));
        }
        int a = userService.getBaseMapper().updateById(userEntity);
        if(a == 1){
            return R.ok();
        } else {
            return R.error("修改失败");
        }

    }

}
