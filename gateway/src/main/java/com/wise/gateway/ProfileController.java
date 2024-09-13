package com.wise.gateway;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProfileController {

    @GetMapping("/userinfo")
    @CrossOrigin("http://localhost:5173")
    public Map<String, Object> userInfo(@AuthenticationPrincipal OidcUser oidcUser, @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient) {

        System.out.println("TOKEN:" + authorizedClient.getAccessToken().getTokenValue());
        Map<String, Object> attributesMap = new HashMap<>(oidcUser.getAttributes());
        attributesMap.put("id_token", oidcUser.getIdToken().getTokenValue());
        attributesMap.put("access_token", authorizedClient.getAccessToken().getTokenValue());
        attributesMap.put("client_name", authorizedClient.getClientRegistration().getClientId());
        attributesMap.put("user_attributes", oidcUser.getAttributes());
        return attributesMap;
    }
}
