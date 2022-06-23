package com.mrd.tool.service.impl;

import com.mrd.tool.entity.Role;
import com.mrd.tool.entity.RoleUser;
import com.mrd.tool.repository.RoleUserRepository;
import com.mrd.tool.service.RoleUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleUserServiceImpl implements RoleUserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleUserServiceImpl.class);

    @Autowired
    private RoleUserRepository roleUserRepository;

    @Override
    public List<RoleUser> findByUuidUser(String username) {
        List<RoleUser> roleUserList = null;
        try {
            roleUserList = roleUserRepository.findByUsernameAndAndIsDeleted(username, 0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return roleUserList;
    }

    @Override
    public List<RoleUser> findByRoleCode(Role roleCode) {
        return null;
    }

    @Override
    public RoleUser findByUuidUserAndRoleCode(String uuidUser, Role roleCode) {
        return null;
    }

    @Override
    public RoleUser findByUuidUserAndRoleCodeAndIsDelete(String uuidUser, Role roleCode, Integer isDelete) {
        return null;
    }

    @Override
    public RoleUser findByUuidUserAndRoleCodeInAndIsDeleted(String uuidUser, Role roleCode, Integer isDelete) {
        return null;
    }

    @Override
    public Optional<RoleUser> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(RoleUser role) {

    }

    @Override
    public boolean update(RoleUser role, String newRole) {
        return false;
    }

    @Override
    public boolean remove(RoleUser role) {
        return false;
    }

    @Override
    public boolean findAdmin(String roleCode) {
        return false;
    }

    @Override
    public List<Role> findAdminRoleList() {
        return null;
    }
}
