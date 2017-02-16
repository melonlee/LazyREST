package lazyrest.controller;

import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import lazyrest.common.Result;
import lazyrest.common.anno.Log;
import lazyrest.common.anno.TokenSecurity;
import lazyrest.common.util.PasswordUtil;
import lazyrest.common.util.ValidateUtil;
import lazyrest.entity.User;
import lazyrest.plugin.token.TokenManager;
import lazyrest.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Melon on 17/2/15.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Resource(name = "redisTokenManager")
    private TokenManager tokenManager;

    @Log("用户登录")
    @ApiOperation(value = "用户登录", response = Result.class)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@Valid User user, BindingResult result) {

        if (result.hasErrors()) {
            return new Result().failure("error", ValidateUtil.toStringJson(result));
        }
        User sysUser = userService.login(user);
        if (sysUser != null) {
            String token = tokenManager.createToken(sysUser.getUsername());
            Map<String, Object> map = new HashMap<String, Object>();
            //返回用户对象
            map.put("user", sysUser);
            //返回给客户端Token,用于安全校验,需要客户端保存起来
            map.put("token", token);
            return new Result().success(map);
        }
        throw new RuntimeException("用户名或密码错误");
    }

    @RequestMapping(value = "/list/{page}", method = RequestMethod.GET)
    public Result list(@PathVariable("page") int page) {
        Page<User> pageData = userService.selectPage(new Page<User>(page, 10));
        return new Result().success(pageData);
    }
}
