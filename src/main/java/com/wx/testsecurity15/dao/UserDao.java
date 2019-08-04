package com.wx.testsecurity15.dao;


import com.wx.testsecurity15.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {

	User findByUsername(String username);
}
