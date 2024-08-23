package com.delta.delta.service;

import com.delta.delta.entity.User;

public interface UserService {

    User getUser(Integer userId);

    User createUser(User user);

    User updateUser(Integer userId, User user);

    void deleteUser(Integer userId);

    void addFollower(Integer userId, Integer followerId);

    void deleteFollower(Integer userId, Integer followerId);

    void addFollowing(Integer userId, Integer followingId);

    void deleteFollowing(Integer userId, Integer followingId);
}
