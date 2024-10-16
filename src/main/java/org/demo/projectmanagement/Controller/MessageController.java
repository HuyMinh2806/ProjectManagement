package org.demo.projectmanagement.Controller;

import lombok.RequiredArgsConstructor;
import org.demo.projectmanagement.Dto.Request.CreateMessageRequest;
import org.demo.projectmanagement.Entity.Chat;
import org.demo.projectmanagement.Entity.Message;
import org.demo.projectmanagement.Entity.User;
import org.demo.projectmanagement.Service.MessageService;
import org.demo.projectmanagement.Service.ProjectService;
import org.demo.projectmanagement.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author Minh
 * Date: 10/5/2024
 * Time: 12:57 AM
 */

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final UserService userService;
    private final ProjectService projectService;

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody CreateMessageRequest request) throws Exception {
        User user = userService.findUserById(request.getSenderId());

        Chat chats = projectService.getProjectById(request.getProjectId()).getChat();

        if(chats == null) {
            throw new Exception("Chat not found");
        }

        Message sentMessage = messageService.sendMessage(request.getSenderId(),
                request.getProjectId(), request.getContent());
        return ResponseEntity.ok(sentMessage);
    }

    @GetMapping("/chat/{projectId}")
    public ResponseEntity<List<Message>> getMessagesByChatId(@PathVariable Long projectId) throws Exception {
        List<Message> messages = messageService.getMessageByProjectId(projectId);

        return ResponseEntity.ok(messages);
    }

}
