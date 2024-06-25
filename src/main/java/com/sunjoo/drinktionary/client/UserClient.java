package com.sunjoo.drinktionary.client;

import com.sunjoo.drinktionary.dto.UserInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name = "auth-service", url = "http://sunjoo-server-auth-spring-1:8090")
public interface UserClient {

    @GetMapping("/auth/userinfo")
    UserInfoResponse getUserInfo(@RequestHeader("Authorization") String token);
}
