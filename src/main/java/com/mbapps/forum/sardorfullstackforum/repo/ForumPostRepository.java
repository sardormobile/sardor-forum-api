package com.mbapps.forum.sardorfullstackforum.repo;

import com.mbapps.forum.sardorfullstackforum.model.connection.ForumPostDTO;
import com.mbapps.forum.sardorfullstackforum.model.db.ForumPostModel;
import com.mbapps.forum.sardorfullstackforum.utils.DatabaseUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class ForumPostRepository {

    public final JdbcTemplate jdbcTemplate;

    private final DatabaseUtility databaseUtility;

    public int save(ForumPostModel forumPost) {
        String sql = "INSERT INTO ForumPost (userIdFk, message, createdDate) VALUES (?, ?, ?)";
        Object[] args = {
                forumPost.getUserId().getUserId(),
                forumPost.getMessage(),
                forumPost.getCreatedDate()
        };
        return databaseUtility.insertAndRetrieveKey("postId", sql, args);
    }
    public List<ForumPostDTO> findAll() {
        String sql = "SELECT * FROM ForumPost LEFT JOIN ForumUser ON ForumPost.userIdFk = ForumUser.userId";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ForumPostDTO.class));
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
}
