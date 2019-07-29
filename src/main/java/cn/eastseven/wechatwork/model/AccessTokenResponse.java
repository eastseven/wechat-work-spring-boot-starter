package cn.eastseven.wechatwork.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author eastseven
 */
@Data
public class AccessTokenResponse implements Serializable {

    @JsonProperty("errcode")
    private int errCode;

    @JsonProperty("errmsg")
    private String errMsg;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private int expires;

    public static AccessTokenResponse of(AccessTokenEntity entity) {
        Objects.requireNonNull(entity);
        Objects.requireNonNull(entity.getToken());
        AccessTokenResponse response = new AccessTokenResponse();
        response.errCode = 0;
        response.errMsg = "ok";
        response.accessToken = entity.getToken();
        response.expires = entity.getExpires();
        return response;
    }
}
