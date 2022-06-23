package com.mrd.tool.controller;

import com.mrd.tool.common.message.MessageContent;
import com.mrd.tool.common.message.ResponseMessage;
import com.mrd.tool.entity.Role;
import com.mrd.tool.entity.RoleObjectPermission;
import com.mrd.tool.form.role.RoleCreateForm;
import com.mrd.tool.form.role.RoleUpdateForm;
import com.mrd.tool.service.RoleService;
import com.mrd.tool.validate.RoleValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/role")
public class RoleController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);
    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleValidation roleValidation;

    @PostMapping
    public ResponseMessage createRole(final @Valid @RequestBody RoleCreateForm form) {
        try {
            String roleCode = form.getRoleCode();
            String roleName = form.getRoleName();

            Role role = new Role(roleCode, roleName);
            String invalidData = roleValidation.validateRole(role, "INSERT");
            if (invalidData != null) {
                ResponseMessage responseMessage = null;
                responseMessage = new ResponseMessage(HttpStatus.BAD_REQUEST.value(), invalidData,
                        new MessageContent(null));
                return responseMessage;
            } else {
                String username = super.getLoggedInUsername();
                Role existRole = roleService.findByRoleCode(roleCode);
                if (existRole != null) {
                    return new ResponseMessage(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.getReasonPhrase(),
                            new MessageContent("Role đã tồn tại"));
                } else {
                    Role roleCreate = roleService.createRole(form, username);
                    return new ResponseMessage(HttpStatus.CREATED.value(), HttpStatus.CREATED.toString(),
                            new MessageContent(roleCreate));
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error to save role >>> " + e.toString());
            return new ResponseMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                    new MessageContent(HttpStatus.INTERNAL_SERVER_ERROR.toString()));

        }
    }

    @PutMapping
    public ResponseMessage updateRole(final @Valid @RequestBody RoleUpdateForm form) {
        try {
            String roleCode = form.getRoleCode();
            String roleName = form.getRoleName();

            Role role = new Role(roleCode, roleName);
            String invalidData = roleValidation.validateRole(role, "UPDATE");
            if (invalidData != null) {
                return new ResponseMessage(HttpStatus.BAD_REQUEST.value(), invalidData,
                        new MessageContent(null));
            } else {
                //Role exist
                String username = super.getLoggedInUsername();
                Role existRole = roleService.findByRoleCode(roleCode);
                if (existRole == null || (existRole.getIsDeleted() != null && existRole.getIsDeleted() == 1)) {
                    return new ResponseMessage(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(),
                            new MessageContent("Không tồn tại Role với roleCode " + roleCode));
                } else {
                    try {
                        Role updateRole = roleService.updateByRoleCode(form, username);
                        if (updateRole != null) {
                            return new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                                    new MessageContent(updateRole));
                        } else {
                            return new ResponseMessage(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(),
                                    new MessageContent(form));
                        }
                    } catch (Exception ex) {
                        LOGGER.error("Error to update role >>> " + ex.toString());
                        ex.printStackTrace();

                        return new ResponseMessage(HttpStatus.NOT_MODIFIED.value(), HttpStatus.NOT_MODIFIED.getReasonPhrase(),
                                new MessageContent(HttpStatus.NOT_MODIFIED.getReasonPhrase()));

                    }
                }

            }
        } catch (Exception e) {
            LOGGER.error("Error to save role >>> " + e.toString());
            return new ResponseMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                    new MessageContent(HttpStatus.INTERNAL_SERVER_ERROR.toString()));

        }
    }

    @GetMapping("/get-role-create-by-user")
    public ResponseMessage findRoleCreateByUserName() {
        String username = super.getLoggedInUsername();
        if (username == null || username.isEmpty()) {
            return new ResponseMessage(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(),
                    new MessageContent(null));
        } else {
            List<Role> roleList = roleService.findRoleCreateByUsername(username);
            if (roleList == null || roleList.size() == 0) {
                return new ResponseMessage(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(),
                        new MessageContent(null));
            }
            return new  ResponseMessage(new MessageContent(roleList));
        }
    }

//    @GetMapping("/list-object-of-role")
//    public ResponseMessage getListObjectOfRole(@RequestParam("roleCode") String roleCode) {
//        ResponseMessage response = null;
//
//        List<RoleObjectPermission> pageList = rolePagePermissionService.findByRoleCode(roleCode);
//        if (pageList != null && !pageList.isEmpty()) {
//            response = new ResponseMessage(new MessageContent(pageList));
//        } else {
//            response = new ResponseMessage(HttpStatus.OK.value(), Constant.VALIDATION_DATA_NOT_FOUND,
//                    new MessageContent(HttpStatus.OK.value(), Constant.VALIDATION_DATA_NOT_FOUND,
//                            "Không tìm thấy Page ứng với roleCode " + roleCode));
//        }
//
//        return response;
//    }
}
