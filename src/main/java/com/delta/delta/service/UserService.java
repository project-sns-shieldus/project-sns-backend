package com.delta.delta.service;

import com.delta.delta.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User getUser(Integer userId);

    User createUser(User user);

    User updateUser(User user);

    void deleteUser(Integer userId);
}
