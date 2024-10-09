package org.demo.projectmanagement.Repository;

import org.demo.projectmanagement.Entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Minh
 * Date: 10/4/2024
 * Time: 8:21 AM
 */

public interface IssueRepository extends JpaRepository<Issue, Long> {

    List<Issue> findByProjectID(Long projectID);
}
