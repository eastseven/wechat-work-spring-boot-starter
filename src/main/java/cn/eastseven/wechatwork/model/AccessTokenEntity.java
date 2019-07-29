package cn.eastseven.wechatwork.model;

import lombok.Data;
import org.joda.time.DateTime;
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

    public AccessTokenEntity(String id, AccessTokenResponse response) {
        this.id = id;
        this.token = response.getAccessToken();
        this.expires = response.getExpires();

        final DateTime now = DateTime.now();
        this.createTime = now.toDate();
        this.expireTime = now.plusSeconds(response.getExpires()).toDate();
    }

    @Id
    private String id;

    private String token;

    private int expires;

    private Date createTime;

    private Date expireTime;
}
