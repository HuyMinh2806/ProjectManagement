package org.demo.projectmanagement.Controller;

import lombok.RequiredArgsConstructor;
import org.demo.projectmanagement.Dto.Request.CreateCommentRequest;
import org.demo.projectmanagement.Dto.Response.MessageResponse;
import org.demo.projectmanagement.Entity.Comment;
import org.demo.projectmanagement.Entity.User;
import org.demo.projectmanagement.Service.CommentService;
import org.demo.projectmanagement.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Minh
 * Date: 10/4/2024
 * Time: 11:09 AM
 */

@RestController
@RequestMapping("api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody CreateCommentRequest request,
                                                 @RequestHeader("Authorization") String jwt)
    throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Comment comment = commentService.createComment(request.getIssueId(), user.getId(), request.getComment());

        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @DeleteMapping("{commentId}")
    public ResponseEntity<MessageResponse> deleteComment(@PathVariable Long commentId,
                                                         @RequestHeader("Authorization") String jwt)
    throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        commentService.deleteComment(commentId, user.getId());
        return new ResponseEntity<>(new MessageResponse("Comment deleted successfully"), HttpStatus.OK);
    }

    @GetMapping("{issueId}")
    public ResponseEntity<List<Comment>> findCommentByIssueId(@PathVariable Long issueId) {
        List<Comment> comments = commentService.findCommentByIssueId(issueId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
