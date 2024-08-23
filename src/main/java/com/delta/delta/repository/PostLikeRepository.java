package com.delta.delta.repository;

import com.delta.delta.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    List<PostLike> findByPostId(Long postId); //게시물의 모든 좋아요 반환
    List<PostLike> findByUserId(Long userId); //사용자의 모든 좋아요 반환
    boolean existsByPostIdAndUserId(Long postId, Long userId); //사용자가 게시물에 좋아요 여부
}
