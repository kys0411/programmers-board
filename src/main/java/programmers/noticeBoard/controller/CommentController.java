package programmers.noticeBoard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import programmers.noticeBoard.domain.Member;
import programmers.noticeBoard.dto.CommentDto;
import programmers.noticeBoard.service.CommentService;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/new/{id}")
    public String newComment(@AuthenticationPrincipal Member member,
                             @PathVariable Long id,
                             CommentDto.Request request) {
        commentService.create(id, member, request);
        return "redirect:/post/" + id;
    }

    @GetMapping("/delete/{postId}/{commentId}")
    public String deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
        commentService.deleteComment(postId, commentId);
        return "redirect:/post/" + postId;
    }
}
