package com.pervukhin.rest;

import com.pervukhin.domain.Chat;
import com.pervukhin.domain.Message;
import com.pervukhin.service.*;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class MessageController {
    private final MessageService messageService;
    private final ProfileService profileService;
    private final ChatService chatService;

    public MessageController() throws SQLException, ClassNotFoundException {
        this.messageService = new MessageServiceImpl();
        this.profileService = new ProfileServiceImpl();
        this.chatService = new ChatServiceImpl();
    }

    @PostMapping("/message")
    public void createMessage(@RequestParam String messageStr,
                              @RequestParam String time,
                              @RequestParam String isEdit,
                              @RequestParam int authorId,
                              @RequestParam int conditionSend,
                              @RequestParam int chatId){


        Message message = new Message(
                messageStr,
                time,
                isEdit,
                profileService.getById(authorId),
                conditionSend,
                chatId
        );

        messageService.insert(message, chatId);
    }

    @PostMapping("/message/{id}")
    public void updateMessage(
            @PathVariable int id ,
            @RequestParam String messageStr,
            @RequestParam String time,
            @RequestParam String isEdit,
            @RequestParam int authorId,
            @RequestParam int conditionSend,
            @RequestParam int chatId){

        Message message = new Message(
                id,
                messageStr,
                time,
                isEdit,
                profileService.getById(authorId),
                conditionSend,
                chatId
        );

        messageService.update(message);
    }

    @DeleteMapping("/message/{id}")
    public void delete(@PathVariable int id) throws SQLException, ClassNotFoundException {
        messageService.delete(id);
    }

    @GetMapping("/message/{id}")
    public Message getById(@PathVariable int id) throws SQLException, ClassNotFoundException {
        return messageService.getById(id);
    }

    @GetMapping("/message/chat/{id}")
    public List<Message> getAllByChatId(@PathVariable int id) throws SQLException, ClassNotFoundException {
        return messageService.getAllByChatId(id);
    }

    @GetMapping("/message")
    public List<Message> getAll(){
        return messageService.getAll();
    }

    @GetMapping("/message/unread/{profileId}")
    public List<Message> getUnread(@PathVariable int profileId){
        return messageService.getUnread(profileId);
    }
}
