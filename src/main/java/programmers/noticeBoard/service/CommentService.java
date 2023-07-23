package programmers.noticeBoard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import programmers.noticeBoard.domain.Comment;
import programmers.noticeBoard.domain.Member;
import programmers.noticeBoard.domain.Post;
import programmers.noticeBoard.dto.CommentDto;
import programmers.noticeBoard.repository.CommentRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostService postService;

    public void create(Long postId, Member member, CommentDto.Request request) {
        Post post = postService.findPostById(postId);

        Comment comment = new Comment(request.getContent(), member, post);
        post.addComment(comment);
        commentRepository.save(comment);
    }
}
