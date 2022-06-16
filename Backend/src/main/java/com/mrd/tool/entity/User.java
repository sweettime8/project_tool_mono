package com.mrd.tool.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "uuid")
    private String uuid;

    @Size(max = 100)
    @Column(name = "username")
    private String userName;

    @Size(max = 255)
    @Column(name = "email")
    private String email;

    @Size(max = 20)
    @Column(name = "mobile")
    private String mobile;

    @Size(max = 255)
    @Column(name = "fullname")
    private String fullName;

    @Size(max = 255)
    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Column(name = "status")
    private Integer status;

    @Size(max = 1000)
    @Column(name = "avatar")
    private String avatar;

    @Size(max = 255)
    @Column(name = "address")
    private String address;

    @Size(max = 255)
    @Column(name = "birthday")
    private String birthDay;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "unit_name")
    private String unitName;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "position_name")
    private String positionName;

    @Column(name = "description")
    private String description;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modify_by")
    private String modifyBy;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "modify_at")
    private Timestamp modifyAt;

    @Column(name = "last_update")
    private Timestamp lastUpdate;

    @Column(name = "is_deleted")
    private Integer isDeleted;

    @JsonIgnore
    public static final Integer STATUS_ACTIVE = 1;
    @JsonIgnore
    public static final Integer STATUS_LOCK = -1;

    @PrePersist
    void preInsert() {
        if (this.getCreatedAt() == null) {
            this.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        }
    }
}
