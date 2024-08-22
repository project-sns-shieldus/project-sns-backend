package com.delta.delta.service;

import com.delta.delta.entity.User;
import com.delta.delta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUser(Integer userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User createUser(User user) {
        if (userRepository.existsById(user.getUserId())) {
            return null; // 409 Conflict if user already exists
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {

        Optional<User> userOptional = userRepository.findById(user.getUserId());

        if (userOptional.isEmpty()) {
            return null; // 404 Not Found if user doesn't exist
        }

        User existingUser = userOptional.get();

        if (user.getUsername() != null) {
            existingUser.setUsername(user.getUsername());
        }
        if (user.getFirstname() != null) {
            existingUser.setFirstname(user.getFirstname());
        }
        if (user.getLastname() != null) {
            existingUser.setLastname(user.getLastname());
        }
        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail());
        }
        if (user.getPassword() != null) {
            existingUser.setPassword(user.getPassword());
        }
        if (user.getProfileImage() != null) {
            existingUser.setProfileImage(user.getProfileImage());
        }

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }
}
