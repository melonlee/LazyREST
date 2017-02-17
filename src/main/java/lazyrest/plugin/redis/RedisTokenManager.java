package lazyrest.plugin.redis;

import lazyrest.plugin.CacheClient;
import lazyrest.plugin.security.TokenManager;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * Created by Melon on 17/2/16.
 */
@Component
public class RedisTokenManager implements TokenManager {

    @Resource(name = "jedisClient")
    private CacheClient cacheClient;

    /**
     * 存储15天
     */
    public static int timeOut = 60 * 60 * 24 * 15;

    /**
     * 创建Token
     */
    public String createToken(String uid) {
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        cacheClient.set(token, uid, timeOut);
        return token;
    }


    /**
     * 验证Token
     */
    public boolean checkToken(String token) {

        return !StringUtils.isEmpty(cacheClient.get(token));
    }
}
