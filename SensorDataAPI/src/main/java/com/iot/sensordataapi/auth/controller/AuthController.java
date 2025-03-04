package com.iot.sensordataapi.auth.controller;

import com.iot.sensordataapi.auth.model.AuthRequest;
import com.iot.sensordataapi.auth.service.AuthService;
import com.iot.sensordataapi.auth.service.JwtUtilService;
import com.iot.sensordataapi.exception.UserNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.iot.sensordataapi.constants.Constants.AUTH_TOKEN;
import static com.iot.sensordataapi.constants.Constants.USER_NOT_FOUND;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class AuthController {

    private JwtUtilService jwtUtilService;

    private AuthService authServiceImpl;

    /**
     * * Generates a JWT token for the given username.
     * <p>This method accepts a username to generate a JSON Web Token (JWT).
     * The token is signed using the application's secret key and includes an expiration time.
     * The generated token can be used for authentication and authorization purposes.</p>
     *
     * @param authRequest the authRequest for which the token is to be generated.
     *                    Must not be null or empty.
     * @return token
     */
    @PostMapping(AUTH_TOKEN)
    public ResponseEntity<String> getToken(@RequestBody @Valid AuthRequest authRequest) {

        UserDetails user = authServiceImpl.getUser(authRequest.username());
        if (user != null) {
            return ResponseEntity.ok(jwtUtilService.generateToken(user.getUsername()));
        }
        throw new UserNotFoundException(USER_NOT_FOUND);
    }
}
