package org.demo.projectmanagement.Repository;

import org.demo.projectmanagement.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Minh
 * Date: 10/4/2024
 * Time: 10:20 AM
 */

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findCommentByIssueId(Long issueId);
}
