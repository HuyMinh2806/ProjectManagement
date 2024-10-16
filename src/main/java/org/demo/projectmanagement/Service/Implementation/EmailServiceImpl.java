package org.demo.projectmanagement.Service.Implementation;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.demo.projectmanagement.Service.EmailService;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.security.auth.Subject;

/**
 * @author Minh
 * Date: 10/3/2024
 * Time: 9:44 PM
 */

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSenderImpl mailSender;

    @Override
    public void sendEmailWithToken(String userMail, String link) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        String subject = "Join Project Team Invitation";
        String text = "Click the link to join the project team " + link;

        helper.setSubject(subject);
        helper.setText(text, true);
        helper.setTo(userMail);

        try {
            mailSender.send(mimeMessage);
        } catch (MailSendException e) {
            throw new MailSendException("Failed to send email", e);
        }
    }
}
