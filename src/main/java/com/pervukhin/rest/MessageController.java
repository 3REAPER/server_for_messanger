package com.pervukhin.rest;

import com.pervukhin.domain.Message;
import com.pervukhin.domain.Profile;
import com.pervukhin.rest.dto.MessageDto;
import com.pervukhin.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.List;

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
    public void createMessage(@RequestBody MessageDto messageDto, @RequestParam String login, @RequestParam String password){
        if (messageDto.getAuthorId().getLogin().equals(login) && profileService.isRightPasswordAndLogin(login, password).getResult().equals("true")) {
            Message message = MessageDto.toDomainObject(messageDto);
            message.setTime(Message.getDateNow());

            messageService.insert(message, message.getChatId());
        }else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/message/{id}")
    public void updateMessage(@RequestBody MessageDto messageDto, @RequestParam String login, @RequestParam String password){
        if (messageDto.getAuthorId().getLogin().equals(login) && profileService.isRightPasswordAndLogin(login, password).getResult().equals("true")) {
            Message message = MessageDto.toDomainObject(messageDto);
            messageService.update(message);
        }else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/message/{id}")
    public void delete(@PathVariable int id,  @RequestParam String login, @RequestParam String password) {
        if (messageService.getById(id).getAuthor().getLogin().equals(login) && profileService.isRightPasswordAndLogin(login, password).getResult().equals("true")) {
            messageService.delete(id);
        }else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/message/{id}")
    public MessageDto getById(@PathVariable int id){
        return MessageDto.toDto(messageService.getById(id));
    }

    @GetMapping("/message/chat/{id}")
    public List<MessageDto> getAllByChatId(@PathVariable int id, @RequestParam String login, @RequestParam String password) {
        List<Profile> profiles = chatService.getById(id).getUsersId();
        for (Profile profile : profiles) {
            if (profile.getLogin().equals(login) && profileService.isRightPasswordAndLogin(login, password).getResult().equals("true")){
                return MessageDto.toDto(messageService.getAllByChatId(id));
            }
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    @GetMapping("/message")
    public List<MessageDto> getAll(){
        return MessageDto.toDto(messageService.getAll());
    }

    @GetMapping("/message/unread/{profileId}")
    public List<MessageDto> getUnread(@PathVariable int profileId, @RequestParam String login, @RequestParam String password) {
        if (profileService.getById(profileId).getLogin().equals(login) && profileService.isRightPasswordAndLogin(login, password).getResult().equals("true")) {
            return MessageDto.toDto(messageService.getUnread(profileId));
        }else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }
}
