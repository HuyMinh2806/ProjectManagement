package org.demo.projectmanagement.Service;

import org.demo.projectmanagement.Entity.PlanType;
import org.demo.projectmanagement.Entity.Subscription;
import org.demo.projectmanagement.Entity.User;

/**
 * @author Minh
 * Date: 10/5/2024
 * Time: 11:15 AM
 */

public interface SubscriptionService {

    Subscription createSubscription(User user);

    Subscription getUsersSubscription(User user);

    Subscription upgradeSubscription(Long userId, PlanType planType);

    boolean isValid(Subscription subscription);
}
