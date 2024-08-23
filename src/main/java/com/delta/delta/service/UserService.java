package com.delta.delta.service;

import com.delta.delta.entity.User;
import org.springframework.stereotype.Service;

public interface UserService {

    User getUser(Long userId);

    User createUser(User user);

    User updateUser(User user);

    void deleteUser(Long userId);
}
