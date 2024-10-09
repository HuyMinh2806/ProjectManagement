package org.demo.projectmanagement.Service;

import org.demo.projectmanagement.Entity.User;

/**
 * @author Minh
 * Date: 10/3/2024
 * Time: 3:14 PM
 */

public interface UserService {

    User findUserProfileByJwt(String jwt) throws Exception;

    User findUserByEmail(String email) throws Exception;

    User findUserById(Long userId) throws Exception;

    User updateUsersProjectSize(User user, int number);
}
