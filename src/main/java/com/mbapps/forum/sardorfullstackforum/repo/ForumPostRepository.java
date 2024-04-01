package com.mbapps.forum.sardorfullstackforum.repo;

import com.mbapps.forum.sardorfullstackforum.model.connection.ForumPostDTO;
import com.mbapps.forum.sardorfullstackforum.model.db.ForumPostModel;
import com.mbapps.forum.sardorfullstackforum.model.db.TopNavBarModel;
import com.mbapps.forum.sardorfullstackforum.utils.DatabaseUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ForumPostRepository {

  public final JdbcTemplate jdbcTemplate;

  private final DatabaseUtility databaseUtility;

  public int save(ForumPostModel forumPost) {
    String sql = "INSERT INTO ForumPost (userIdFk, message, createdDate, topicIdFk) VALUES (?, ?, ?, ?)";
    Object[] args = {
        forumPost.getUserId().getUserId(),
        forumPost.getMessage(),
        forumPost.getCreatedDate(),
        forumPost.getTopicIdFk()
    };
    return databaseUtility.insertAndRetrieveKey("postId", sql, args);
  }

  public List<ForumPostDTO> findAll() {
    String sql = "SELECT * FROM ForumPost LEFT JOIN ForumUser ON ForumPost.userIdFk = ForumUser.userId";
    return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ForumPostDTO.class));
  }
  public List<ForumPostDTO> findAllByTopicId(Integer id) {
    String sql = "SELECT * FROM ForumPost LEFT JOIN ForumUser ON ForumPost.userIdFk = ForumUser.userId WHERE topicIdFk = ?";
    return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ForumPostDTO.class), id);
  }

  public ForumPostModel findByPostId(Integer postId) {
    String sql = "SELECT * FROM ForumPost WHERE postId = ?";
    return jdbcTemplate.queryForObject(sql, new Object[]{postId}, new BeanPropertyRowMapper<>(ForumPostModel.class));
  }

  public List<ForumPostModel> findByUserId(Integer userId) {
    String sql = "SELECT * FROM ForumPost WHERE userId = " + userId;
    return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ForumPostModel.class));
  }

  public int deleteByPostId(Integer postId) {
    String sql = "DELETE FROM ForumPost WHERE postId = ?";
    return jdbcTemplate.update(sql, postId);
  }

  public List<String> getTopTabItemNames() {
    String sql = "SELECT DISTINCT topic FROM FORUMTABBAR";
    return jdbcTemplate.queryForList(sql, String.class);
  }
  public List<TopNavBarModel> getTopTabItems() {
    String sql = "SELECT * FROM FORUMTABBAR";
    return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TopNavBarModel.class));
  }
  public int deleteByTitle(String title) {
    String sql = "DELETE FROM FORUMTABBAR WHERE topic = ?";
    return jdbcTemplate.update(sql, title);
  }

  public int insertNewNavBarTitle(String title) {
    String sql = "INSERT INTO FORUMTABBAR (topic) VALUES (?)";
    return jdbcTemplate.update(sql, title);
  }
}
