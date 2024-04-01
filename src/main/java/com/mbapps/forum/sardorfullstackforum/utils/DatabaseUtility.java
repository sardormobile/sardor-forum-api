package com.mbapps.forum.sardorfullstackforum.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;

@Component
@RequiredArgsConstructor
public class DatabaseUtility {

  private final JdbcTemplate jdbcTemplate;

  public int insertAndRetrieveKey(String tableIdName, String sql, Object[] args) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    int rowsAffected = jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(sql, new String[]{tableIdName});
      for (int i = 0; i < args.length; i++) {
        ps.setObject(i + 1, args[i]);
      }
      return ps;
    }, keyHolder);

    if (rowsAffected == 1) {
      Number generatedKey = keyHolder.getKey();
      return generatedKey != null ? generatedKey.intValue() : -1;
    } else {
      return -1; // Insert failed
    }
  }
}
//KeyHolder keyHolder = new GeneratedKeyHolder();
//int rowsAffected = jdbcTemplate.update(connection -> {
//    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);//new String[]{"commentId"}
//    ps.setLong(1, comment.getPostId());
//    ps.setLong(2, comment.getUserId());
//    ps.setString(3, comment.getMessage());
//    ps.setString(4, comment.getCreatedDate());
//    return ps;
//}, keyHolder);
//        if (rowsAffected == 1) {
//Number postId = keyHolder.getKey();
//            return postId != null ? postId.intValue() : -1;
//        } else {
//        return -1; // Insert failed
//        }
