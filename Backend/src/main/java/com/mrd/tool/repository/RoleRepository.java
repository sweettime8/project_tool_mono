package com.mrd.tool.repository;

import com.mrd.tool.entity.Role;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByRoleCode(String roleCode);

    List<Role> findByCreatedByAndIsDeleted(String username, Integer isDeleted);

    Role findByRoleCodeOrRoleName(String roleCode, String roleName);

    List<Role> findByIsDeletedNot(Integer isDelete);

    @Transactional()
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Role r SET r.roleName = :roleName WHERE r.roleCode = :roleCode")
    int updateRole(@Param("roleCode") String roleCode, @Param("roleName") String roleName);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("DELETE Role r WHERE r.roleCode = :roleCode")
    int deleteRole(@Param("roleCode") String roleCode);
}
