package com.samsung.rest;

import com.samsung.domain.Chat;
import com.samsung.service.*;
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
            @RequestParam String usersId,
            @RequestParam String isPrivate) throws SQLException, ClassNotFoundException {

        Chat chat = new Chat(
                name,
                usersId,
                isPrivate
        );

        chatService.insert(chat);
    }

    @PostMapping("chat/{id}")
    public void update(
            @PathVariable int id,
            @RequestParam String name,
            @RequestParam String usersId,
            @RequestParam String isPrivate) throws SQLException, ClassNotFoundException {

        Chat chat = new Chat(
                id,
                name,
                usersId,
                isPrivate
        );

        chatService.update(chat);
    }

    @DeleteMapping("chat/{id}")
    public void delete(@PathVariable int id){
        chatService.delete(id);
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