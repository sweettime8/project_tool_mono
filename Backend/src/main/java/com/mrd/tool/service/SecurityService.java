package com.mrd.tool.service;

import com.mrd.tool.auth.CustomUserDetails;
import com.mrd.tool.entity.User;
import com.mrd.tool.repository.UserRepositoryCustom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityService implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityService.class);
    @Autowired
    private UserRepositoryCustom userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Kiểm tra xem user có tồn tại trong database không?
        User user = userRepository.findByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with userInfo : " + username);
        } else {
            LOGGER.info("Find user with " + username + " ==> uuid: " + user.getUuid());
        }
        return new CustomUserDetails(user);
    }


}
