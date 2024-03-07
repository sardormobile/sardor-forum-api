package com.mbapps.forum.sardorfullstackforum.repo;

import com.mbapps.forum.sardorfullstackforum.model.db.ForumCommentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SortingCommentRepository extends PagingAndSortingRepository<ForumCommentModel, Integer> , ListCrudRepository<ForumCommentModel, Integer> {

    @Query(value= "SELECT * FROM POST_ID_FK", nativeQuery = true)
    List<ForumCommentModel> findAllByPostId(Integer postId);

    @Query(value= "SELECT * FROM ForumComment", nativeQuery = true)
    List<ForumCommentModel> findAll();

}
