package org.demo.projectmanagement.Repository;

import org.demo.projectmanagement.Entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Minh
 * Date: 10/5/2024
 * Time: 11:27 AM
 */

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    Subscription findByUserId(Long userId);
}
