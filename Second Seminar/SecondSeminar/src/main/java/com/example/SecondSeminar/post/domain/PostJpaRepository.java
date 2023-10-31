package com.example.SecondSeminar.post.domain;

import com.example.SecondSeminar.member.domain.Member;
import com.example.SecondSeminar.post.exception.PostExceptionType;
import com.example.SecondSeminar.post.exception.PostException;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<Post, Long> {
    default Post findByIdOrThrowException(Long postId) {
        return findById(postId).orElseThrow(() -> new PostException(PostExceptionType.NOT_FOUND_POST));
    }

    List<Post> findAllByMemberId(Long memberId);

    List<Post> findAllByMember(Member member);
}
