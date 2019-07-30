package cn.eastseven.wechatwork.model.msg;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author d7
 */
@Data
public class BaseMsgRequest implements Serializable {

    @JsonProperty("touser")
    protected String toUser;

    @JsonProperty("toparty")
    protected String toParty;

    @JsonProperty("totag")
    protected String toTag;

    @JsonProperty("msgtype")
    protected MsgType msgType = MsgType.text;

    @JsonProperty("agentid")
    protected int agentId;
}
