package lazyrest.test;

import lazyrest.entity.User;
import lazyrest.service.IUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Melon on 17/2/20.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/spring-*.xml"})
public class BaseTest {

    protected Logger logger = LoggerFactory.getLogger(BaseTest.class);


    @Resource
    protected IUserService userService;


    protected User user;

    @Before
    public void setup() {

        user = new User();
        user.setUsername("melon");
        user.setPassword("123456");

    }

    @Test
    public void test() {

        logger.info(String.format("登录测试结果: [%s]", userService.login(user)));

    }
}
