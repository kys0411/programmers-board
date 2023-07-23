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
    private final MemberService memberService;

    public PostDto.Response create(PostDto.Request request, Member member) {
        request.setMember(member);
        Post post = new Post(request.getTitle(), request.getContent(), request.getMember());

        Post savePost = postRepository.save(post);
        return toDto(savePost);
    }

    public PostDto.Response get(Long id) {
        Post post = findPostById(id);
        return toDto(post);
    }

    public Page<PostDto.Response> getAll(Pageable pageable) {
        return postRepository.findAll(pageable).map(this::toDto);
    }

    public Page<PostDto.Response> getAllByMember(Pageable pageable, Long memberId) {
        Member member = memberService.findMemberById(memberId);

        return postRepository.findAllByMember(pageable, member).map(this::toDto);
    }

    public PostDto.Response update(Long id, PostDto.Request request, Member member) {
        Post post = findPostById(id);

        if (member.getId() != post.getMember().getId()) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }

        post.updatePost(request.getTitle(), request.getContent());

        return toDto(post);
    }

    public void delete(Long id, Member member) {
        Post post = findPostById(id);

        if (member.getId() != post.getMember().getId()) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }

        postRepository.deleteById(id);
    }

    public Page<PostDto.Response> searchPost(Pageable pageable, String keyword) {
        return postRepository.findAllByTitleContains(pageable, keyword).map(this::toDto);
    }

    public Post findPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 게시글이 없습니다."));
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
