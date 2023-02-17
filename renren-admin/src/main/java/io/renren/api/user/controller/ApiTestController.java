

package io.renren.api.user.controller;

import io.renren.api.user.entity.UserEntity;
import io.renren.common.annotation.Login;
import io.renren.common.annotation.LoginUser;
import io.renren.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试接口
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/api")
@Api(tags="测试接口")
public class ApiTestController {
   /* @Resource
    MyWebSocketService webSocketService;



    @RequestMapping("/sendMessage")
    @ResponseBody
    public Object sendMessage(String msg){
        webSocketService.sendMessage(msg);
        return null;
    }
*/



    @Login
    @GetMapping("userInfo")
    @ApiOperation(value="获取用户信息", response= UserEntity.class)
    public R userInfo(@ApiIgnore @LoginUser UserEntity user){
        return R.ok().put("user", user);
    }

    @Login
    @GetMapping("userId")
    @ApiOperation("获取用户ID")
    public R userInfo(@ApiIgnore @RequestAttribute("userId") Integer userId){
        return R.ok().put("userId", userId);
    }

    @GetMapping("notToken")
    @ApiOperation("忽略Token验证测试")
    public R notToken(){
        return R.ok().put("msg", "无需token也能访问。。。");
    }


    @ResponseBody
    @GetMapping("/getUsername")
    public Map<String, Object> getUsername(HttpSession session) {
        Map<String, Object> map = new HashMap<String, Object>();
        String username = (String) session.getAttribute("username");
        String userId = (String) session.getAttribute("userId");
        map.put("flag", true);
        map.put("username", username);
        map.put("userId", userId);
        return map;
    }

}
