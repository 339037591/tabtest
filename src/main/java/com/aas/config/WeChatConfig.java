package com.aas.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:wc-params.properties")
@Data
public class WeChatConfig {

    @Value("${wechat.index.url}")
    private String indexURL;

    @Value("${wechat.login.url}")
    private String loginURL;

    @Value("${wechat.token.url}")
    private String tokenURL;

    @Value("${wechat.user.url}")
    private String userURL;

    @Value("${wechat.reget.url}")
    private String regetURL;

    @Value("${wechat.message.url}")
    private String messageURL;

    @Value("${wechat.app.id}")
    private String appId;

    @Value("${wechat.upload.url}")
    private String uploadURL;

}
