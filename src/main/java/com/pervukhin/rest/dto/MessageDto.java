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
        ProfileDto author = ProfileDto.toDto(message.getAuthor());

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
                author,
                conditionSendDtoList,
                message.getChatId()
        );
    }

    public static Message toDomainObject(MessageDto messageDto){
        Profile author = ProfileDto.toDomainObject(messageDto.authorId);

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
                author,
                conditionSendList,
                messageDto.getChatId()
        );
    }
}
