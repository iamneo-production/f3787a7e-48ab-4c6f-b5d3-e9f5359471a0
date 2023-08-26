package com.tech_tribe.authservice.security;

import com.tech_tribe.authservice.entity.User;
import com.tech_tribe.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class JwtToUserConverter implements Converter<Jwt, UsernamePasswordAuthenticationToken>
{
    @Autowired
    UserRepository userRepository;

    @Override
    public UsernamePasswordAuthenticationToken convert(Jwt jwt) {
        String username =jwt.getSubject();
        User user = userRepository.findByUsername(username).orElseThrow();
        return new UsernamePasswordAuthenticationToken(user,jwt, Collections.EMPTY_LIST);
    }
}
