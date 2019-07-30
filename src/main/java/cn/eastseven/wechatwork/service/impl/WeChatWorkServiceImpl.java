package cn.eastseven.wechatwork.service.impl;

import cn.eastseven.wechatwork.autoconfigure.WeChatWorkProperties;
import cn.eastseven.wechatwork.model.AccessTokenEntity;
import cn.eastseven.wechatwork.model.AccessTokenResponse;
import cn.eastseven.wechatwork.model.msg.MarkdownTextRequest;
import cn.eastseven.wechatwork.repository.AccessTokenRepository;
import cn.eastseven.wechatwork.service.WeChatWorkService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

/**
 * @author eastseven
 */
@Slf4j
public class WeChatWorkServiceImpl implements WeChatWorkService {

    private ObjectMapper om;

    private WeChatWorkProperties properties;

    private RestTemplate restTemplate;

    @Autowired
    private AccessTokenRepository accessTokenRepository;

    public WeChatWorkServiceImpl(WeChatWorkProperties properties, RestTemplate restTemplate) {
        this.properties = properties;
        this.restTemplate = restTemplate;
        this.om = new ObjectMapper();
    }

    @Override
    public AccessTokenResponse accessToken() {
        String corpId = properties.getCorpId();
        String corpSecret = properties.getCorpSecret();
        Objects.requireNonNull(corpId);
        Objects.requireNonNull(corpSecret);
        return accessToken(corpId, corpSecret);
    }

    @Override
    public AccessTokenResponse accessToken(String corpId, String corpSecret) {
        Optional<AccessTokenEntity> optional = accessTokenRepository.findById(corpId);
        if (optional.isPresent()) {
            AccessTokenEntity entity = optional.get();
            boolean notExpired = new Date().before(entity.getExpireTime());
            if (notExpired) {
                return AccessTokenResponse.of(entity);
            }
        }

        final String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid={corpId}&corpsecret={corpSecret}";
        AccessTokenResponse response = restTemplate.getForEntity(url, AccessTokenResponse.class, corpId, corpSecret).getBody();
        log.debug(">>>获取企业微信AccessToken\n{}", response);

        if (response.getErrCode() == 0) {
            accessTokenRepository.save(new AccessTokenEntity(corpId, response));
        }
        return response;
    }

    /**
     * https://www.baeldung.com/spring-resttemplate-post-json
     *
     * @param requestBody 消息体
     * @return 结果
     */
    private Object send(String requestBody) {
        AccessTokenResponse accessTokenResponse = accessToken();
        if (accessTokenResponse.getErrCode() != 0) {
            throw new RuntimeException(accessTokenResponse.getErrMsg());
        }

        final String url = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token={accessToken}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class, accessTokenResponse.getAccessToken());
        log.info("\nrequestBody={}\nresponse={}\n", requestBody, response);
        return response;
    }

    @Override
    public Object sendText(String msg) {
        String requestBody = "{\n" +
                "   \"totag\" : \"" + properties.getTagId() + "\",\n" +
                "   \"msgtype\" : \"text\",\n" +
                "   \"agentid\" : " + properties.getAgentId() + ",\n" +
                "   \"text\" : {\n" +
                "       \"content\" : \"" + msg + "\"" + "\n" +
                "   },\n" +
                "   \"safe\":0\n" +
                "}";
        return send(requestBody);
    }

    @Override
    public Object sendMarkdown(MarkdownTextRequest msg) {

        try {
            String requestBody = om.writeValueAsString(msg);
            log.debug("\n{}\n", requestBody);
            return send(requestBody);
        } catch (JsonProcessingException e) {
            log.error("", e);
        }

        return null;
    }
}
