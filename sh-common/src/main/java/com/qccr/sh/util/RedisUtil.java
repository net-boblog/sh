package com.qccr.sh.util;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {

    private static final Logger log = LoggerFactory.getLogger(RedisUtil.class);

    private static final JedisPool jedisPool;// 非切片连接池

    // private static final ShardedJedisPool jedisPool;//切片连接池

    private static String KEY_PREFIX = "SH_";

    static {

        String prefix = BT.getConfig("redis_prefix");
        if (!BT.isEmpty(prefix)) {
            KEY_PREFIX = prefix;
        }
        // 池基本配置
        JedisPoolConfig jpconfig = new JedisPoolConfig();

        // 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
        // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
        // jpconfig.setMaxActive(Integer.parseInt(Config.getValue("redis_maxActive",
        // "-1"), 10));

        // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
        jpconfig.setMaxIdle(Integer.parseInt(BT.getConfig("redis_maxIdle"), 10));

        // 初始化连接数 默认0
        // jpconfig.setMinIdle(5);

        // getResource()最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
        // jpconfig.setMaxWait(Long.parseLong(Config.getValue("redis_maxWait",
        // "1000"), 10));

        // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
        jpconfig.setTestOnBorrow(false);

        String pwd = BT.getConfig("redis_password");
        if (BT.isEmpty(pwd)) {
            jedisPool = new JedisPool(jpconfig, BT.getConfig("redis_ip"), Integer.parseInt(BT.getConfig("redis_port"), 10), Integer.parseInt(
                    BT.getConfig("redis_timeout"), 10));
        } else {
            jedisPool = new JedisPool(jpconfig, BT.getConfig("redis_ip"), Integer.parseInt(BT.getConfig("redis_port"), 10), Integer.parseInt(
                    BT.getConfig("redis_timeout"), 10), pwd);
        }

        /**
         * 切片连接池 List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
         * shards.add(new JedisShardInfo(Config.getValue("redis_ip",
         * "21.254.43.68"), Integer.parseInt(Config.getValue("redis_port",
         * "31889"), 10), Integer.parseInt(Config.getValue("redis_timeout",
         * "1500"), 10));
         *
         * jedisPool = new ShardedJedisPool(jpconfig, shards);
         */
    }

    /**
     * 设置Bean
     *
     * @param key
     * @param value
     */
    public static final void setBean(final String key, final Object value) {
        set(key, JSON.toJSONString(value));
    }

    /**
     * 设置Bean
     *
     * @param key
     * @param value
     * @param seconds
     */
    public static final void setBean(final String key, final Object value, final int seconds) {
        set(key, JSON.toJSONString(value), seconds);
    }

    /**
     * 获取Bean
     *
     * @param key
     * @param clazz
     * @return
     */
    public static <T> T getBean(final String key, final Class<T> clazz) {
        String got = get(key);
        if (BT.isEmpty(got))
            return null;
        return JSON.parseObject(got, clazz);
    }

    /**
     * 设置字符串
     *
     * @param key
     * @param value
     * @return
     */
    public static boolean set(final String key, final String value) {
        Jedis jedis = null;
        boolean isOK = true;
        try {
            jedis = jedisPool.getResource();
            jedis.set(KEY_PREFIX + key, value);
            return true;
        } catch (Exception e) {
            isOK = false;
            log.error("Redis Set Value Error!", e);
            throw new RuntimeException(e);
        } finally {
            giveback(jedis, isOK);
        }
    }

    public static boolean set(final String key, final String value, final int seconds) {
        Jedis jedis = null;
        boolean isOK = true;
        try {
            jedis = jedisPool.getResource();
            jedis.setex(KEY_PREFIX + key, seconds, value);
            return true;
        } catch (Exception e) {
            isOK = false;
            log.error("Redis Set Value Error!", e);
            throw new RuntimeException(e);
        } finally {
            giveback(jedis, isOK);
        }
    }

    /**
     * 获取字符串
     *
     * @param key
     * @return
     */
    public static String get(final String key) {
        Jedis jedis = null;
        boolean isOK = true;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(KEY_PREFIX + key);
        } catch (Exception e) {
            isOK = false;
            log.error("Redis Get Value Error!", e);
            throw new RuntimeException(e);
        } finally {
            giveback(jedis, isOK);
        }

    }

    /**
     * @param key 缓存的键值 value 缓存的value值 seconds 缓存的有效时间，0--长期有效
     * @return boolean
     * @Description: 缓存对象信息
     * @author: 潘成忠
     * @date: 2015年6月14日 上午11:26:27
     */
    public static boolean setObject(final String key, final Object value, final int seconds) {
        Jedis jedis = null;
        boolean isOK = true;
        try {
            jedis = jedisPool.getResource();
            jedis.setex((KEY_PREFIX + key).getBytes(), seconds, ObjectUtils.serialize(value));
            return true;
        } catch (Exception e) {
            isOK = false;
            log.error("Redis Set Value Error!", e);
            throw new RuntimeException(e);
        } finally {
            giveback(jedis, isOK);
        }
    }

    public static Object getObject(final String key) {
        Jedis jedis = null;
        boolean isOK = true;
        try {
            jedis = jedisPool.getResource();
            byte[] data = jedis.get((KEY_PREFIX + key).getBytes());
            if (data == null || data.length == 0)
                return null;
            return ObjectUtils.unserialize(data);
        } catch (Exception e) {
            isOK = false;
            log.error("Redis Get Value Error!", e);
            throw new RuntimeException(e);
        } finally {
            giveback(jedis, isOK);
        }
    }

    /**
     * 若key不存在，则存储
     *
     * @param key
     * @param value
     * @param seconds
     * @return
     */
    public static final boolean setnx(String key, final String value, int seconds) {
        Jedis jedis = null;
        boolean isOK = true;
        try {
            jedis = jedisPool.getResource();
            long result = jedis.setnx(KEY_PREFIX + key, value);
            if (result == 1) {
                jedis.expire(KEY_PREFIX + key, seconds);
                return true;
            }
            return false;
        } catch (Exception e) {
            isOK = false;
            log.error("Redis Set Value Error!", e);
            throw new RuntimeException(e);
        } finally {
            giveback(jedis, isOK);
        }
    }

    public static boolean remove(final String key) {
        return remove(key, null);
    }

    /**
     * @param key
     * @param permission 为null直接不查询删除，不为null查询回调判断
     * @return
     */
    public static boolean remove(final String key, final DeletePermission permission) {
        Jedis jedis = null;
        boolean isOK = true;
        try {
            jedis = jedisPool.getResource();
            if (permission != null) {
                if (!permission.allows(jedis.get(KEY_PREFIX + key)))
                    return false;
            }
            Long result = jedis.del(KEY_PREFIX + key);
            if (result != null && result > 0)
                return true;
            return false;
        } catch (Exception e) {
            isOK = false;
            log.error("Redis Remove Value Error!", e);
            throw new RuntimeException(e);
        } finally {
            giveback(jedis, isOK);
        }
        // return false;
    }

    /**
     * @param
     * @return boolean
     * @Description: 删除序列化缓存
     * @author: 孟伟
     * @date: 2015年7月13日 上午10:12:43
     */
    public static boolean removeObject(final String key) {
        Jedis jedis = null;
        boolean isOK = true;
        try {
            jedis = jedisPool.getResource();
            Long result = jedis.del((KEY_PREFIX + key).getBytes());
            if (result != null && result > 0)
                return true;
            return false;
        } catch (Exception e) {
            isOK = false;
            log.error("Redis Remove Value Error!", e);
            throw new RuntimeException(e);
        } finally {
            giveback(jedis, isOK);
        }
    }

    public interface DeletePermission {
        /**
         * 是否允许删除
         *
         * @param cache
         * @return
         */
        public boolean allows(String cache);
    }

    public static <T> T getSetBean(final String key, final T value, final Class<T> getBeanclazz) {
        String got = getSet(key, JSON.toJSONString(value));
        if (BT.isEmpty(got))
            return null;

        return JSON.parseObject(got, getBeanclazz);
    }

    public static String getSet(final String key, final String value) {
        Jedis jedis = null;
        boolean isOK = true;
        try {
            jedis = jedisPool.getResource();
            return jedis.getSet(KEY_PREFIX + key, value);
        } catch (Exception e) {
            isOK = false;
            log.error("Redis getset Value Error!", e);
            throw new RuntimeException(e);
        } finally {
            giveback(jedis, isOK);
        }
    }

    public static long incr(final String key) {
        Jedis jedis = null;
        boolean isOK = true;
        try {
            jedis = jedisPool.getResource();
            return jedis.incr(KEY_PREFIX + key);
        } catch (Exception e) {
            isOK = false;
            log.error("Redis incr Value Error!", e);
            throw new RuntimeException(e);
        } finally {
            giveback(jedis, isOK);
        }
    }

    /**
     * 设置key生存时间，当key过期时，它会被自动删除。
     *
     * @param key
     * @param seconds
     * @return
     */
    public static long expire(final String key, final int seconds) {
        Jedis jedis = null;
        boolean isOK = true;
        try {
            jedis = jedisPool.getResource();
            return jedis.expire(KEY_PREFIX + key, seconds);
        } catch (Exception e) {
            isOK = false;
            log.error("Redis incr Value Error!", e);
            throw new RuntimeException(e);
        } finally {
            giveback(jedis, isOK);
        }
    }


    private static void giveback(final Jedis jedis, final boolean isOK) {
        if (jedis != null) {
            if (isOK)
                jedisPool.returnResource(jedis);
            else
                jedisPool.returnBrokenResource(jedis);
        }
    }

    /**
     * 存活时间(ttl)：time to live
     *
     * @param key
     * @return
     */
    public static Long ttl(final String key) {
        Jedis jedis = null;
        boolean isOK = true;
        try {
            jedis = jedisPool.getResource();
            return jedis.ttl(KEY_PREFIX + key);
        } catch (Exception e) {
            isOK = false;
            log.error("Redis ttl Value Error!", e);
            throw new RuntimeException(e);
        } finally {
            giveback(jedis, isOK);
        }
    }

}
