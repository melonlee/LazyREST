package lazyrest.plugin.memcached;

import lazyrest.plugin.CacheClient;
import net.rubyeye.xmemcached.MemcachedClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Melon on 17/2/16.
 */
@Service
public class MemcachedUtil implements CacheClient {

    @Autowired
    private MemcachedClient client;

    public String get(String key) {
        return null;
    }

    public byte[] get(byte[] key) {
        return new byte[0];
    }

    public String set(String key, String value) {
        return null;
    }

    public String set(String key, String value, int expire) {
        return null;
    }

    public String set(byte[] key, byte[] value) {
        return null;
    }

    public String set(byte[] key, byte[] value, int expire) {
        return null;
    }

    public String hget(String hkey, String key) {
        return null;
    }

    public long hset(String hkey, String key, String value) {
        return 0;
    }

    public long incr(String key) {
        return 0;
    }

    public long expire(String key, int second) {
        return 0;
    }

    public long ttl(String key) {
        return 0;
    }

    public long del(String key) {
        return 0;
    }

    public long del(byte[] key) {
        return 0;
    }

    public long hdel(String hkey, String key) {
        return 0;
    }

    public Set<byte[]> keys(String pattern) {
        return null;
    }

    public void flushDB() {

    }

    public Long dbSize() {
        return null;
    }
}
