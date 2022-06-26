package com.mrd.tool.controller;

import com.mrd.tool.auth.CustomUserDetails;
import com.mrd.tool.common.message.MessageContent;
import com.mrd.tool.common.message.ResponseMessage;
import com.mrd.tool.constants.CommonConstants;
import com.mrd.tool.entity.RoleUser;
import com.mrd.tool.entity.dto.AuthorizationResponseDTO;
import com.mrd.tool.service.RoleUserService;
import com.mrd.tool.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/authen")
public class ProcessLoginController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessLoginController.class);
    @Autowired
    private SecurityService securityService;

    @Autowired
    private RoleUserService roleUserService;

    @PostMapping(value = "/login")
    public ResponseMessage userLogin(final @Valid @RequestBody Map<String, Object> bodyParam) {
        ResponseMessage response = null;
        try {
            if (bodyParam == null || bodyParam.isEmpty()) {
                return new ResponseMessage(HttpStatus.BAD_REQUEST.value(), false, CommonConstants.VALIDATION_INVALID_PARAM_VALUE,
                        new MessageContent(null));
            } else {
                String username = bodyParam.get("username").toString();
                String password = bodyParam.get("password").toString();
                authenticate(username, password);

                CustomUserDetails userDetails = securityService.loadUserByUsername(username);
                String token = super.getJwtTokenUtils().generateToken(userDetails.getUsername());

                List<RoleUser> roleUser = roleUserService.findByUuidUser(userDetails.getUser().getUserName());
                List<String> roles = new ArrayList<>();
                for (RoleUser r : roleUser) {
                    roles.add(r.getRolecode());
                }
                AuthorizationResponseDTO responseDTO = new AuthorizationResponseDTO(userDetails, token, token, roles);
                response = new ResponseMessage(new MessageContent(responseDTO));
                return response;
            }

        } catch (Exception e) {
            e.getMessage();
            LOGGER.error(e.toString());
            return new ResponseMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),false, CommonConstants.INTERNAL_SERVER_ERROR,
                    new MessageContent(null));
        }

    }

}
