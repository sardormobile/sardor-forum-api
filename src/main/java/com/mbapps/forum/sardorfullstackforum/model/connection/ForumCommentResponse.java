package com.mbapps.forum.sardorfullstackforum.model.connection;

import com.mbapps.forum.sardorfullstackforum.enums.Role;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ForumCommentResponse {
  private Boolean status = false;
  private Integer commentId;
  @NotNull
  private Integer postId;
  @NotNull
  private Integer userId;
  private Role role;
  private String firstName;
  private String username;
  @NotNull
  private String message;
  private String createdDate;
}
