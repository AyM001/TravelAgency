package com.example.ProjectTogether.common.config;


import com.example.ProjectTogether.common.utils.Hasher;
import com.example.ProjectTogether.model.UserModel;
import com.example.ProjectTogether.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        if (shouldAuthenticateAgainstThirdPartySystem(username, password)) {

            // use the credentials
            // and authenticate against the third-party system

            UserModel userModel = userRepository.getUserModelByUsername(username).orElse(null);
            Set<SimpleGrantedAuthority> authorities = new HashSet<>();
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_" + userModel.getUserRole());
            authorities.add(simpleGrantedAuthority);

            return new UsernamePasswordAuthenticationToken(
                    username, password, authorities);
        } else {
            return null;
        }
    }
    private boolean shouldAuthenticateAgainstThirdPartySystem(String name, String password){
       /* if (name == "admin" && password == "admin"){
            return true;
        }

        Security access with login admin admin

        */
        UserModel user=userRepository.getUserModelByUsername(name).orElse(null);
        if(user!=null&&user.getPassword().equals(Hasher.encode(password))){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
