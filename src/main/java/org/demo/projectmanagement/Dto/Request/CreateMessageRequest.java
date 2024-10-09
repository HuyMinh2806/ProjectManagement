package org.demo.projectmanagement.Dto.Request;

import lombok.Data;

/**
 * @author Minh
 * Date: 10/5/2024
 * Time: 1:00 AM
 */

@Data
public class CreateMessageRequest {

    private Long senderId;
    private Long projectId;
    private String content;
}
