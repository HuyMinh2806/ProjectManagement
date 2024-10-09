package org.demo.projectmanagement.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Minh
 * Date: 10/3/2024
 * Time: 2:07 PM
 */

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String content;

    private LocalDateTime createdAt;

    @ManyToOne
    private Chat chat;

    @ManyToOne
    private User sender;
}
