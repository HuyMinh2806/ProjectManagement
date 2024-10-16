package org.demo.projectmanagement.Service;

import jakarta.mail.MessagingException;
import org.demo.projectmanagement.Entity.Invitation;

/**
 * @author Minh
 * Date: 10/4/2024
 * Time: 7:42 AM
 */

public interface InvitationService {

    void sendInvitation(String email, Long projectId) throws MessagingException;

    Invitation acceptInvitation(String token, Long userId) throws Exception;

    String getTokenByUserMail(String userEmail);

    void deleteInvitation(String token);
}
