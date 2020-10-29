package com.ess.filter.service;

import com.ess.filter.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {
    UserDetails loadUserByUsername(String userName);
    User findByUsername(String name);
//    User testApi(String username, String token);
}
