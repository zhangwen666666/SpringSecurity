package com.zw.listener;

import com.zw.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class ApplicationShutdownListener implements ApplicationListener<ContextClosedEvent> {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    /**
     * spring上下文关闭时，会触发执行该方法
     * @param event
     */
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        // System.out.println("spring context 关闭了");
        // 让登录用户的token失效，删除redis中的token
        redisTemplate.delete(Constant.REDIS_TOKEN_KEY);
    }
}
