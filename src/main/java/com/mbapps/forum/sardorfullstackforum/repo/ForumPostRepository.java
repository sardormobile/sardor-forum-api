package com.mbapps.forum.sardorfullstackforum.repo;

import com.mbapps.forum.sardorfullstackforum.model.connection.ForumPostDTO;
import com.mbapps.forum.sardorfullstackforum.model.db.ForumPostModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ForumPostRepository extends JpaRepository<ForumPostModel, Long> {
//    @Transactional//delete with comment table
//    @Modifying//change table
    @Query("DELETE FROM forum_post WHERE post_id = :postId")
    void deleteForumPostById(@Param("postId") Long postId);
}
