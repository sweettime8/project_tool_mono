package com.mrd.tool.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    UserDetails loadUserByUuid(String uuid);

}
