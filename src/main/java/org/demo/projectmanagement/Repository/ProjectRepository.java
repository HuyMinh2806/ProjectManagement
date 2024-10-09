package org.demo.projectmanagement.Repository;

import org.demo.projectmanagement.Entity.Project;
import org.demo.projectmanagement.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Minh
 * Date: 10/3/2024
 * Time: 3:05 PM
 */

public interface ProjectRepository extends JpaRepository<Project, Long> {

//    List<Project> findByOwner(User user);

    @Query("select p from Project p where p.name like ?1")
    List<Project> findByNameContainingAndTeamContains(String partialName, User user);

//    @Query("select p from Project p join p.team t where t = :user")
//    List<Project> findProjectByTeam(@Param("user") User user);

    List<Project> findByTeamContainingOrOwner(User user, User owner);
}
