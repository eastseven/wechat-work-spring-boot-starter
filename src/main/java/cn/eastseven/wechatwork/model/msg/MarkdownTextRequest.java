package cn.eastseven.wechatwork.model.msg;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author d7
 */
@Data
public class MarkdownTextRequest implements Serializable {

    public static MarkdownTextRequest of(int agentId, String users, String parties, String tags, String content) {
        MarkdownTextRequest request = new MarkdownTextRequest();
        request.agentId = agentId;
        request.toUser = users;
        request.toParty = parties;
        request.toTag = tags;
        request.markdown = new MarkdownBean(content);
        return request;
    }

    public static MarkdownTextRequest toUser(int agentId, String users, String content) {
        MarkdownTextRequest request = new MarkdownTextRequest();
        request.agentId = agentId;
        request.toUser = users;
        request.markdown = new MarkdownBean(content);
        return request;
    }

    public static MarkdownTextRequest toParty(int agentId, String parties, String content) {
        MarkdownTextRequest request = new MarkdownTextRequest();
        request.agentId = agentId;
        request.toParty = parties;
        request.markdown = new MarkdownBean(content);
        return request;
    }

    public static MarkdownTextRequest toTag(int agentId, String tags, String content) {
        MarkdownTextRequest request = new MarkdownTextRequest();
        request.agentId = agentId;
        request.toTag = tags;
        request.markdown = new MarkdownBean(content);
        return request;
    }

    /**
     * touser : UserID1|UserID2|UserID3
     * toparty : PartyID1|PartyID2
     * totag : TagID1 | TagID2
     * msgtype : markdown
     * agentid : 1
     * markdown : {"content":"您的会议室已经预定，稍后会同步到`邮箱`                  >**事项详情**                  >事　项：<font color=\"info\">开会<\/font>                  >组织者：@miglioguan                  >参与者：@miglioguan、@kunliu、@jamdeezhou、@kanexiong、@kisonwang                  >                  >会议室：<font color=\"info\">广州TIT 1楼 301<\/font>                  >日　期：<font color=\"warning\">2018年5月18日<\/font>                  >时　间：<font color=\"comment\">上午9:00-11:00<\/font>                  >                  >请准时参加会议。                  >                  >如需修改会议信息，请点击：[修改会议信息](https://work.weixin.qq.com)"}
     */
    @JsonProperty("touser")
    private String toUser;

    @JsonProperty("toparty")
    private String toParty;

    @JsonProperty("totag")
    private String toTag;

    @JsonProperty("msgtype")
    private MsgType msgType = MsgType.markdown;

    @JsonProperty("agentid")
    private int agentId;

    private MarkdownBean markdown;

    @Data
    private static class MarkdownBean {

        public MarkdownBean(String content) {
            this.content = content;
        }

        /**
         * content : 您的会议室已经预定，稍后会同步到`邮箱`                  >**事项详情**                  >事　项：<font color="info">开会</font>                  >组织者：@miglioguan                  >参与者：@miglioguan、@kunliu、@jamdeezhou、@kanexiong、@kisonwang                  >                  >会议室：<font color="info">广州TIT 1楼 301</font>                  >日　期：<font color="warning">2018年5月18日</font>                  >时　间：<font color="comment">上午9:00-11:00</font>                  >                  >请准时参加会议。                  >                  >如需修改会议信息，请点击：[修改会议信息](https://work.weixin.qq.com)
         */

        private String content;
    }
}
