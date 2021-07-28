package pers.guzx.uaa.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import pers.guzx.uaa.code.ErrorCode;
import pers.guzx.uaa.entity.JsonDto;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.Map;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021-07-27 上午 11:16
 * @describe
 */
@RestController
@RequestMapping("/oauth")
public class OauthController {

    @Resource
    private TokenEndpoint tokenEndpoint;

    @GetMapping("/token")
    public JsonDto getAccessToken(Principal principal, @RequestParam Map<String, String> parameters) {
        ResponseEntity<OAuth2AccessToken> accessToken = null;
        try {
            accessToken = tokenEndpoint.getAccessToken(principal, parameters);

        } catch (HttpRequestMethodNotSupportedException e) {
            e.printStackTrace();
        }
        OAuth2AccessToken body = accessToken.getBody();
        return JsonDto.retOk(body);
    }

    @PostMapping("/token")
    public JsonDto postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) {
        ResponseEntity<OAuth2AccessToken> oAuth2AccessTokenResponseEntity = null;
        try {
            oAuth2AccessTokenResponseEntity = tokenEndpoint.postAccessToken(principal, parameters);
        } catch (HttpRequestMethodNotSupportedException e) {
            e.printStackTrace();
            return JsonDto.retFail(ErrorCode.CUSTOMER_AUTHORITY_FAIL, e.getMessage());
        }
        OAuth2AccessToken body = oAuth2AccessTokenResponseEntity.getBody();
        return JsonDto.retOk(body);

    }
}
