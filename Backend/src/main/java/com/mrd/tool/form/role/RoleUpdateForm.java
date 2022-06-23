package com.mrd.tool.form.role;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class RoleUpdateForm {
    @NotBlank
    private String roleName;

    @NotBlank
    private String roleCode;
}
