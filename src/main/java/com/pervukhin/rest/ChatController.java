package com.pervukhin.rest;

import com.pervukhin.domain.Chat;
import com.pervukhin.domain.GroupChat;
import com.pervukhin.domain.Profile;
import com.pervukhin.rest.dto.ChatDto;
import com.pervukhin.service.*;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class ChatController {
    private final ChatService chatService;


    public ChatController() throws SQLException, ClassNotFoundException {
        this.chatService = new ChatServiceImpl();
    }

    @PostMapping("chat")
    public void insert(@RequestBody ChatDto chatDto){

        Chat chat = ChatDto.toDomainObject(chatDto);

        chatService.insert(chat);
    }

    @PostMapping("chat/{id}")
    public void update(@RequestBody ChatDto chatDto){

        Chat chat = ChatDto.toDomainObject(chatDto);

        chatService.update(chat);
    }

    @DeleteMapping("chat/{id}")
    public void delete(@PathVariable int id){
        chatService.delete(id);
    }

    @DeleteMapping("chat/{chatId}/user/{userId}")
    public void deleteUser(@PathVariable int chatId,
                           @PathVariable int userId){
        Chat chat = chatService.getById(chatId);
        Profile expectedProfile = null;
        for (Profile profile: chat.getUsersId()) {
            if (profile.getId() == userId){
                expectedProfile = profile;
            }
        }
        if (expectedProfile != null){
            chat.getUsersId().remove(expectedProfile);
            chatService.update(chat);
        }
    }

    @GetMapping("chat/{id}")
    public ChatDto getById(@PathVariable int id){
        return ChatDto.toDto(chatService.getById(id));
    }

    @GetMapping("chat/name/{name}")
    public List<ChatDto> getByName(@PathVariable String name){
        return ChatDto.toDto(chatService.getByName(name));
    }

    @GetMapping("chat/user/{id}")
    public List<ChatDto> getAllByUserId(@PathVariable int id){
        return ChatDto.toDto(chatService.getAllByUserId(id));
    }

    @PostMapping("chat/{chatId}/user/{userId}")
    public void addUser(
            @PathVariable int chatId,
            @PathVariable int userId){
        chatService.addUser(chatId,userId);
    }

    @GetMapping("chat/user/{myId}/{userId}")
    public ChatDto getByUsers(@PathVariable int myId, @PathVariable int userId){
        return ChatDto.toDto(chatService.getByUsers(myId, userId));
    }

    @GetMapping("chat/private/{name}")
    public List<ChatDto> getByNameNoPrivate(@PathVariable String name){
        return ChatDto.toDto(chatService.getAllByNameNoPrivate(name));
    }


}