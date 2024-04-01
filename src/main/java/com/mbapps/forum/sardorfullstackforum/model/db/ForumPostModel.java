package com.mbapps.forum.sardorfullstackforum.model.db;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@Table(name = "ForumPost")
public class ForumPostModel {

  private Integer topicIdFk;
  private String topic;
  private Integer postId;
  //    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "userIdFk", referencedColumnName = "userId", nullable = false)
  private UserModel userId;
  //    private Integer userIdFk;
  private String message;
  private String createdDate;
  //    @OneToMany(mappedBy = "postId", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ForumCommentModel> comments = new ArrayList<>();

}
