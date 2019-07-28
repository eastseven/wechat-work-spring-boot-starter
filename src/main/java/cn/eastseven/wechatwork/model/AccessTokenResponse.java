package cn.eastseven.wechatwork.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

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

}
