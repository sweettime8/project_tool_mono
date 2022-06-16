package com.mrd.tool.service;

import com.mrd.tool.entity.User;
import com.mrd.tool.entity.dto.user.UserSearchForm;

import java.util.List;

public interface UserService {
    User findByUserName(String userName);

    List<User> search(UserSearchForm searchForm, int currentPage, int rowPerPage);

    User findByUuid(String uuid);

    User findByEmail(String email);

    User findByMobile(String mobile);

    long count(UserSearchForm searchForm);

    void save(User user);

}
