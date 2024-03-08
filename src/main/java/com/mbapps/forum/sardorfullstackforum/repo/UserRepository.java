package com.mbapps.forum.sardorfullstackforum.repo;

import com.mbapps.forum.sardorfullstackforum.model.db.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
//    UserModel findByUsername(String username);
    Optional<UserModel> findByUsername(String username);

    Boolean existsByUsername(String username);

}
