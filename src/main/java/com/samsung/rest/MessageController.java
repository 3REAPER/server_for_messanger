package com.samsung.rest;

import com.samsung.domain.Message;
import com.samsung.domain.Profile;
import com.samsung.service.MessageService;
import com.samsung.service.MessageServiceImpl;
import com.samsung.service.ProfileService;
import com.samsung.service.ProfileServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class MessageController {
    private final MessageService messageService;
    private final ProfileService profileService;

    public MessageController() throws SQLException, ClassNotFoundException {
        this.messageService = new MessageServiceImpl();
        this.profileService = new ProfileServiceImpl();
    }

    @PostMapping("/message")
    public void createProfile(@RequestParam String messageStr,
                              @RequestParam String time,
                              @RequestParam int isEdit,
                              @RequestParam int chatId,
                              @RequestParam int authorId) throws SQLException, ClassNotFoundException {

        Message message = new Message(
                messageStr,
                time,
                isEdit,
                chatId,
                profileService.getById(authorId)
        );

        messageService.insert(message);
    }

    @PostMapping("/message/{id}")
    public void updateProfile(
            @PathVariable int id ,
            @RequestParam String messageStr,
            @RequestParam String time,
            @RequestParam int isEdit,
            @RequestParam int chatId,
            @RequestParam int authorId) throws SQLException, ClassNotFoundException {

        Message message = new Message(
                id,
                messageStr,
                time,
                isEdit,
                chatId,
                profileService.getById(authorId)
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
    public List<Message> getAll() throws SQLException, ClassNotFoundException {
        return messageService.getAll();
    }
}
