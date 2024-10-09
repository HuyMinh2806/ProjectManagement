package org.demo.projectmanagement.Dto.Request;

import lombok.Data;

/**
 * @author Minh
 * Date: 10/3/2024
 * Time: 11:19 AM
 */

@Data
public class LoginRequest {

    private String email;
    private String password;
}
