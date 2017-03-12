package lazyrest.plugin.quartz;

import lazyrest.plugin.redis.CacheClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Melon on 17/3/12.
 */

@Component
public class CacheQuartz {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "jedisClientImpl")
    private CacheClient cacheClient;

    /**
     * 每隔1分钟定时清理缓存
     */
    @Scheduled(cron = "0 0/1 * * * ? ")
    public void cacheClear() {
        LOG.info("@Scheduled-------flushDB()");
        cacheClient.flushDB();
    }

}
