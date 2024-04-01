package com.mbapps.forum.sardorfullstackforum.model.db;


import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@Table(name = "ForumComment")
public class ForumCommentModel {

  private Integer commentId;
  //    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "postIdFk", referencedColumnName = "postId", nullable = false)
  private Integer postId;
  //    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "userIdFk", referencedColumnName = "userId", nullable = false)
  private Integer userId;
  private String message;
  //    @Column(nullable = false)
  private String createdDate;
}
