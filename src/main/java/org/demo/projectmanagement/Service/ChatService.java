package org.demo.projectmanagement.Service;

import org.demo.projectmanagement.Entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Minh
 * Date: 10/3/2024
 * Time: 4:49 PM
 */

public interface ChatService {

    Chat createChat(Chat chat);
}
