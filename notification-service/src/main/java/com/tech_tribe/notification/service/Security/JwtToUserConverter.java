package com.tech_tribe.notification.service.Security;

import com.tech_tribe.notification.service.entity.User;
import com.tech_tribe.notification.service.utility.UserRole;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class JwtToUserConverter implements Converter<Jwt, UsernamePasswordAuthenticationToken> {

    @Override
    public UsernamePasswordAuthenticationToken convert(Jwt jwt) {
        User user = new User(jwt.getClaim("empId"), UserRole.valueOf(jwt.getClaim("role")), jwt.getClaim("email"));
        return new UsernamePasswordAuthenticationToken(user, jwt, Collections.EMPTY_LIST);
    }
}
