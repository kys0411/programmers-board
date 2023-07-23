package programmers.noticeBoard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import programmers.noticeBoard.domain.Member;
import programmers.noticeBoard.domain.Post;
import programmers.noticeBoard.dto.PostDto;
import programmers.noticeBoard.repository.PostRepository;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;

    public PostDto.Response create(PostDto.Request request, Member member) {
        request.setMember(member);
        Post post = new Post(request.getTitle(), request.getContent(), request.getMember());

        Post savePost = postRepository.save(post);
        return toDto(savePost);
    }

    private PostDto.Response toDto(Post post) {
        return PostDto.Response.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .member(post.getMember())
                .commentList(post.getCommentList())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }
}
