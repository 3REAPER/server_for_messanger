package com.pervukhin.rest;

import com.pervukhin.domain.Message;
import com.pervukhin.domain.PhotoMessage;
import com.pervukhin.domain.TextMessage;
import com.pervukhin.rest.dto.MessageDto;
import com.pervukhin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@RestController
public class MessageController {
    private final MessageService messageService;
    private ProfileService profileService;
    @Autowired
    private ServletContext servletContext;

    public MessageController() throws SQLException, ClassNotFoundException {
        this.messageService = new MessageServiceImpl();
        this.profileService = new ProfileServiceImpl();
    }

    @PostMapping("/message")
    public void createMessage(@RequestBody MessageDto messageDto){

        Message message = MessageDto.toDomainObject(messageDto);
        message.setTime(TextMessage.getDateNow());

        messageService.insert(message);
    }

    @PostMapping("/message/{id}")
    public void updateMessage(@RequestBody MessageDto messageDto){

        Message message = MessageDto.toDomainObject(messageDto);

        messageService.update(message);
    }

    @DeleteMapping("/message/{id}")
    public void delete(@PathVariable int id){
        messageService.delete(id);
    }

    @GetMapping("/message/{id}")
    public MessageDto getById(@PathVariable int id){
        Message message = messageService.getById(id);
        if (message.getIsPhoto()){
            return MessageDto.toDto(((PhotoMessage) message));
        }else {
            return MessageDto.toDto(((TextMessage) message));
        }
    }

    @GetMapping("/message/chat/{id}")
    public List<MessageDto> getAllByChatId(@PathVariable int id) {
        return MessageDto.toDto(messageService.getAllByChatId(id));
    }

    @GetMapping("/message")
    public List<MessageDto> getAll(){
        return MessageDto.toDto(messageService.getAll());
    }

    @GetMapping("/message/unread/{profileId}")
    public List<MessageDto> getUnread(@PathVariable int profileId){
        return MessageDto.toDto(messageService.getUnread(profileId));
    }

    @PostMapping("/message/upload")
    public void upload(@RequestParam MultipartFile file, @RequestParam  int profileId, @RequestParam int chatId){
        if (!file.isEmpty()) {
            try {
                File createdFile = new File("photo/" + file.getOriginalFilename());
                String[] nameAndType = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
                String name = nameAndType[0];
                String type = nameAndType[1];
                int i = 0;
                while (createdFile.exists()){
                    i++;
                    createdFile = new File("photo/" + name +"(" + i +")" +"." +type);
                    System.out.println("photo/" + file.getName() +"(" + i +")" +file.getContentType());
                }
                byte[] bytes = file.getBytes();
                Path path = createdFile.toPath();
                BufferedOutputStream stream =
                        new BufferedOutputStream(Files.newOutputStream(path));
                stream.write(bytes);
                stream.close();
                PhotoMessage message = new PhotoMessage(null,profileService.getById(profileId), chatId, null, true, null);
                message.setTime(TextMessage.getDateNow());
                message.setPath(path.toAbsolutePath().toString());
                messageService.insert(message);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @GetMapping("message/download")
    public void downloadFile(HttpServletResponse response,
                              @RequestParam(defaultValue = "DEFAULT_FILE_NAME") String fileName) throws IOException {


        MediaType mediaType;
        String mineType = servletContext.getMimeType(fileName);
        try {
            mediaType = MediaType.parseMediaType(mineType);
        } catch (Exception e) {
            mediaType = MediaType.APPLICATION_OCTET_STREAM;
        }
        System.out.println("fileName: " + fileName);
        System.out.println("mediaType: " + mediaType);

        File file = new File("D:\\project\\server_for_messanger\\photo" + "\\" + fileName);

        response.setContentType(mediaType.getType());
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName());
        response.setContentLength((int) file.length());

        BufferedInputStream inStream = new BufferedInputStream(Files.newInputStream(file.toPath()));
        BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream());

        byte[] buffer = new byte[1024];
        int bytesRead = 0;
        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        outStream.flush();
        inStream.close();
    }
}
