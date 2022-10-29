package com.pervukhin.rest;

import com.pervukhin.domain.Chat;
import com.pervukhin.domain.Profile;
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
    public void insert(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam int admin,
            @RequestParam String usersId,
            @RequestParam String isPrivate,
            @RequestParam String messages) throws SQLException, ClassNotFoundException {

        Chat chat = new Chat(
                name,
                description,
                Chat.parseIntToAdmin(admin),
                usersId,
                isPrivate,
                messages
        );

        chatService.insert(chat);
    }

    @PostMapping("chat/{id}")
    public void update(
            @PathVariable int id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam int admin,
            @RequestParam String usersId,
            @RequestParam String isPrivate,
            @RequestParam String messages) throws SQLException, ClassNotFoundException {

        Chat chat = new Chat(
                id,
                name,
                description,
                admin,
                usersId,
                isPrivate,
                messages
        );

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
    public Chat getById(@PathVariable int id){
        return chatService.getById(id);
    }

    @GetMapping("chat/name/{name}")
    public List<Chat> getByName(@PathVariable String name){
        return chatService.getByName(name);
    }

    @GetMapping("chat/user/{id}")
    public List<Chat> getAllByUserId(@PathVariable int id){
        return chatService.getAllByUserId(id);
    }

    @PostMapping("chat/{chatId}/user/{userId}")
    public void addUser(
            @PathVariable int chatId,
            @PathVariable int userId){
        chatService.addUser(chatId,userId);
    }
}