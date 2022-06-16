package com.mrd.tool.service.impl;

import com.mrd.tool.auth.CustomUserDetails;
import com.mrd.tool.entity.User;
import com.mrd.tool.repository.UserRepositoryCustom;
import com.mrd.tool.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AuthServiceImpl implements UserDetailsService, AuthService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);
    @Autowired
    private UserRepositoryCustom userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) {
        // Kiểm tra xem user có tồn tại trong database không?
        User user = userRepository.findByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with userInfo : " + username);
        } else {
            LOGGER.info("Find user with " + username + " ==> uuid: " + user.getUuid());
        }
        return new CustomUserDetails(user);
    }

    // JWTAuthenticationFilter sẽ sử dụng hàm này
    @Transactional
    @Override
    public UserDetails loadUserByUuid(String uuid) {
        User user = userRepository.findByUuid(uuid);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with uuid : " + uuid);
        }
        return new CustomUserDetails(user);
    }

}
