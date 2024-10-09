package org.demo.projectmanagement.Service.Implementation;

import lombok.RequiredArgsConstructor;
import org.demo.projectmanagement.Entity.Comment;
import org.demo.projectmanagement.Entity.Issue;
import org.demo.projectmanagement.Entity.User;
import org.demo.projectmanagement.Repository.CommentRepository;
import org.demo.projectmanagement.Repository.IssueRepository;
import org.demo.projectmanagement.Repository.UserRepository;
import org.demo.projectmanagement.Service.CommentService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * @author Minh
 * Date: 10/4/2024
 * Time: 10:17 AM
 */

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final IssueRepository issueRepository;
    private final UserRepository userRepository;

    @Override
    public Comment createComment(Long issueId, Long userId, String comment) throws Exception {
        Optional<Issue> issueOptional = issueRepository.findById(issueId);
        Optional<User> userOptional = userRepository.findById(userId);

        if(issueOptional.isEmpty()) {
            throw new Exception("Issue not found with id: " + issueId);
        }

        if(userOptional.isEmpty()) {
            throw new Exception("User not found with id: " + userId);
        }

        Issue issue = issueOptional.get();
        User user = userOptional.get();
        Comment commentEntity = new Comment();
        commentEntity.setContent(comment);
        commentEntity.setIssue(issue);
        commentEntity.setUser(user);
        Comment savedComment = commentRepository.save(commentEntity);

        issue.getComments().add(commentEntity);

        return savedComment;
    }

    @Override
    public void deleteComment(Long commentId, Long userId) throws Exception {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        Optional<User> userOptional = userRepository.findById(userId);

        if(commentOptional.isEmpty()) {
            throw new Exception("Comment not found with id: " + commentId);
        }

        if(userOptional.isEmpty()) {
            throw new Exception("User not found with id: " + userId);
        }

        Comment comment = commentOptional.get();
        User user = userOptional.get();

        if(comment.getUser().equals(user)) {
            commentRepository.delete(comment);
        } else {
        throw new Exception("You are not authorized to delete this comment");
        }
    }

    @Override
    public List<Comment> findCommentByIssueId(Long issueId) {
        return List.of();
    }
}
