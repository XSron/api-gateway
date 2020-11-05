package com.ess.filter.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@ToString
@Component
@Data
public class ConfigValue {

    @Value("${security.jwt.header}")
    private String header;

    @Value("${security.jwt.prefix}")
    private String prefix;

    @Value("${security.jwt.expiration}")
    private int expiration;

    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.client}")
    private String client;

    @Value("${url.user}")
    private String userUrl;

    @Value("${url.product}")
    private String productUrl;

    @Value("${url.report}")
    private String reportUrl;

    @Value("${url.upload}")
    private String uploadUrl;

    @Value("${url.payment}")
    private String paymentUrl;

    @Value("${url.shopping}")
    private String shoppingUrl;

}
