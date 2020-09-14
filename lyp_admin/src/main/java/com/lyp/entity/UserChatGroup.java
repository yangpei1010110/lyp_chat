package com.lyp.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class UserChatGroup {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private Date createDate;//创建时间
    @OneToOne
    private User createUser;//创建者
    @Column
    private Date modifyDate;//修改时间
    @OneToOne
    private User modifyUser;//修改者
}
