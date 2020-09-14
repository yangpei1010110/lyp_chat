package com.lyp.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class ChatGroup {//可以是多人聊天群 也可以是1对1聊天群 一个聊天群有一个消息队列

    @Id
    @GeneratedValue
    private Long id;//id
    @ManyToMany
    private List<User> userList;//群内所拥有的用户
    @OneToMany
    private List<ChatMessage> chatMessageList;//消息队列 一对多 应按照发送时间倒排
    @Column
    private Date createDate;//创建时间
    @OneToOne
    private User createUser;//创建者
    @Column
    private Date modifyDate;//修改时间
    @OneToOne
    private User modifyUser;//修改者
}
