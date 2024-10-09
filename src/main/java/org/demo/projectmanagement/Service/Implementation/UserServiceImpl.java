package org.demo.projectmanagement.Service.Implementation;

import lombok.RequiredArgsConstructor;
import org.demo.projectmanagement.Config.JwtProvider;
import org.demo.projectmanagement.Entity.User;
import org.demo.projectmanagement.Repository.UserRepository;
import org.demo.projectmanagement.Service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/** @author Minh
* Date: 10/3/2024
* Time: 3:19 PM
*/

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findUserProfileByJwt(String jwt) throws Exception {
        String email = JwtProvider.getEmailFromToken(jwt);
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            throw new Exception("User not found");
        }

        return user;
    }

    @Override
    public User findUserById(Long userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);

        if(user.isEmpty()) {
            throw new Exception("User not found");
        }
        return user.get();
    }

    @Override
    public User updateUsersProjectSize(User user, int number) {
        user.setProjectSize(user.getProjectSize() + number);

        if(user.getProjectSize() < 0) {
            user.setProjectSize(0);
            throw new IllegalArgumentException("Project size cannot be negative");
        }

        return userRepository.save(user);
    }
}
