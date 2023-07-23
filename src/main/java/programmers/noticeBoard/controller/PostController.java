package programmers.noticeBoard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import programmers.noticeBoard.domain.Member;
import programmers.noticeBoard.dto.CommentDto;
import programmers.noticeBoard.dto.PostDto;
import programmers.noticeBoard.service.PostService;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/new")
    public String newPostForm(Model model) {
        model.addAttribute("postNewDto", new PostDto.Request());
        return "newPost";
    }

    @PostMapping("/new")
    public String newPost(@AuthenticationPrincipal Member member, PostDto.Request request) {
        PostDto.Response post = postService.create(request, member);
        return "redirect:/post/" + post.getId();
    }

    @GetMapping("/{id}")
    public String getPost(@PathVariable Long id,
                          @AuthenticationPrincipal Member member,
                          Model model) {
        PostDto.Response post = postService.get(id);
        model.addAttribute("post", post);
        model.addAttribute("commentNewDto", new CommentDto.Request());
        model.addAttribute("commentList", post.getCommentList());

        if (member != null){
            model.addAttribute("member", member.getUsername());
        }
        return "post";
    }

    @GetMapping("/update/{id}")
    public String updatePostForm(@PathVariable Long id,
                                 Model model) {
        PostDto.Response post = postService.get(id);
        model.addAttribute("postInfoDto", post);
        return "updatePost";
    }

    @PostMapping("/update/{id}")
    public String updatePost(@PathVariable Long id, @AuthenticationPrincipal Member member, PostDto.Request request) {
        PostDto.Response post = postService.update(id, request, member);
        return "redirect:/post/" + post.getId();
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id, @AuthenticationPrincipal Member member) {
        postService.delete(id, member);
        return "redirect:/";
    }
}
