package org.demo.projectmanagement.Service;

import org.demo.projectmanagement.Entity.Comment;

import java.util.List;

/**
 * @author Minh
 * Date: 10/4/2024
 * Time: 10:14 AM
 */

public interface CommentService {

    Comment createComment(Long issueId, Long userId, String comment) throws Exception;

    void deleteComment(Long commentId, Long userId) throws Exception;

    List<Comment> findCommentByIssueId(Long issueId);
}
