package cn.eastseven.wechatwork.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author eastseven
 */
@Data
@ConfigurationProperties(prefix = "wechat.work")
public class WeChatWorkProperties {

    private String corpId;

    private String corpSecret;

    private Integer agentId;

    private Integer tagId;
}
