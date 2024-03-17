package com.mbapps.forum.sardorfullstackforum.repo;

import com.mbapps.forum.sardorfullstackforum.model.connection.ForumCommentDTO;
import com.mbapps.forum.sardorfullstackforum.model.db.ForumCommentModel;
import com.mbapps.forum.sardorfullstackforum.utils.DatabaseUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class ForumCommentRepository {

    private final JdbcTemplate jdbcTemplate;

    private final DatabaseUtility databaseUtility;

    public int save(ForumCommentModel comment) {
        String sql = "INSERT INTO ForumComment(postIdFk, userIdFk, message, createdDate) VALUES (?, ?, ?, ?)";
        Object[] args = {comment.getPostId(), comment.getUserId(), comment.getMessage(), comment.getCreatedDate()};
        return databaseUtility.insertAndRetrieveKey("commentId", sql, args);
    }

    public List<ForumCommentDTO> findAllByPostId(Integer postId) {
        String sql =
                """
                         SELECT c.commentId, c.postIdFk, c.userIdFk, c.createdDate, u.role, u.firstName, u.username, c.message
                                    FROM ForumComment c
                                    LEFT JOIN ForumUser u ON c.userIdFk = u.userId
                                    WHERE c.postIdFk = ?
                        """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ForumCommentDTO.class), postId);
    }

    public List<ForumCommentDTO> findAll() {
        String sql =
                """
                        SELECT c.commentId, c.postIdFk, c.userIdFk, c.createdDate, u.role, u.firstName, u.username, c.message
                                     FROM ForumComment c
                                     LEFT JOIN ForumPost p ON c.postIdFk = p.postId
                                     LEFT JOIN ForumUser u ON c.userIdFk = u.userId
                        """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ForumCommentDTO.class));
    }

    public int deleteCommentById(Integer commentId) {
        String sql = "DELETE FROM ForumComment WHERE commentId = " + commentId;
        return jdbcTemplate.update(sql);
    }

    public Integer getCommentsCountByPostId(Integer postId) {
        String sql = "SELECT COUNT(*) FROM ForumComment WHERE postIdFk = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, postId);
    }

}
