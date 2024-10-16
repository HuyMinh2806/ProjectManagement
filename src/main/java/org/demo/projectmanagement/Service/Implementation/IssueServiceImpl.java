package org.demo.projectmanagement.Service.Implementation;

import lombok.RequiredArgsConstructor;
import org.demo.projectmanagement.Dto.Request.IssueRequest;
import org.demo.projectmanagement.Entity.Issue;
import org.demo.projectmanagement.Entity.Project;
import org.demo.projectmanagement.Entity.User;
import org.demo.projectmanagement.Repository.IssueRepository;
import org.demo.projectmanagement.Service.IssueService;
import org.demo.projectmanagement.Service.ProjectService;
import org.demo.projectmanagement.Service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Minh
 * Date: 10/4/2024
 * Time: 8:20 AM
 */

@Service
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;
    private final ProjectService projectService;
    private final UserService userService;

    @Override
    public Issue getIssueById(Long issueId) throws Exception {
        Optional<Issue> issue = issueRepository.findById(issueId);
        if(!issue.isPresent()) {
            throw new Exception("Issue not found with id: " + issueId);
        }

        return issue.get();
    }

    @Override
    public List<Issue> getIssuesByProjectId(Long projectId) throws Exception {
        return issueRepository.findByProjectID(projectId);
    }

    @Override
    public Issue createIssue(IssueRequest request, User user) throws Exception {
        Project project = projectService.getProjectById(request.getProjectID());
        Issue issue = Issue.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(request.getStatus())
                .projectID(request.getProjectID())
                .priority(request.getPriority())
                .dueDate(request.getDueDate())
                .project(project)
                .build();

        return issueRepository.save(issue);
    }

    @Override
    public void deleteIssue(Long issuedId, Long userId) throws Exception {
        getIssueById(issuedId);

        issueRepository.deleteById(issuedId);
    }

    @Override
    public Issue addUserToIssue(Long issueId, Long userId) throws Exception {
        User user = userService.findUserById(userId);
        Issue issue = getIssueById(issueId);
        issue.setAssignee(user);

        return issueRepository.save(issue);
    }

    @Override
    public Issue updateStatus(Long issueId, String status) throws Exception {
        Issue issue = getIssueById(issueId);

        issue.setStatus(status);
        return issueRepository.save(issue);
    }
}
