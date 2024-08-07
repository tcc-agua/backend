package com.ambercff.gateway;

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
    @CrossOrigin("http://localhost:3000")
    public Map<String, Object> userInfo(@AuthenticationPrincipal OidcUser oidcUser, @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient) {
        Map<String, Object> attributesMap = new HashMap<>(oidcUser.getAttributes());
        attributesMap.put("idToken", oidcUser.getIdToken().getTokenValue());
        attributesMap.put("clientName", authorizedClient.getClientRegistration().getClientId());
        attributesMap.put("userAttributes", oidcUser.getAttributes());
        return attributesMap;
    }
}
