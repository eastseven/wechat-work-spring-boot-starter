package cn.eastseven.wechatwork.service;

import cn.eastseven.wechatwork.model.AccessTokenResponse;
import cn.eastseven.wechatwork.model.msg.MarkdownTextRequest;
import cn.eastseven.wechatwork.model.msg.TaskCardRequest;

/**
 * @author eastseven
 */
public interface WeChatWorkService {

    /**
     * 获取AccessToken
     *
     * @return 默认AccessToken
     */
    AccessTokenResponse accessToken();

    /**
     * 获取AccessToken
     *
     * @param corpId     ID
     * @param corpSecret Secret
     * @return 结果
     */
    AccessTokenResponse accessToken(String corpId, String corpSecret);

    /**
     * 发送文本消息
     *
     * @param msg 消息
     * @return 结果
     */
    Object sendText(String msg);

    /**
     * markdown消息
     *
     * @param msg markdown消息
     * @return 结果
     */
    Object sendMarkdown(MarkdownTextRequest msg);

    /**
     * 任务卡片消息
     *
     * @param msg 任务卡片消息
     * @return 结果
     */
    Object sendTaskCard(TaskCardRequest msg);
}
