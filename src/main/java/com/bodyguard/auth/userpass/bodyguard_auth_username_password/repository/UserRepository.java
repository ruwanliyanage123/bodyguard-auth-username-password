package com.bodyguard.auth.userpass.bodyguard_auth_username_password.repository;

import com.bodyguard.auth.userpass.bodyguard_auth_username_password.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> , JpaSpecificationExecutor<UserEntity> {

    @Query(value = "select * from user where username=:username", nativeQuery = true)
    public UserEntity findUserByUserName(@Param("username") String username);
}
