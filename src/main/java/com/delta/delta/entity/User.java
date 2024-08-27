package com.delta.delta.entity;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String profileImage;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

    // postLike, comment와 동일한 논리로 인해 include 도입
    @ElementCollection
    @CollectionTable(name = "user_followers", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "follower_id")
    @JsonIncludeProperties({"userId", "username", "firstname", "lastname", "profileImage"})
    private Set<Long> followers = new HashSet<>();


    @ElementCollection
    @CollectionTable(name = "user_followings", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "following_id")
    @JsonIncludeProperties({"userId", "username", "firstname", "lastname", "profileImage"})
    private Set<Long> followings = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    @ToString.Exclude
    private List<Post> posts;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
