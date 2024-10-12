package com.carlospatinos.notificationservice.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carlospatinos.notificationservice.db.model.UserToken;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, Long> {
    List<UserToken> findByAppUser(String appUser);
}
