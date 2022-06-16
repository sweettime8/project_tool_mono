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
@Table(name = "t_role_object_permission")
public class RoleObjectPermission extends BaseEntity {

    @Column(name = "role_code")
    private String roleCode;

    @Column(name = "object_code")
    private String objectCode;

    @Column(name = "can_create")
    private Integer canCreate;

    @Column(name = "can_view")
    private Integer canView;

    @Column(name = "can_update")
    private Integer canUpdate;

    @Column(name = "can_delete")
    private Integer canDelete;
}
