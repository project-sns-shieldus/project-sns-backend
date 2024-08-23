package com.delta.delta.service;

import com.delta.delta.entity.User;
import com.delta.delta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUser(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userId));
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Integer userId, User user) {

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userId));

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
        existingUser.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void addFollower(Integer userId, Integer followerId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userId));

        if (!userId.equals(followerId)) {
            user.getFollowers().add(followerId);
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("A user cannot follow themselves.");
        }
    }


    @Override
    public void deleteFollower(Integer userId, Integer followerId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userId));

        user.getFollowers().remove(followerId);
        userRepository.save(user);
    }

    @Override
    public void addFollowing(Integer userId, Integer followingId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userId));

        if (!userId.equals(followingId)) {
            user.getFollowings().add(followingId);
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("A user cannot follow themselves.");
        }
    }

    @Override
    public void deleteFollowing(Integer userId, Integer followingId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userId));

        user.getFollowings().remove(followingId);
        userRepository.save(user);
    }
}
