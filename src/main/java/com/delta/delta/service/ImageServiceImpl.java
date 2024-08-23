package com.delta.delta.service;

import com.delta.delta.entity.Image;
import com.delta.delta.entity.Post;
import com.delta.delta.repository.ImageRepository;
import com.delta.delta.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    @Transactional
    public Image createImage(Long postId, String fileName) {
        Optional<Post> postOptional = postRepository.findById(postId);

        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            Image image = Image.builder()
                    .post(post)
                    .fileName(fileName)
                    .build();
            return imageRepository.save(image);
        } else {
            throw new RuntimeException("해당 ID의 포스팅을 찾지 못했습니다: postId=" + postId);
        }
    }

    @Override
    public List<Image> getImagesByPostId(Long postId) {
        return imageRepository.findByPost_PostId(postId);
    }

    @Override
    public Optional<Image> getImageById(Long imageId) {
        return imageRepository.findById(imageId);
    }

    @Override
    @Transactional
    public void deleteImage(Long imageId) {
        Optional<Image> imageOptional = imageRepository.findById(imageId);
        if (imageOptional.isPresent()) {
            imageRepository.delete(imageOptional.get());
        } else {
            throw new RuntimeException("해당 ID의 이미지를 찾지 못했습니다: " + imageId);
        }
    }
}
