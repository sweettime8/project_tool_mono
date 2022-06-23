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
@Table(name = "t_user_role")
public class RoleUser extends BaseEntity {

    @Column(name = "username")
    private String username;

    @Column(name = "roleCode")
    private String rolecode;


}
