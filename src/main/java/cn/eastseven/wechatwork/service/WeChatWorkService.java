package cn.eastseven.wechatwork.service;

import cn.eastseven.wechatwork.model.AccessTokenResponse;

/**
 * @author eastseven
 */
public interface WeChatWorkService {

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
}
