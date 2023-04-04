package com.pervukhin.rest.dto;

import com.pervukhin.domain.Chat;
import com.pervukhin.domain.GroupChat;
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
public class ChatDto {
    private int id;
    private List<ProfileDto> usersId;
    private List<MessageDto> messages;
    private Boolean isGroup;
    private String name;
    private String description;
    private Boolean isPrivate;
    private Profile admin;

    public static ChatDto toDto(Chat chat){
        List<ProfileDto> userList = ProfileDto.toDto(chat.getUsersId());
        List<MessageDto> messageList = MessageDto.toDto(chat.getMessages());

        if (chat instanceof GroupChat){
            GroupChat groupChat = ((GroupChat) chat);
            return new ChatDto(groupChat.getId() ,userList ,messageList, groupChat.getIsGroup(), groupChat.getName(), groupChat.getDescription(), groupChat.getIsPrivate(), groupChat.getAdmin());
        }else {
            return new ChatDto(chat.getId(), userList, messageList, chat.getIsGroup(), null, null,null,null);
        }
    }

    public static Chat toDomainObject(ChatDto chatDto) {
        try {
            List<Profile> userList = ProfileDto.toDomainObject(chatDto.getUsersId());
            List<Message> messageList = MessageDto.toDomainObject(chatDto.getMessages());

            if (chatDto.isGroup) {
                return new GroupChat(chatDto.getId(), userList, messageList, chatDto.getIsGroup(), chatDto.getName(), chatDto.getDescription(), chatDto.getIsPrivate(), chatDto.getAdmin());
            } else {
                return new Chat(chatDto.getId(), userList, messageList, chatDto.getIsGroup());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<ChatDto> toDto(List<Chat> list){
        List<ChatDto> result = new ArrayList<>();
        if (list != null) {
            for (Chat chat : list) {
                result.add(toDto(chat));
            }
        }
        return result;
    }

    public static List<Chat> toDomainObject(List<ChatDto> list){
        List<Chat> result = new ArrayList<>();
        if (list != null) {
            for (ChatDto chat : list) {
                result.add(toDomainObject(chat));
            }
        }
        return result;
    }

}
