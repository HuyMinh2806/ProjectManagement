package org.demo.projectmanagement.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

/**
 * @author Minh
 * Date: 10/5/2024
 * Time: 11:15 AM
 */

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate subscriptionStartDate;
    private LocalDate SubscriptionEndDate;
    private PlanType planType;
    private boolean isValid;

    @OneToOne
    private User user;
}
