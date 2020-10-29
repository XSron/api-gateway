package com.ess.filter.config;

import com.ess.filter.entity.Privilege;
import com.ess.filter.entity.User;
import com.ess.filter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CustomTokenEnhancer implements TokenEnhancer {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> additionalInfo = new HashMap<>();
        User user = userService.findByUsername(authentication.getName());
        additionalInfo.put("user_id", user.getId());
        List<Privilege> privileges = user.getRoles().stream()
                .map(u->u.getPrivileges()).flatMap(List::stream)
                .collect(Collectors.toList());
        additionalInfo.put("privileges", privileges);
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
