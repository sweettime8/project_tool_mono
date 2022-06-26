//package com.mrd.tool.controller;
//
//import com.mrd.tool.auth.CustomUserDetails;
//import com.mrd.tool.common.message.MessageContent;
//import com.mrd.tool.common.message.ResponseMessage;
//import com.mrd.tool.constants.CommonConstants;
//import com.mrd.tool.entity.RoleUser;
//import com.mrd.tool.entity.User;
//import com.mrd.tool.entity.dto.AuthorizationResponseDTO;
//import com.mrd.tool.service.AuthService;
//import com.mrd.tool.service.RoleUserService;
//import com.mrd.tool.service.UserService;
//import com.mrd.tool.validate.UserValidation;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.validation.Valid;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/authen")
//public class AuthenController extends BaseController {
//    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenController.class);
//    @Autowired
//    private JwtTokenProvider tokenProvider;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private AuthService authService;
//
//    @Autowired
//    private RoleUserService roleUserService;
//
//    @PostMapping("/login")
//    public ResponseMessage userLogin(final @Valid @RequestBody Map<String,Object> bodyParam) {
//        ResponseMessage response = null;
//        try{
//            if (bodyParam == null || bodyParam.isEmpty()) {
//                response = new ResponseMessage(HttpStatus.BAD_REQUEST.value(), CommonConstants.VALIDATION_INVALID_PARAM_VALUE,
//                        new MessageContent(null));
//            }else {
//                String username = bodyParam.get("username").toString();
//                String password = bodyParam.get("password").toString();
//
//                String invalidData = new UserValidation().validateLogin(username, password);
//                if (invalidData != null) {
//                    response = new ResponseMessage(HttpStatus.BAD_REQUEST.value(), invalidData,
//                            new MessageContent(null));
//                }else {
//                    // Check exist user with username
//                    User existUser = userService.findByUserName(username);
//                    if (existUser == null) {
//                        invalidData = "username không tồn tại";
//                        return new ResponseMessage(HttpStatus.NOT_FOUND.value(), invalidData,
//                                new MessageContent(null));
//                    }else{
//                        // Xác thực thông tin người dùng Request lên, nếu không xảy ra exception tức là thông tin hợp lệ
//                        Authentication authentication = null;
//                        try {
//                            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//                        } catch (AuthenticationException ex) {
//                            LOGGER.error(ex.toString());
//                            invalidData = "username hoặc mật khẩu không đúng";
//                            return new ResponseMessage(HttpStatus.UNAUTHORIZED.value(), invalidData,
//                                    new MessageContent(null));
//                        }
//
//                        // Set thông tin authentication vào Security Context
//                        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
//                        if (userDetails.getUser().getStatus() == User.STATUS_LOCK) {
//                            response = new ResponseMessage(HttpStatus.UNAUTHORIZED.value(), CommonConstants.VALIDATION_ACCOUNT_LOCKED, new MessageContent(null));
//                        }else {
//                            SecurityContextHolder.getContext().setAuthentication(authentication);
//                            // Trả về jwt cho người dùng.
//                            String accessJwt = tokenProvider.generateToken(userDetails);
//                            String refreshJwt = tokenProvider.createToken(userDetails.getUser().getUuid());
//
//                            List<RoleUser> roleUser = roleUserService.findByUuidUser(userDetails.getUser().getUserName());
//                            List<String> roles = new ArrayList<>();
//                            for(RoleUser r : roleUser){
//                                roles.add(r.getRolecode());
//                            }
//                            AuthorizationResponseDTO responseDTO = new AuthorizationResponseDTO(userDetails, accessJwt, refreshJwt,roles);
//
//                            response = new ResponseMessage(new MessageContent(responseDTO));
//                        }
//                    }
//                }
//            }
//        }catch(Exception ex){
//            LOGGER.error(ex.toString());
//            return new ResponseMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonConstants.INTERNAL_SERVER_ERROR,
//                    new MessageContent(null));
//        }
//
//        return response;
//    }
//}
