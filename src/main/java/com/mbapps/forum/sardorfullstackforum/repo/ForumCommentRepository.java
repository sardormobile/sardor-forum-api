package com.mbapps.forum.sardorfullstackforum.repo;

import com.mbapps.forum.sardorfullstackforum.model.connection.ForumCommentDTO;
import com.mbapps.forum.sardorfullstackforum.model.db.ForumCommentModel;
import com.mbapps.forum.sardorfullstackforum.model.db.ForumPostModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class ForumCommentRepository {

    private final JdbcTemplate jdbcTemplate;

    public int save(ForumCommentModel comment) {
        String sql = "INSERT INTO ForumComment(commentId, postIdFk, userIdFk, message, createdDate) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                comment.getCommentId(),
                comment.getPostId(),
                comment.getUserId(),
                comment.getMessage(),
                comment.getCreatedDate()
        );
    }
    public List<ForumCommentModel> findAllByPostId(Integer postId) {
        String sql = "SELECT * FROM ForumCommentModel WHERE postId = " + postId;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ForumCommentModel.class));
    }

    public List<ForumCommentDTO> findAll() {
        String sql =
                "SELECT c.commentId, c.postIdFk, c.userIdFk, c.createdDate, u.role, u.firstName, u.username, c.message " +
                "FROM ForumComment c " +
                "LEFT JOIN ForumPost p ON c.postIdFk = p.postId " +
                "LEFT JOIN ForumUser u ON c.userIdFk = u.userId";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ForumCommentDTO.class));
    }

    public void deleteById(Integer commitId) {
        String sql = "DELETE * FROM ForumCommentModel WHERE commitId = " + commitId;
        jdbcTemplate.update(sql);
    }

    public void deleteByPostId(Integer postId) {
        String sql = "DELETE * FROM ForumCommentModel WHERE postId.postId = " + postId;
        jdbcTemplate.update(sql);
    }
    public Integer getCommentsCountByPostId(Integer postId) {
        String sql = "SELECT COUNT(*) FROM ForumComment WHERE postIdFk = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, postId);
    }

}
