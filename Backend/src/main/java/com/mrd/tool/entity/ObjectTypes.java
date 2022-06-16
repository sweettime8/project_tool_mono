package com.mrd.tool.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_object_types")
public class ObjectTypes extends BaseEntity {

    @Column(name = "object_name")
    private String objectName;

    @Column(name = "object_code")
    private String objectCode;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "group_key")
    private String groupKey;


}
