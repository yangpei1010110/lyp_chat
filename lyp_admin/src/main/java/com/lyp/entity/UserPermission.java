package com.lyp.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
public class UserPermission {
    @Id
    @GeneratedValue
    private Long id;//id
    @Column
    private String permissionName;//权限名
    @ManyToMany
    private List<UserRole> userRoleList;//角色表 多对多
    @Column
    private Timestamp createDate;//创建时间
    @OneToOne
    private User createUser;//创建者
    @Column
    private Timestamp modifyDate;//修改时间
    @OneToOne
    private User modifyUser;//修改者
}
