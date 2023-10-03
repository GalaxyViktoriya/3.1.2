package com.springboot.dao;

import com.springboot.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    void add(User user);
    void delete(long id);
    void updateUser(User user);
    User getUser(long id);
}
