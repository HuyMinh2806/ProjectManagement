package org.demo.projectmanagement.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Minh
 * Date: 10/4/2024
 * Time: 11:16 AM
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentRequest {

    private Long issueId;
    private String comment;
}
