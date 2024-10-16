package org.demo.projectmanagement.Service.Implementation;

import lombok.RequiredArgsConstructor;
import org.demo.projectmanagement.Entity.PlanType;
import org.demo.projectmanagement.Entity.Subscription;
import org.demo.projectmanagement.Entity.User;
import org.demo.projectmanagement.Repository.SubscriptionRepository;
import org.demo.projectmanagement.Service.SubscriptionService;
import org.demo.projectmanagement.Service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * @author Minh
 * Date: 10/5/2024
 * Time: 11:25 AM
 */

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final UserService userService;
    private final SubscriptionRepository subscriptionRepository;

    @Override
    public Subscription createSubscription(User user) {
        Subscription subscription = Subscription.builder()
                .user(user)
                .subscriptionStartDate(LocalDate.now())
                .SubscriptionEndDate(LocalDate.now().plusMonths(12))
                .isValid(true)
                .planType(PlanType.FREE)
                .build();

        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription getUsersSubscription(User user) {
        Subscription subscription = subscriptionRepository.findByUserId(user.getId());
        if(!isValid(subscription)) {
            subscription.setPlanType(PlanType.FREE);
            subscription.setSubscriptionStartDate(LocalDate.now());
            subscription.setSubscriptionEndDate(LocalDate.now().plusYears(1));
        }

        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription upgradeSubscription(Long userId, PlanType planType) {
        Subscription subscription = subscriptionRepository.findByUserId(userId);
        subscription.setPlanType(planType);
        subscription.setSubscriptionStartDate(LocalDate.now());
        if(planType.equals(PlanType.MONTHLY)) {
            subscription.setSubscriptionEndDate(LocalDate.now().plusMonths(1));
        } else if(planType.equals(PlanType.ANNUALLY)) {
            subscription.setSubscriptionEndDate(LocalDate.now().plusYears(1));
        }
        return subscriptionRepository.save(subscription);
    }

    @Override
    public boolean isValid(Subscription subscription) {
        if(subscription.getPlanType().equals(PlanType.FREE)) {
            return true;
        }

        LocalDate endDate = subscription.getSubscriptionEndDate();
        LocalDate now = LocalDate.now();
        return endDate.isAfter(now) || endDate.equals(now);
    }
}
