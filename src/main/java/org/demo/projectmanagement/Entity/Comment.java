package org.demo.projectmanagement.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author Minh
 * Date: 10/3/2024
 * Time: 2:30 PM
 */

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String content;

    private LocalDateTime createdDateTime;

    @ManyToOne
    private User user;

    @ManyToOne
    private Issue issue;
}
