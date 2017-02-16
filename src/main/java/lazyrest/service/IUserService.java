package lazyrest.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import lazyrest.entity.User;

import java.util.List;
import java.util.Map;

/**
 * Created by Melon on 17/2/15.
 */
public interface IUserService extends IService<User> {


    User login(User user);

    List<User> list(int pageF);

}
