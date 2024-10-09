package org.demo.projectmanagement.Service;

import jakarta.mail.MessagingException;

/**
 * @author Minh
 * Date: 10/3/2024
 * Time: 9:43 PM
 */

public interface EmailService {

    void sendEmailWithToken(String userMail, String link) throws MessagingException;
}
