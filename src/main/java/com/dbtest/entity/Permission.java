package com.dbtest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PERMISSION", schema = "USER_MGMT")
public class Permission extends ABaseEntity {
    @Id
    @Column(name = "PERMISSION_ID", nullable = false, insertable = false, updatable = false)
    @GeneratedValue(generator = "PERMISSION_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "PERMISSION_SEQ", allocationSize = 1, sequenceName = "PERMISSION_SEQ", schema = "USER_MGMT")
    private Long permissionId;

    @Basic
    @Column(name = "NAME", nullable = false, length = 100)
    private String name;
    @Basic
    @Column(name = "FARSI_NAME", nullable = false, length = 100)
    private String farsiName;
    @Basic
    @Column(name = "KEY", nullable = false, length = 100)
    private String key;
    @Basic
    @Column(name = "IS_ACTIVE")
    private boolean isActive;
    @Basic
    @Column(name = "PARENT_PERMISSION_ID", updatable = false)
    private Long parentPermissionId;

}
