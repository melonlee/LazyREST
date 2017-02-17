package lazyrest.plugin.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * Created by Melon on 17/2/16.
 */
@Service
public class JedisClientImpl implements CacheClient {

    @Autowired
    private JedisPool jedisPool;

    public String get(String key) {
        String value = null;
        Jedis jedis = jedisPool.getResource();
        try {
            value = jedis.get(key);
        } catch (Exception e) {
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return value;
    }

    public byte[] get(byte[] key) {
        byte[] value = null;
        Jedis jedis = jedisPool.getResource();
        try {
            value = jedis.get(key);
        } catch (Exception e) {
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return value;
    }

    public String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        try {
            value = jedis.set(key, value);
        } catch (Exception e) {
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return value;
    }

    public String set(String key, String value, int expire) {
        Jedis jedis = jedisPool.getResource();
        try {
            value = jedis.set(key, value);
            if (expire != 0) {
                jedis.expire(key, expire);
            }
        } catch (Exception e) {
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return value;
    }

    public String set(byte[] key, byte[] value) {
        Jedis jedis = jedisPool.getResource();
        String v = null;
        try {
            v = jedis.set(key, value);
        } catch (Exception e) {
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return v;
    }

    public String set(byte[] key, byte[] value, int expire) {
        Jedis jedis = jedisPool.getResource();
        String v = null;
        try {
            v = jedis.set(key, value);
            if (expire != 0) {
                jedis.expire(key, expire);
            }
        } catch (Exception e) {
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return v;
    }

    public String hget(String hkey, String key) {
        String value = null;
        Jedis jedis = jedisPool.getResource();
        try {
            value = jedis.hget(hkey, key);
        } catch (Exception e) {
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return value;
    }

    public long hset(String hkey, String key, String value) {
        Jedis jedis = jedisPool.getResource();
        Long result = null;
        try {
            result = jedis.hset(hkey, key, value);
        } catch (Exception e) {
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return result;
    }

    public long incr(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = null;
        try {
            result = jedis.incr(key);
        } catch (Exception e) {
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return result;
    }

    public long expire(String key, int second) {
        Jedis jedis = jedisPool.getResource();
        Long result = null;
        try {
            result = jedis.expire(key, second);
        } catch (Exception e) {
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return result;
    }

    public long ttl(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = null;
        try {
            result = jedis.ttl(key);
        } catch (Exception e) {
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return result;
    }

    public long del(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = null;
        try {
            result = jedis.del(key);
        } catch (Exception e) {
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return result;
    }

    public long del(byte[] key) {
        Jedis jedis = jedisPool.getResource();
        Long result = null;
        try {
            result = jedis.del(key);
        } catch (Exception e) {
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return result;
    }

    public long hdel(String hkey, String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = null;
        try {
            result = jedis.hdel(hkey, key);
        } catch (Exception e) {
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return result;
    }

    public Set<byte[]> keys(String pattern) {
        Set<byte[]> keys = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
        } catch (Exception e) {
            return null;
        }
        try {
            keys = jedis.keys(pattern.getBytes());
        } catch (Exception e) {
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return keys;
    }

    public void flushDB() {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.flushDB();
        } catch (Exception e) {
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
    }

    public Long dbSize() {
        Long dbSize = 0L;
        Jedis jedis = jedisPool.getResource();
        try {
            dbSize = jedis.dbSize();
        } catch (Exception e) {
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return dbSize;
    }
}
