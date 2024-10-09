package org.demo.projectmanagement.Repository;

import org.demo.projectmanagement.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Minh
 * Date: 10/3/2024
 * Time: 10:21 AM
 */

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
