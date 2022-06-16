package com.mrd.tool.service.impl;

import com.mrd.tool.entity.User;
import com.mrd.tool.entity.dto.user.UserSearchForm;
import com.mrd.tool.repository.UserRepository;
import com.mrd.tool.repository.UserRepositoryCustom;
import com.mrd.tool.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRepositoryCustom userRepositoryCustom;

    public long countAll() {
        return userRepository.count();
    }

    @Override
    public User findByUserName(String userName) {
        return userRepositoryCustom.findByUserName(userName);
    }

    @Override
    public List<User> search(UserSearchForm searchForm, int currentPage, int rowPerPage) {
        return userRepositoryCustom.search(searchForm, currentPage, rowPerPage);
    }

    @Override
    public User findByUuid(String uuid) {
        return userRepositoryCustom.findByUuid(uuid);
    }

    @Override
    public User findByEmail(String email) {
        try {
            return userRepositoryCustom.findByEmail(email);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return null;
    }

    @Override
    public User findByMobile(String mobile) {
        return userRepositoryCustom.findByMobile(mobile);
    }

    @Override
    public long count(UserSearchForm searchForm) {
        return userRepositoryCustom.count(searchForm);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

}