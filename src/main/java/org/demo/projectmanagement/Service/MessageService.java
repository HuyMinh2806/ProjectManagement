package org.demo.projectmanagement.Service;

import org.demo.projectmanagement.Entity.Message;

import java.util.List;

/**
 * @author Minh
 * Date: 10/4/2024
 * Time: 11:46 AM
 */

public interface MessageService {

    Message sendMessage(Long senderId, Long projectId, String content) throws Exception;

    List<Message> getMessageByProjectId(Long projectId) throws Exception;
}
