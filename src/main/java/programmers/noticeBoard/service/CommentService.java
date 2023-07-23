package programmers.noticeBoard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import programmers.noticeBoard.domain.Comment;
import programmers.noticeBoard.domain.Member;
import programmers.noticeBoard.domain.Post;
import programmers.noticeBoard.dto.CommentDto;
import programmers.noticeBoard.repository.CommentRepository;
import java.util.NoSuchElementException;

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

    public void deleteComment(Long postId, Long commentId) {
        Post post = postService.findPostById(postId);
        Comment comment = commentRepository.findById(commentId)
                        .orElseThrow(() -> new NoSuchElementException("댓글이 없습니다"));
        post.deleteComment(comment);
        commentRepository.deleteById(commentId);
    }
}
