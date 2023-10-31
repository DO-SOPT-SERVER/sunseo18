package com.example.SecondSeminar.domain.repository;

import com.example.SecondSeminar.domain.Member;
import com.example.SecondSeminar.domain.Post;
import com.example.SecondSeminar.exception.NotFoundException;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<Post, Long> {
    default Post findByIdOrThrowException(Long postId) {
        return findById(postId).orElseThrow(() -> new NotFoundException("존재하지 않는 게시글입니다"));
    }

    List<Post> findAllByMemberId(Long memberId);

    List<Post> findAllByMember(Member member);
}
