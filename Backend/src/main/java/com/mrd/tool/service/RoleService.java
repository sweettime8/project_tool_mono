package com.mrd.tool.service;

import com.mrd.tool.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    void save(Role role);

    boolean update(Role role);

    boolean updateRoleCode(String newRoleCode, String newRoleDetail, String roleCodeOld);

    boolean remove(Role role);

    Role findByRoleCode(String roleCode);

    Optional<Role> findById(Long id);

    List<Role> findActive();

}
