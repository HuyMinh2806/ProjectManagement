package org.demo.projectmanagement.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.demo.projectmanagement.Entity.Project;
import org.demo.projectmanagement.Entity.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Minh
 * Date: 10/4/2024
 * Time: 9:18 AM
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IssueResponse {

    private Long id;
    private String title;
    private String description;
    private String status;
    private Project project;
    private Long projectID;
    private String priority;
    private LocalDate dueDate;
    private List<String> tags = new ArrayList<>();
    private User assignee;
}
