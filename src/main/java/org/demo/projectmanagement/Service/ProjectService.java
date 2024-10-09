package org.demo.projectmanagement.Service;

import org.demo.projectmanagement.Entity.Chat;
import org.demo.projectmanagement.Entity.Project;
import org.demo.projectmanagement.Entity.User;
import java.util.List;

/**
 * @author Minh
 * Date: 10/3/2024
 * Time: 2:54 PM
 */

public interface ProjectService {

    Project createProject(Project project, User user) throws Exception;

    List<Project> getProjectByTeam(User user, String category, String tag) throws Exception;

    Project getProjectById(Long projectId) throws Exception;

    void deleteProject(Long projectId, Long userId) throws Exception;

    Project updateProject(Project updatedProject, Long userId) throws Exception;

    void addUserToProject(Long projectId, Long userId) throws Exception;

    void removeUserToProject(Long projectId, Long userId) throws Exception;

    Chat getChatByProjectId(Long projectId) throws Exception;

    List<Project> searchProject(String keyword, User user) throws Exception;
}
