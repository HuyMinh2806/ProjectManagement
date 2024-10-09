package org.demo.projectmanagement.Service;

import org.demo.projectmanagement.Dto.Request.IssueRequest;
import org.demo.projectmanagement.Entity.Issue;
import org.demo.projectmanagement.Entity.User;

import java.util.List;
import java.util.Optional;

/**
 * @author Minh
 * Date: 10/4/2024
 * Time: 8:11 AM
 */

public interface IssueService {

    Issue getIssueById(Long issueId) throws Exception;

    List<Issue> getIssuesByProjectId(Long projectId) throws Exception;

    Issue createIssue(IssueRequest request, User user) throws Exception;

    void deleteIssue(Long issuedId, Long userId) throws Exception;

    Issue addUserToIssue(Long issueId, Long userId) throws Exception;

    Issue updateStatus(Long issueId, String status) throws Exception;
}
