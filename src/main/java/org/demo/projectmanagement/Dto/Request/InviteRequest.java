package org.demo.projectmanagement.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Minh
 * Date: 10/4/2024
 * Time: 7:57 AM
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InviteRequest {

    private Long projectId;
    private String email;
}
