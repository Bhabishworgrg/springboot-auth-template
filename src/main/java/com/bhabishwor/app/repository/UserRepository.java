package com.bhabishwor.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bhabishwor.app.model.AppUser;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
	Optional<AppUser> findByEmail(String email);
}
