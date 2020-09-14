package com.lyp.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;//自增id
    @Column
    private String username;//用户登录名
    @Column
    private String password;//用户密码(MD5加密)
    @Column
    private Integer status;//0正常访问 1限制访问（仅允许访问） 2封禁（禁止登陆）
    @ManyToMany
    private List<UserRole> userRoleList;//角色表 多对多
    @ManyToMany
    private List<ChatGroup> chatGroupList;//用户拥有的所有群
    @Column
    private String telephone;//手机号
    @Column
    private Integer sex;//性别 无0 男1 女2
    @Column
    private Date createDate;//创建时间
    @OneToOne
    private User createUser;//创建者
    @Column
    private Date modifyDate;//修改时间
    @OneToOne
    private User modifyUser;//修改者
    @Column
    private Date lastOnlineDate;//最后在线时间
    @Column
    private Date lastLoginDate;//最后一次登录时间

}
