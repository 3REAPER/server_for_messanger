package com.pervukhin.rest.dto;

import com.pervukhin.domain.ConditionSend;
import com.pervukhin.domain.Message;
import com.pervukhin.domain.Profile;
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

    public static MessageDto toDto(Message message){
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
                message.getChatId()
        );
    }

    public static Message toDomainObject(MessageDto messageDto){
        Profile authorId = ProfileDto.toDomainObject(messageDto.authorId);

        List<ConditionSendDto> conditionSendDtoList = messageDto.getConditionSend();
        List<ConditionSend> conditionSendList = new ArrayList<>();

        for (ConditionSendDto conditionSendDto: conditionSendDtoList) {
            conditionSendList.add(ConditionSendDto.toDomainObject(conditionSendDto));
        }

        return new Message(
                messageDto.getId(),
                messageDto.getMessage(),
                messageDto.getTime(),
                messageDto.getIsEdit(),
                authorId,
                conditionSendList,
                messageDto.getChatId()
        );
    }

    public static List<MessageDto> toDto(List<Message> list){
        List<MessageDto> result = new ArrayList<>();
        for (Message message: list) {
            result.add(toDto(message));
        }
        return result;
    }

    public static List<Message> toDomainObject(List<MessageDto> list){
        List<Message> result = new ArrayList<>();
        for (MessageDto messageDto: list) {
            result.add(toDomainObject(messageDto));
        }
        return result;
    }
}
