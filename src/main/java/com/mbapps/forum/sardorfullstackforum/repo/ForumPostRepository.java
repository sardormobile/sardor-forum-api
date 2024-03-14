package com.mbapps.forum.sardorfullstackforum.repo;

import com.mbapps.forum.sardorfullstackforum.model.connection.ForumPostDTO;
import com.mbapps.forum.sardorfullstackforum.model.db.ForumPostModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ForumPostRepository extends JpaRepository<ForumPostModel, Integer> {

//    @Transactional
//    void deleteByCommitId(Integer postId);

    List<ForumPostModel> findByUserId_UserId(Integer userId);
}
