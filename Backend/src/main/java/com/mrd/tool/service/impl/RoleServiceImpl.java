package com.mrd.tool.service.impl;

import com.mrd.tool.entity.Role;
import com.mrd.tool.form.role.RoleCreateForm;
import com.mrd.tool.form.role.RoleUpdateForm;
import com.mrd.tool.repository.RoleRepository;
import com.mrd.tool.repository.RoleRepositoryCustom;
import com.mrd.tool.service.BaseService;
import com.mrd.tool.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl extends BaseService implements RoleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleRepositoryCustom roleRepositoryCustom;

    @Override
    public Role createRole(RoleCreateForm form, String username) {
        Role role = super.getModelMapper().map(form, Role.class);
        fillBaseInfo(role, username, false);
        roleRepository.save(role);
        return role;
    }

    @Override
    public Role updateByRoleCode(RoleUpdateForm form, String username) {
        Role role = roleRepository.findByRoleCode(form.getRoleCode());
        if (role != null) {
            super.getModelMapper().map(form, role);
            fillBaseInfo(role, username, true);
            roleRepository.save(role);
        }
        return role;
    }

    @Override
    public List<Role> findRoleCreateByUsername(String username) {
        return roleRepository.findByCreatedByAndIsDeleted(username, 0);
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
        return roleRepositoryCustom.updateRoleCode(newRoleCode, newRoleDetail, roleCodeOld);
    }
}
