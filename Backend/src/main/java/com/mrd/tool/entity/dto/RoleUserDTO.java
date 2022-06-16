package com.mrd.tool.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleUserDTO {
    private Long id;
    private String uuidUser;
    private String roleCode;
}
