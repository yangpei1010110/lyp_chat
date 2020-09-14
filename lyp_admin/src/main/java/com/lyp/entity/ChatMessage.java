package com.lyp.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class ChatMessage {//单条消息
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private ChatMessage message;//消息 可以为文本，图片，音乐或视频的URL
    @Column
    private Long userMessageId;//消息发送者的id
    @Column
    private Timestamp createDate;//创建时间
    @OneToOne
    private User createUser;//创建者
    @Column
    private Timestamp modifyDate;//修改时间
    @OneToOne
    private User modifyUser;//修改者
}
