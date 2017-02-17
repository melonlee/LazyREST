package lazyrest.web.controller;

import com.baomidou.mybatisplus.plugins.Page;
import lazyrest.common.util.Result;
import lazyrest.common.anno.Log;
import lazyrest.common.anno.Token;
import lazyrest.common.util.ValidateUtil;
import lazyrest.entity.User;
import lazyrest.plugin.security.TokenManager;
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
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@Valid User user, BindingResult result) {

        if (result.hasErrors()) {
            return new Result().failure("参数有误", ValidateUtil.toStringJson(result));
        }
        User sysUser = userService.login(user);
        if (sysUser == null) {
            //注册用户
            sysUser = userService.register(user);
        }
        String token = tokenManager.createToken(sysUser.getUsername());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user", sysUser);
        map.put("token", token);
        return new Result().success(map);

    }

    @Token
    @RequestMapping(value = "/list/{page}", method = RequestMethod.GET)
    public Result list(@PathVariable("page") int page) {
        Page<User> pageData = userService.selectPage(new Page<User>(page, 10));
        return new Result().success(pageData);
    }
}
