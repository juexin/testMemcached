package com.qf.testmemcached.testmemcached;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.command.TextCommandFactory;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;
import net.rubyeye.xmemcached.transcoders.SerializingTranscoder;
import net.rubyeye.xmemcached.utils.AddrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 陈综光
 * @date 2016/10/14 17:11
 */
@Configuration
@EnableCaching(proxyTargetClass=true)
public class XmemcahedConfiguration extends CachingConfigurerSupport {
    @Value("${spring.data.memcached.address}")
    private  String address;
    @Autowired
    private Environment env;
    @Bean
    public MemcachedClient memcachedClient() throws IOException {
        System.out.println(env);
        XMemcachedClientBuilder clientBuilder = new XMemcachedClientBuilder(AddrUtil.getAddresses(address));
        //连接池数目，默认是1
        clientBuilder.setConnectionPoolSize(1);
        //默认是文协议。 BinaryCommandFactory
        clientBuilder.setCommandFactory(new TextCommandFactory());
        //序列化转换器
        clientBuilder.setTranscoder(new SerializingTranscoder());
        // 设置客户端负载均衡算法，默认是一致性hash。
        /**
         * ElectionMemcachedSessionLocator 选举hash
         */
        clientBuilder.setSessionLocator(new KetamaMemcachedSessionLocator());
        return clientBuilder.build();
    }

    @Bean
    public KeyGenerator wiselyKeyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }
    @Bean
    public CacheManager cacheManager(MemcachedClient memcachedClient){
        MemcachedCacheManager cacheManager = new MemcachedCacheManager(memcachedClient);
        Map<String, Integer> expireMap = new HashMap<String, Integer>();   //缓存的时间
        expireMap.put("defaultCache",0);
        expireMap.put("inTimeCache",3600);
        cacheManager.setConfigMap(expireMap);
        return cacheManager;
    }

}
