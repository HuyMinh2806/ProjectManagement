package org.demo.projectmanagement.Repository;

import org.demo.projectmanagement.Entity.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Minh
 * Date: 10/4/2024
 * Time: 7:45 AM
 */

public interface InvitationRepository extends JpaRepository<Invitation, Long> {

    Invitation findByToken(String token);

    Invitation findByEmail(String userEmail);
}
