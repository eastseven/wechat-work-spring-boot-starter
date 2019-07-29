package cn.eastseven.wechatwork.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.keyvalue.annotation.KeySpace;

import java.io.Serializable;
import java.util.Date;

/**
 * @author eastseven
 */
@Data
@KeySpace("AccessToken")
public class AccessTokenEntity implements Serializable {

    @Id
    private String token;

    private Date createTime;

    private int expires;

    private Date expireTime;
}
