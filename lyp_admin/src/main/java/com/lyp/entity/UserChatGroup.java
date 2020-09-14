package com.lyp.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class UserChatGroup {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private Timestamp createDate;//创建时间
    @OneToOne
    private User createUser;//创建者
    @Column
    private Timestamp modifyDate;//修改时间
    @OneToOne
    private User modifyUser;//修改者
}
