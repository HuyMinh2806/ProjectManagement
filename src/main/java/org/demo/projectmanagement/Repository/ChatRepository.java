package org.demo.projectmanagement.Repository;

import org.demo.projectmanagement.Entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Minh
 * Date: 10/3/2024
 * Time: 4:51 PM
 */

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
