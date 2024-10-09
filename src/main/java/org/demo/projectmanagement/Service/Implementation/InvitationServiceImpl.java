package org.demo.projectmanagement.Service.Implementation;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.demo.projectmanagement.Entity.Invitation;
import org.demo.projectmanagement.Repository.InvitationRepository;
import org.demo.projectmanagement.Service.EmailService;
import org.demo.projectmanagement.Service.InvitationService;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Minh
 * Date: 10/4/2024
 * Time: 7:44 AM
 */

@Service
@RequiredArgsConstructor
public class InvitationServiceImpl implements InvitationService {

    private final InvitationRepository invitationRepository;
    private final EmailService emailService;

    @Override
    public void sendInvitation(String email, Long projectId) throws MessagingException {

        String invitationToken = UUID.randomUUID().toString();
        Invitation invitation = Invitation.builder()
                .token(invitationToken)
                .projectId(projectId)
                .email(email)
                .build();
        invitationRepository.save(invitation);

        String invitationLink = "http://localhost:3000/accept_invitation?token=" + invitationToken;
        emailService.sendEmailWithToken(email, invitationLink);
    }

    @Override
    public Invitation acceptInvitation(String token, Long userId) throws Exception {
        Invitation invitation = invitationRepository.findByToken(token);
        if(invitation == null) {
            throw new Exception("Invitation not found");
        }

        return invitation;
    }

    @Override
    public String getTokenByUserMail(String userEmail) {
        Invitation invitation = invitationRepository.findByEmail(userEmail);
        return invitation.getToken();
    }

    @Override
    public void deleteInvitation(String token) {
        Invitation invitation = invitationRepository.findByToken(token);

        invitationRepository.delete(invitation);
    }
}
