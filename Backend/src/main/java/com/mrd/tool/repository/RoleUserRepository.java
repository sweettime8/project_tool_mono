package com.mrd.tool.repository;

import com.mrd.tool.entity.RoleUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleUserRepository extends CrudRepository<RoleUser, Long> {

    List<RoleUser> findByUsernameAndAndIsDeleted(String uuidUser, Integer isDelete);
}
