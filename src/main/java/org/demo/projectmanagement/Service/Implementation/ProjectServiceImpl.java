package org.demo.projectmanagement.Service.Implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.demo.projectmanagement.Entity.Chat;
import org.demo.projectmanagement.Entity.Project;
import org.demo.projectmanagement.Entity.User;
import org.demo.projectmanagement.Repository.ProjectRepository;
import org.demo.projectmanagement.Service.ChatService;
import org.demo.projectmanagement.Service.ProjectService;
import org.demo.projectmanagement.Service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Minh
 * Date: 10/3/2024
 * Time: 3:05 PM
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserService userService;
    private final ChatService chatService;

    @Override
    public Project createProject(Project project, User user) throws Exception {
        Project newProject = Project.builder()
                .owner(user)
                .name(project.getName())
                .tags(project.getTags())
                .category(project.getCategory())
                .description(project.getDescription())
                .team(project.getTeam())
                .build();

        newProject.getTeam().add(user);

        Project savedProject = projectRepository.save(newProject);
        Chat chat = new Chat();
        chat.setProject(savedProject);

        Chat savedChat = chatService.createChat(chat);
        savedProject.setChat(savedChat);

        return savedProject;
    }

    @Override
    public List<Project> getProjectByTeam(User user, String category, String tag) throws Exception {
        List<Project> projects = projectRepository.findByTeamContainingOrOwner(user, user);

        if(category != null) {
            projects = projects.stream().filter(p -> p.getCategory().equals(category)).toList();
        }

        if(tag != null) {
            projects = projects.stream().filter(p -> p.getTags().contains(tag)).toList();
        }

        return projects;
    }

    @Override
    public Project getProjectById(Long projectId) throws Exception {
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if(optionalProject.isEmpty()) {
            throw new Exception("Project not found");
        }

        return optionalProject.get();
    }

    @Override
    public void deleteProject(Long projectId, Long userId) throws Exception {
        getProjectById(projectId);
        projectRepository.deleteById(projectId);
    }

    @Override
    public Project updateProject(Project updatedProject, Long userId) throws Exception {
        Project project = getProjectById(updatedProject.getId());

        project.setName(updatedProject.getName());
        project.setTags(updatedProject.getTags());
        project.setDescription(updatedProject.getDescription());

        return projectRepository.save(project);
    }

    @Override
    public void addUserToProject(Long projectId, Long userId) throws Exception {

        Project project = getProjectById(projectId);
        User user = userService.findUserById(userId);
        if(!project.getTeam().contains(user)) {
            project.getChat().getUsers().add(user);
            project.getTeam().add(user);
            projectRepository.save(project);
        }
    }

    @Override
    public void removeUserToProject(Long projectId, Long userId) throws Exception {


        Project project = getProjectById(projectId);
        User user = userService.findUserById(userId);
        if(!project.getTeam().contains(user)) {
            project.getChat().getUsers().remove(user);
            project.getTeam().remove(user);
            projectRepository.save(project);
        }
    }

    @Override
    public Chat getChatByProjectId(Long projectId) throws Exception {

        Project project = getProjectById(projectId);

        return project.getChat();
    }

    @Override
    public List<Project> searchProject(String keyword, User user) throws Exception {
        String partialName = "%" + keyword + "%";
        log.info("Project Service: search project");
        log.info("PartialName: " + keyword);
        var result = projectRepository.findByNameContainingAndTeamContains(partialName, user);

        for(Project index: result) {
            log.info(String.valueOf(index));
        }
        return result;
    }
}
