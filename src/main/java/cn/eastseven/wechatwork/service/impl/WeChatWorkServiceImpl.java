package cn.eastseven.wechatwork.service.impl;

import cn.eastseven.wechatwork.autoconfigure.WeChatWorkProperties;
import cn.eastseven.wechatwork.model.AccessTokenResponse;
import cn.eastseven.wechatwork.service.WeChatWorkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author eastseven
 */
@Slf4j
public class WeChatWorkServiceImpl implements WeChatWorkService {

    private WeChatWorkProperties properties;

    private RestTemplate restTemplate;

    public WeChatWorkServiceImpl(WeChatWorkProperties properties, RestTemplate restTemplate) {
        this.properties = properties;
        this.restTemplate = restTemplate;
    }

    @Override
    public AccessTokenResponse accessToken(String corpId, String corpSecret) {
        final String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid={corpId}&corpsecret={corpSecret}";
        AccessTokenResponse response = restTemplate.getForEntity(url, AccessTokenResponse.class, corpId, corpSecret).getBody();
        log.debug(">>>获取企业微信AccessToken\n{}", response);
        return response;
    }

    @Override
    public Object sendText(String msg) {
        AccessTokenResponse accessTokenResponse = accessToken(properties.getCorpId(), properties.getCorpSecret());
        if (accessTokenResponse.getErrCode() == 0) {
            final String url = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token={accessToken}";
            // https://www.baeldung.com/spring-resttemplate-post-json
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            String requestBody = "{\n" +
                    "   \"totag\" : \"" + properties.getTagId() + "\",\n" +
                    "   \"msgtype\" : \"text\",\n" +
                    "   \"agentid\" : " + properties.getAgentId() + ",\n" +
                    "   \"text\" : {\n" +
                    "       \"content\" : \"" + msg + "\"" + "\n" +
                    "   },\n" +
                    "   \"safe\":0\n" +
                    "}";
            HttpEntity<String> request = new HttpEntity<String>(requestBody, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class, accessTokenResponse.getAccessToken());
            log.info("\nrequestBody={}\nresponse={}\n", requestBody, response);
            return response;
        }

        return null;
    }
}
