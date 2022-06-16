package com.mrd.tool.service;

import com.mrd.tool.entity.Role;
import com.mrd.tool.entity.RoleUser;

import java.util.List;
import java.util.Optional;

public interface RoleUserService {
    List<RoleUser> findByUuidUser(String uuidUser);

    List<RoleUser> findByRoleCode(Role roleCode);

    RoleUser findByUuidUserAndRoleCode(String uuidUser, Role roleCode);

    RoleUser findByUuidUserAndRoleCodeAndIsDelete(String uuidUser, Role roleCode, Integer isDelete);

    RoleUser findByUuidUserAndRoleCodeInAndIsDeleted(String uuidUser, Role roleCode, Integer isDeleted);

    Optional<RoleUser> findById(Long id);

    void save(RoleUser role);

    boolean update(RoleUser role, String newRole);

    boolean remove(RoleUser role);

    boolean findAdmin(String roleCode);

    List<Role> findAdminRoleList();
}
