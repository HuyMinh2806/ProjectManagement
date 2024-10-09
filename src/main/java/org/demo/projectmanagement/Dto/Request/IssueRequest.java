package org.demo.projectmanagement.Dto.Request;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author Minh
 * Date: 10/4/2024
 * Time: 8:15 AM
 */

@Data
public class IssueRequest {

    private String title;
    private String description;
    private String status;
    private Long projectID;
    private String priority;
    private LocalDate dueDate;
}
