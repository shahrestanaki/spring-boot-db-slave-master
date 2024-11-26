package com.dbtest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERS", schema = "SCHEMA_USERS" )
public class User extends ABaseEntity {

    @Id
    @Column(name = "USER_ID", nullable = false, updatable = false)
    @GeneratedValue(generator = "USER_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "USER_SEQ", allocationSize = 1, sequenceName = "USER_SEQ", schema = "SCHEMA_USERS")
    private Long userId;

    @Basic
    @Column(name = "USER_NAME", nullable = false, length = 50)
    private String userName;

    @Basic
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Basic
    @Column(name = "RESCUE_EMAIL", length = 100)
    private String rescueEmail;

    @Basic
    @Column(name = "RESCUE_CELL_PHONE", length = 20)
    private String rescueCellPhone;

    @Basic
    @Column(name = "LAST_LOGIN")
    private Timestamp lastLogin;

    @Basic
    @Column(name = "LAST_LOGIN_IP", length = 15)
    private String lastLoginIp;

    @Basic
    @Column(name = "CURRENT_LOGIN")
    private Timestamp currentLogin;

    @Basic
    @Column(name = "CURRENT_LOGIN_IP", length = 15)
    private String currentLoginIp;

    @Basic
    @Column(name = "UNSUCCESSFUL_TRIES", nullable = false)
    private Long unsuccessfulTries;

    @Basic
    @Column(name = "UNBLOCK_TIME")
    private Timestamp unBlockTime;


    @Basic
    @Column(name = "PRINCIPLE_ID")
    private Long principleId;

    @Basic
    @Column(name = "FIRST_NAME", length = 50, nullable = false)
    private String firstName;

    @Basic
    @Column(name = "LAST_NAME", length = 50, nullable = false)
    private String lastName;

    @Basic
    @Column(name = "DESCRIPTION")
    private String description;

}
