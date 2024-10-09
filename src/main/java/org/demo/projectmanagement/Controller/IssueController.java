package org.demo.projectmanagement.Controller;

import lombok.RequiredArgsConstructor;
import org.demo.projectmanagement.Dto.Request.IssueRequest;
import org.demo.projectmanagement.Dto.Response.IssueResponse;
import org.demo.projectmanagement.Dto.Response.MessageResponse;
import org.demo.projectmanagement.Entity.Issue;
import org.demo.projectmanagement.Entity.User;
import org.demo.projectmanagement.Service.IssueService;
import org.demo.projectmanagement.Service.ProjectService;
import org.demo.projectmanagement.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Minh
 * Date: 10/4/2024
 * Time: 8:57 AM
 */

@RestController
@RequestMapping("api/issues")
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;
    private final UserService userService;
    private final ProjectService projectService;

    @GetMapping("{issueId}")
    public ResponseEntity<Issue> getIssueById(@PathVariable Long issueId) throws Exception {
        return ResponseEntity.ok(issueService.getIssueById(issueId));
    }

    @GetMapping("project/{projectId}")
    public ResponseEntity<List<Issue>> getIssuesByProjectId(@PathVariable Long projectId) throws Exception {
        return ResponseEntity.ok(issueService.getIssuesByProjectId(projectId));
    }

    @PostMapping
    public ResponseEntity<IssueResponse> createIssue(@RequestBody IssueRequest request,
                                                     @RequestHeader("Authorization") String jwt)
    throws Exception {
        User tokenUser = userService.findUserProfileByJwt(jwt);

        Issue issue = issueService.createIssue(request, tokenUser);
        IssueResponse issueResponse = IssueResponse.builder()
                                        .description(issue.getDescription())
                                        .dueDate(issue.getDueDate())
                                        .id(issue.getId())
                                        .priority(issue.getPriority())
                                        .project(issue.getProject())
                                        .projectID(issue.getProjectID())
                                        .status(issue.getStatus())
                                        .title(issue.getTitle())
                                        .tags(issue.getTags())
                                        .assignee(issue.getAssignee())
                                        .build();

        return ResponseEntity.ok(issueResponse);
    }

    @DeleteMapping("{issueId}")
    public ResponseEntity<MessageResponse> deleteIssue(@PathVariable Long issueId,
                                                       @RequestHeader("Authorization") String jwt)
    throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        issueService.deleteIssue(issueId, user.getId());
        return ResponseEntity.ok(new MessageResponse("Issue deleted successfully"));
    }

    @PutMapping("{issueId}/assignee/{userId}")
    public ResponseEntity<Issue> addUserToIssue(@PathVariable Long issueId, @PathVariable Long userId) throws Exception {
        return ResponseEntity.ok(issueService.addUserToIssue(issueId, userId));
    }

    @PutMapping("{issueId}/status/{status}")
    public ResponseEntity<Issue> updateStatus(@PathVariable Long issueId, @PathVariable String status) throws Exception {
        return ResponseEntity.ok(issueService.updateStatus(issueId, status));
    }
}
