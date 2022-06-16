package com.mrd.tool.controller;

import com.mrd.tool.common.message.MessageContent;
import com.mrd.tool.common.message.ResponseMessage;
import com.mrd.tool.entity.Role;
import com.mrd.tool.service.RoleService;
import com.mrd.tool.validate.RoleValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;
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
    public ResponseMessage createRole(final @Valid @RequestBody Map<String, Object> bodyParam) {
        try {
            String roleCode = (String) bodyParam.get("roleCode");
            String roleName = (String) bodyParam.get("roleName");

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
                }else {
                    role.setIsAdmin(0);
                    role.setCreatedBy(username);
                    role.setIsDeleted(0);
                    role.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                    roleService.save(role);
                    return new ResponseMessage(HttpStatus.CREATED.value(), HttpStatus.CREATED.toString(),
                            new MessageContent(role));
                }

            }
        }catch (Exception e){
            LOGGER.error("Error to save role >>> " + e.toString());
            return new ResponseMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                    new MessageContent(HttpStatus.INTERNAL_SERVER_ERROR.toString()));

        }
    }

    @PutMapping
    public ResponseMessage updateRole(final @Valid @RequestBody Map<String, Object> bodyParam) {
        try {
            String roleCode = (String) bodyParam.get("roleCode");
            String roleName = (String) bodyParam.get("roleName");

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
                            new MessageContent( "Không tồn tại Role với roleCode " + roleCode));
                }else {
                    try {

                        if (roleService.update(role)) {
                            return new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                                    new MessageContent(bodyParam));
                        } else {
                            return new ResponseMessage(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(),
                                    new MessageContent(bodyParam));
                        }
                    } catch (Exception ex) {
                        LOGGER.error("Error to update role >>> " + ex.toString());
                        ex.printStackTrace();

                        return new ResponseMessage(HttpStatus.NOT_MODIFIED.value(), HttpStatus.NOT_MODIFIED.getReasonPhrase(),
                                new MessageContent(HttpStatus.NOT_MODIFIED.getReasonPhrase()));

                    }
                }

            }
        }catch (Exception e){
            LOGGER.error("Error to save role >>> " + e.toString());
            return new ResponseMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                    new MessageContent(HttpStatus.INTERNAL_SERVER_ERROR.toString()));

        }
    }
}
