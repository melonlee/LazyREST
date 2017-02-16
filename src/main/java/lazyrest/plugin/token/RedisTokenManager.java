package lazyrest.plugin.token;

import lazyrest.plugin.redis.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * Created by Melon on 17/2/16.
 */
@Component
public class RedisTokenManager implements TokenManager {

    @Autowired
    private JedisClient jedisClient;

    /**
     * 存储15天
     */
    public static int timeOut = 60 * 60 * 24 * 15;

    /**
     * 创建Token
     */
    public String createToken(String uid) {
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        jedisClient.set(token, uid, timeOut);
        return token;
    }

    /**
     * 验证Token
     */
    public boolean checkToken(String token) {

        return !StringUtils.isEmpty(jedisClient.get(token));
    }
}
