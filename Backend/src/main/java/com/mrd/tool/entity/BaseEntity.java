package com.mrd.tool.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

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
}
