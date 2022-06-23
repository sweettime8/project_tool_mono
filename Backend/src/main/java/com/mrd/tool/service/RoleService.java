package com.mrd.tool.service;

import com.mrd.tool.entity.Role;
import com.mrd.tool.form.role.RoleCreateForm;
import com.mrd.tool.form.role.RoleUpdateForm;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    Role createRole(RoleCreateForm form, String username);

    Role updateByRoleCode(RoleUpdateForm form, String username);

    List<Role> findRoleCreateByUsername(String username);

    boolean updateRoleCode(String newRoleCode, String newRoleDetail, String roleCodeOld);

    boolean remove(Role role);

    Role findByRoleCode(String roleCode);

    Optional<Role> findById(Long id);

    List<Role> findActive();

}
