package com.delta.delta.repository;

import com.delta.delta.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    // 기본적인 데이터 접근 로직입니다~ 특정 게시물/사용자의 comment 모두 반환
    List<Comment> findByPostId(Long postId);
    List<Comment> findByUserId(Long userId);
}