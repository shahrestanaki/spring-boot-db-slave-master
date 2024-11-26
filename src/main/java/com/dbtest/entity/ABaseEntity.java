package com.dbtest.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Version;

import java.sql.Timestamp;

/**
 * @author m.Shahrestanaki @createDate 11/25/2024
 */


@MappedSuperclass
@Setter
@Getter
public abstract class ABaseEntity {
    @Basic
    @Column(
            name = "CREATION_DATE",
            nullable = false
    )
    private Timestamp creationDate;
    @Basic
    @Column(
            name = "CREATOR_USER_ID",
            nullable = false
    )
    private Long creatorUserId;
    @Basic
    @Version
    @Column(
            name = "LAST_UPDATE"
    )
    private Timestamp lastUpdate;
    @Basic
    @Column(
            name = "UPDATER_USER_ID",
            insertable = false
    )
    private Long updaterUserId;

}
