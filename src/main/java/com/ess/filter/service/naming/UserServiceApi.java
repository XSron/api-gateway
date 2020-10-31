package com.ess.filter.service.naming;

import com.ess.filter.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user-service")
public interface UserServiceApi {

    @GetMapping("/user/{username}")
    User findUSerByName(@PathVariable("username") String username);
}
