package com.mrd.tool.service.impl;

import com.mrd.tool.entity.Role;
import com.mrd.tool.repository.RoleRepository;
import com.mrd.tool.repository.RoleRepositoryCustom;
import com.mrd.tool.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleRepositoryCustom roleRepositoryCustom;

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public boolean update(Role role) {
        System.out.println(role.getRoleCode());
        System.out.println(role.getRoleName());
        System.out.println(roleRepository.updateRole(role.getRoleCode(), role.getRoleName()) > 0);
        return roleRepository.updateRole(role.getRoleCode(), role.getRoleName()) > 0;
    }

    @Override
    public boolean remove(Role role) {
        return roleRepository.deleteRole(role.getRoleCode()) > 0;
    }

    @Override
    public Role findByRoleCode(String roleCode) {
        return roleRepository.findByRoleCode(roleCode);
    }

    @Override
    public List<Role> findActive() {
        return roleRepository.findByIsDeletedNot(1);
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public boolean updateRoleCode(String newRoleCode, String newRoleDetail, String roleCodeOld) {
        return roleRepositoryCustom.updateRoleCode(newRoleCode, newRoleDetail,roleCodeOld);
    }
}
