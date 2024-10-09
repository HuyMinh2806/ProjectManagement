package org.demo.projectmanagement.Controller;

import lombok.RequiredArgsConstructor;
import org.demo.projectmanagement.Entity.PlanType;
import org.demo.projectmanagement.Entity.Subscription;
import org.demo.projectmanagement.Entity.User;
import org.demo.projectmanagement.Service.SubscriptionService;
import org.demo.projectmanagement.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Minh
 * Date: 10/5/2024
 * Time: 12:35 PM
 */

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final UserService userService;

    @GetMapping("/user")
    public ResponseEntity<Subscription> getSubscription(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);

        Subscription subscription = subscriptionService.getUsersSubscription(user);

        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }

    @PatchMapping("/upgrade")
    public ResponseEntity<Subscription> upgradeSubscription(@RequestHeader("Authorization") String jwt,
                                                            @RequestBody PlanType planType)
    throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Subscription subscription = subscriptionService.upgradeSubscription(user.getId(), planType);

        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }


}
