package com.pervukhin.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pervukhin.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private int id;
    private String message;
    private String time;
    private String isEdit;
    private ProfileDto authorId;
    private List<ConditionSendDto> conditionSend;
    private int chatId;
    private boolean isPhoto;
    @JsonIgnore
    private String path;
    private String link;

    public static MessageDto toDto(TextMessage message){
        ProfileDto authorId = ProfileDto.toDto(message.getAuthor());

        List<ConditionSend> conditionSendList = message.getConditionSend();
        List<ConditionSendDto> conditionSendDtoList = new ArrayList<>();

        for (ConditionSend conditionSend: conditionSendList) {
            conditionSendDtoList.add(ConditionSendDto.toDto(conditionSend));
        }

        return new MessageDto(
                message.getId(),
                message.getMessage(),
                message.getTime(),
                message.getIsEdit(),
                authorId,
                conditionSendDtoList,
                message.getChatId(),
                message.getIsPhoto(),
                null,
                null
        );
    }

    public static MessageDto toDto(PhotoMessage message){
        ProfileDto authorId = ProfileDto.toDto(message.getAuthor());

        List<ConditionSend> conditionSendList = message.getConditionSend();
        List<ConditionSendDto> conditionSendDtoList = new ArrayList<>();

        for (ConditionSend conditionSend: conditionSendList) {
            conditionSendDtoList.add(ConditionSendDto.toDto(conditionSend));
        }

        return new MessageDto(
                message.getId(),
                null,
                message.getTime(),
                null,
                authorId,
                conditionSendDtoList,
                message.getChatId(),
                message.getIsPhoto(),
                message.getPath(),
                LinkBuilder.createLink(message.getPath())
        );
    }

    public static Message toDomainObject(MessageDto messageDto){
        Profile authorId = ProfileDto.toDomainObject(messageDto.authorId);

        List<ConditionSendDto> conditionSendDtoList = messageDto.getConditionSend();
        List<ConditionSend> conditionSendList = new ArrayList<>();

        for (ConditionSendDto conditionSendDto: conditionSendDtoList) {
            conditionSendList.add(ConditionSendDto.toDomainObject(conditionSendDto));
        }
        if (messageDto.isPhoto){
            return new PhotoMessage(
                    messageDto.getId(),
                    messageDto.getTime(),
                    authorId,
                    messageDto.getChatId(),
                    conditionSendList,
                    messageDto.isPhoto,
                    messageDto.getPath()
            );
        }else {
            return new TextMessage(
                    messageDto.getId(),
                    messageDto.getMessage(),
                    messageDto.getTime(),
                    messageDto.getIsEdit(),
                    authorId,
                    conditionSendList,
                    messageDto.getChatId(),
                    messageDto.isPhoto

            );
        }
    }

    public static List<MessageDto> toDto(List<Message> list) {
        List<MessageDto> result = new ArrayList<>();
        if (list != null) {
            for (Message message : list) {
                if (message.getIsPhoto()){
                    result.add(toDto(((PhotoMessage) message)));
                }else {
                    result.add(toDto(((TextMessage) message)));
                }
            }
        }
        return result;
    }

    public static List<Message> toDomainObject(List<MessageDto> list){
        List<Message> result = new ArrayList<>();
        if (list != null) {
            for (MessageDto messageDto : list) {
                result.add(toDomainObject(messageDto));
            }
        }
        return result;
    }
}
