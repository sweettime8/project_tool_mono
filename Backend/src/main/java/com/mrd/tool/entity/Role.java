package com.mrd.tool.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_role")
public class Role extends BaseEntity {

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_code")
    private String roleCode;

    @Column(name = "is_admin")
    private Integer isAdmin;

    public Role(String roleName, String roleCode) {
        this.roleName = roleName;
        this.roleCode = roleCode;
    }
}
