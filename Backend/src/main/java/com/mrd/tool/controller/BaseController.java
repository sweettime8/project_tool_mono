package com.mrd.tool.controller;

import com.mrd.tool.auth.jwt.JwtTokenUtils;
import com.mrd.tool.entity.Role;
import com.mrd.tool.entity.RoleUser;
import com.mrd.tool.service.RoleService;
import com.mrd.tool.service.RoleUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public class BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    /**
     * JWT utils.
     */
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    public void setJwtTokenUtils(final JwtTokenUtils pJwtTokenUtils) {
        jwtTokenUtils = pJwtTokenUtils;
    }

    public JwtTokenUtils getJwtTokenUtils() {
        return jwtTokenUtils;
    }

    public void authenticate(String username, String password){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (Exception e){
            e.getMessage();
            LOGGER.error(e.getMessage());
        }
    }

//    @Autowired
//    private RoleService roleService;
//
    public String getLoggedInUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

//    @Autowired
//    private RoleUserService roleUserService;
//
//    public String authorUserService(String uuid) {
//        String errorMsg = null;
//        List<Role> adminRoleList = roleService.findAdminRoleList();
//        if (adminRoleList == null || adminRoleList.isEmpty()) {
//            errorMsg = "Hệ thống chưa có user admin nào. Vui lòng liên hệ kỹ thuật xử lý để tạo admin";
//        } else {
//            //Check uuid - admin role
//            RoleUser requestRoleUser = roleUserService.findByUuidUserAndRoleCodeInAndIsDeleted(uuid, adminRoleList, 0);
//            if (requestRoleUser == null || (requestRoleUser.getIsDeleted() != null && requestRoleUser.getIsDeleted() == 1)) {
//                errorMsg = "Chỉ có tài khoản admin mới được phép thao tác cho nội dung này";
//            }
//        }
//
//        return errorMsg;
//    }
}
