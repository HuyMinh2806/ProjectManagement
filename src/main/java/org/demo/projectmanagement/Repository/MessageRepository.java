package org.demo.projectmanagement.Repository;

import org.demo.projectmanagement.Entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Minh
 * Date: 10/4/2024
 * Time: 1:55 PM
 */

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByChatIdOrderByCreatedAtAsc(long id);
}
