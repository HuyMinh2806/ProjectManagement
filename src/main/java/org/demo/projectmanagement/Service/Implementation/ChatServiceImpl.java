package org.demo.projectmanagement.Service.Implementation;

import lombok.RequiredArgsConstructor;
import org.demo.projectmanagement.Entity.Chat;
import org.demo.projectmanagement.Repository.ChatRepository;
import org.demo.projectmanagement.Service.ChatService;
import org.springframework.stereotype.Service;

/**
 * @author Minh
 * Date: 10/3/2024
 * Time: 4:50 PM
 */

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;

    @Override
    public Chat createChat(Chat chat) {
        return chatRepository.save(chat);
    }
}
