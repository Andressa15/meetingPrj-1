package com.meetingprj.login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.meetingprj.login.model.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long>{
	
	Optional<Login> findByUsernameAndPassword(@Param("username")String username,@Param("password") String password);
}
