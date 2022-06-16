package com.mrd.tool.entity.dto;

import com.mrd.tool.auth.CustomUserDetails;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class AuthorizationResponseDTO {
    private String accessToken;
    private String refreshToken;
    private String uuid;
    private String username;
    private String email;
    private String mobile;
    private String fullName;
    private Integer status;
    private String avatar;
    private String address;
    private String birthDay;
    private Integer gender;
    private String description;
    private String createdBy;
    private String modifyBy;
    private Timestamp createdAt;
    private Timestamp modifyAt;
    private Integer isDelete;
    private List<String> roles;

    public AuthorizationResponseDTO(CustomUserDetails userDetails, String accessToken, String refreshToken, List<String> roles) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.uuid = userDetails.getUser().getUuid();
        this.username = userDetails.getUser().getUserName();
        this.email = userDetails.getUser().getEmail();
        this.mobile = userDetails.getUser().getMobile();
        this.fullName = userDetails.getUser().getFullName();
        this.status = userDetails.getUser().getStatus();
        this.avatar = userDetails.getUser().getAvatar();
        this.address = userDetails.getUser().getAddress();
        this.birthDay = userDetails.getUser().getBirthDay();
        this.gender = userDetails.getUser().getGender();
        this.description = userDetails.getUser().getDescription();
        this.createdBy = userDetails.getUser().getCreatedBy();
        this.modifyBy = userDetails.getUser().getModifyBy();
        this.createdAt = userDetails.getUser().getCreatedAt();
        this.modifyAt = userDetails.getUser().getModifyAt();
        this.isDelete = userDetails.getUser().getIsDeleted();
        this.roles = roles;
    }

}
