package org.demo.projectmanagement.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

/**
 * @author Minh
 * Date: 10/3/2024
 * Time: 2:47 PM
 */

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String token;
    private String email;
    private Long projectId;
}
