package cn.eastseven.wechatwork.model.msg;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author d7
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MarkdownTextRequest extends BaseMsgRequest {

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

    public MarkdownTextRequest() {
        this.msgType = MsgType.markdown;
    }

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
