package com.mrd.tool.validate;

import com.mrd.tool.common.ultils.StringUtil;
import com.mrd.tool.entity.Role;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;

@Service
public class RoleValidation extends BaseValidation{
    public String validateRole(Role role, String actionType) throws ValidationException {
        if (role == null) {
            return "payLoad không hợp lệ";
        }

        if (StringUtil.isNullOrEmpty(role.getRoleCode())) {
            return "roleCode không được để trống";
        }

        if ("INSERT".equals(actionType) && StringUtil.isNullOrEmpty(role.getRoleName())) {
            return "roleName không được để trống";
        }

        /**/
        return !isValid() ? this.buildValidationMessage() : null;
    }
}
