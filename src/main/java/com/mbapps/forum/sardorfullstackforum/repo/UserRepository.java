package com.mbapps.forum.sardorfullstackforum.repo;

import com.mbapps.forum.sardorfullstackforum.model.db.UserModel;
import com.mbapps.forum.sardorfullstackforum.utils.DatabaseUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

  public final JdbcTemplate jdbcTemplate;

  private final DatabaseUtility databaseUtility;

  public int save(UserModel user) {
    String sql = "INSERT INTO ForumUser(firstName, lastName, username, password, role)  VALUES (?, ?, ?, ?, ?)";
    Object[] args = {
        user.getFirstName(),
        user.getLastName(),
        user.getUsername(),
        user.getPassword(),
        user.getRole().name()
    };
    return databaseUtility.insertAndRetrieveKey("userId", sql, args);
  }

  public List<UserModel> findAll() {
    String sql = "SELECT * FROM ForumUser";
    return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(UserModel.class));
  }

  public UserModel findByUsername(String username) {
    String sql = "SELECT * FROM ForumUser WHERE username = ?";
    return jdbcTemplate.queryForObject(sql, new Object[]{username}, new BeanPropertyRowMapper<>(UserModel.class));
  }

  public UserModel findByUserId(Integer userId) {
    String sql = "SELECT * FROM ForumUser WHERE userId = ?";
    return jdbcTemplate.queryForObject(sql, new Object[]{userId}, new BeanPropertyRowMapper<>(UserModel.class));
  }

  public Boolean existsByUsername(String username) {
    String sql = "SELECT COUNT(*) FROM ForumUser WHERE username = ?";
    int count = jdbcTemplate.queryForObject(sql, Integer.class, username);
    return count > 0;
  }

  public int disableJwtExpiredUsers(String jwtToken) {
    String sql = "DELETE FROM ForumUser WHERE token = ?";
    return jdbcTemplate.update(sql, jwtToken);
  }

  public boolean isTokenExists(String jwtToken) {
    String sql = "SELECT COUNT(*) FROM ForumUser WHERE token = ?";
    int count = jdbcTemplate.queryForObject(sql, Integer.class, jwtToken);
    return count > 0;
  }

}
