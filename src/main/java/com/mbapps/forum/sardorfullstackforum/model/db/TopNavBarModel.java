package com.mbapps.forum.sardorfullstackforum.model.db;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class TopNavBarModel {
  private Number topicId;
  private String topic;
}
