package org.demo.projectmanagement.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Minh
 * Date: 10/3/2024
 * Time: 11:14 AM
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private String jwt;
    private String message;
}
