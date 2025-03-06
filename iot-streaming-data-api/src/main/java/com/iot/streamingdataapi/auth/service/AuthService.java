package com.iot.streamingdataapi.auth.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;


/**
 *
 */
@Service
public class AuthService {

    /**
     * @param userName
     * @return
     */
    public User getUser(String userName) {
        if (userName.equalsIgnoreCase("datastream")) {
            return (User) User.builder()
                    .username("datastream")
                    .password("Datastream1!")
                    .roles("USER")
                    .build();

        }
        return null;
    }

}
