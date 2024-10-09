package org.demo.projectmanagement.Controller;

import lombok.RequiredArgsConstructor;
import org.demo.projectmanagement.Dto.Request.InviteRequest;
import org.demo.projectmanagement.Dto.Response.MessageResponse;
import org.demo.projectmanagement.Entity.*;
import org.demo.projectmanagement.Service.InvitationService;
import org.demo.projectmanagement.Service.ProjectService;
import org.demo.projectmanagement.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author Minh
 * Date: 10/3/2024
 * Time: 6:18 PM
 */

@RestController
@RequestMapping("api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;
    private final InvitationService invitationService;

    @GetMapping
    public ResponseEntity<List<Project>> getProjects(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String tag,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        List<Project> projects = projectService.getProjectByTeam(user, category, tag);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("{projectId}")
    public ResponseEntity<Project> getProjectById(
            @PathVariable Long projectId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Project project = projectService.getProjectById(projectId);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Project> createProject(
            @RequestBody Project project,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Project createdProject = projectService.createProject(project, user);
        return new ResponseEntity<>(createdProject, HttpStatus.OK);
    }

    @PatchMapping("{projectId}")
    public ResponseEntity<Project> updateProject(
            @PathVariable Long projectId,
            @RequestBody Project project,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Project updatedProject = projectService.updateProject(project, projectId);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }

    @DeleteMapping("{projectId}")
    public ResponseEntity<MessageResponse> deleteProject(
            @PathVariable Long projectId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        projectService.deleteProject(projectId, user.getId());
        return new ResponseEntity<>(new MessageResponse("Project deleted successfully"), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Project>> searchProject(
            @RequestParam(required = false) String keyword,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        List<Project> projects = projectService.searchProject(keyword, user);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{projectId}/chat")
    public ResponseEntity<Chat> getChatByProjectId(
            @PathVariable Long projectId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Chat chat = projectService.getChatByProjectId(projectId);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }

    @PostMapping("/invite")
    public ResponseEntity<MessageResponse> inviteProject(
            @RequestBody InviteRequest request,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        invitationService.sendInvitation(request.getEmail(), request.getProjectId());
        return new ResponseEntity<>(new MessageResponse("Invitation sent successfully"), HttpStatus.OK);
    }

    @PostMapping("/accept_invitation")
    public ResponseEntity<MessageResponse> acceptInviteProject(
            @RequestParam String token,
            @RequestHeader("Authorization") String jwt
            ) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Invitation invitation = invitationService.acceptInvitation(token, user.getId());
        projectService.addUserToProject(invitation.getProjectId(), user.getId());
        return new ResponseEntity<>(new MessageResponse("Invitation sent successfully"), HttpStatus.OK);
    }
}