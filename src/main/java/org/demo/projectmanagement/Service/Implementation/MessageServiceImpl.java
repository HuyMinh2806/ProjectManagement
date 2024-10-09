package org.demo.projectmanagement.Service.Implementation;

import lombok.RequiredArgsConstructor;
import org.demo.projectmanagement.Entity.Chat;
import org.demo.projectmanagement.Entity.Message;
import org.demo.projectmanagement.Entity.User;
import org.demo.projectmanagement.Repository.MessageRepository;
import org.demo.projectmanagement.Repository.UserRepository;
import org.demo.projectmanagement.Service.MessageService;
import org.demo.projectmanagement.Service.ProjectService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Minh
 * Date: 10/4/2024
 * Time: 1:57 PM
 */

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final UserRepository userRepository;
    private final ProjectService projectService;
    private final MessageRepository messageRepository;

    @Override
    public Message sendMessage(Long senderId, Long projectId, String content) throws Exception {
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new Exception("User not found with id: " + senderId));

        Chat chat = projectService.getProjectById(projectId).getChat();

        Message message = Message.builder()
                .content(content)
                .sender(sender)
                .createdAt(LocalDateTime.now())
                .chat(chat)
                .build();

        Message savedMessage = messageRepository.save(message);
        chat.getMessages().add(savedMessage);

        return savedMessage;
    }

    @Override
    public List<Message> getMessageByProjectId(Long projectId) throws Exception {
        Chat chat = projectService.getChatByProjectId(projectId);
        List<Message> messages = messageRepository.findByChatIdOrderByCreatedAtAsc(chat.getId());
        return messages;
    }
}
