package com.phpimentel.hroauth.services;

import com.phpimentel.hroauth.entities.User;
import com.phpimentel.hroauth.feignclients.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class UserService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserFeignClient userFeignClient;

    public User findByEmail(String email) {
        User user = this.userFeignClient.findByEmail(email).getBody();
        if (Objects.isNull(user)) {
            LOGGER.error("Email not found: " + email);
            throw new IllegalArgumentException("Email nor found");
        }
        LOGGER.info("Email found: " + email);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userFeignClient.findByEmail(username).getBody();
        if (Objects.isNull(user)) {
            LOGGER.error("Email not found: " + username);
            throw new UsernameNotFoundException("Email nor found");
        }
        LOGGER.info("Email found: " + username);
        return user;
    }
}
