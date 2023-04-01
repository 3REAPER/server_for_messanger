package com.pervukhin.rest;

import com.pervukhin.domain.Message;
import com.pervukhin.rest.dto.MessageDto;
import com.pervukhin.service.*;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class MessageController {
    private final MessageService messageService;
    private final ProfileService profileService;
    private final ChatService chatService;
    private final ConditionSendService conditionSendService;

    public MessageController() throws SQLException, ClassNotFoundException {
        this.messageService = new MessageServiceImpl();
        this.profileService = new ProfileServiceImpl();
        this.chatService = new ChatServiceImpl();
        this.conditionSendService = new ConditionSendServiceImpl();
    }

    @PostMapping("/message")
    public void createMessage(@RequestBody MessageDto messageDto){

        Message message = MessageDto.toDomainObject(messageDto);
        message.setTime(Message.getDateNow());

        messageService.insert(message, message.getChatId());
    }

    @PostMapping("/message/{id}")
    public void updateMessage(@RequestBody MessageDto messageDto){

        Message message = MessageDto.toDomainObject(messageDto);

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
