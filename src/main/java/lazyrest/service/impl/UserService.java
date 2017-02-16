package lazyrest.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lazyrest.common.util.PasswordUtil;
import lazyrest.dao.UserMapper;
import lazyrest.entity.User;
import lazyrest.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Melon on 17/2/15.
 */

@Service
public class UserService extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Resource
    private PasswordUtil passwordUtil;

    public User login(User user) {


        passwordUtil.encryptPassword(user);

        return this.selectOne(new EntityWrapper<User>().eq("userName", user.getUsername()).eq("password", user.getPassword()));
    }

    public List<User> list(int pageF) {
        return null;
    }
}
