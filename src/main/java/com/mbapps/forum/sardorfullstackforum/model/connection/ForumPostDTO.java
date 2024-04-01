package com.mbapps.forum.sardorfullstackforum.model.connection;

import com.mbapps.forum.sardorfullstackforum.enums.Role;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ForumPostDTO {
  private Boolean status = false;
  private String err_msg;
  private Integer postId;
  private Integer topicIdFk;
  @NotNull
  private Integer userId;
  private Role role;
  private String firstName;
  private String username;
  @NotNull
  private String message;
  private String createdDate;
  private Integer commentsCount;

}
