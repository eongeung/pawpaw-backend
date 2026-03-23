package com.pawpaw.pawpaw.domain.chat.dto;

import com.pawpaw.pawpaw.domain.chat.entity.Message;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MessageResponseDto {

    private Long id;
    private Long roomId;
    private String senderNickname;
    private String content;
    private Boolean isRead;
    private LocalDateTime createdAt;

    public MessageResponseDto(Message message) {
        this.id = message.getId();
        this.roomId = message.getChatRoom().getId();
        this.senderNickname = message.getSender().getNickname();
        this.content = message.getContent();
        this.isRead = message.getIsRead();
        this.createdAt = message.getCreatedAt();
    }
}
