package cn.eastseven.wechatwork.autoconfigure;

import cn.eastseven.wechatwork.service.WeChatWorkService;
import cn.eastseven.wechatwork.service.impl.WeChatWorkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

/**
 * @author eastseven
 */
@Configuration
@ConditionalOnClass({WeChatWorkService.class})
@EnableConfigurationProperties(WeChatWorkProperties.class)
public class WeChatWorkAutoConfiguration {

    @Autowired
    private WeChatWorkProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public WeChatWorkService weChatWorkService() {
        Objects.requireNonNull(properties);
        Objects.requireNonNull(properties.getCorpId(), "wechat.work.corpId is null");
        Objects.requireNonNull(properties.getCorpSecret(), "wechat.work.corpSecret is null");
        Objects.requireNonNull(properties.getAgentId(), "wechat.work.agentId is null");
        Objects.requireNonNull(properties.getTagId(), "wechat.work.tagId is null");

        return new WeChatWorkServiceImpl(properties, new RestTemplate());
    }
}
