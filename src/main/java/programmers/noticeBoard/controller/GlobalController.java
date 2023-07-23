package programmers.noticeBoard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import programmers.noticeBoard.domain.Member;
import programmers.noticeBoard.service.PostService;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class GlobalController {

    private final PostService postService;

    @GetMapping(value = "/")
    public String mainPage(@PageableDefault(size = 8, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                           @AuthenticationPrincipal Member member, Model model) {
        model.addAttribute("boardList", postService.getAll(pageable));

        if (member != null) {
            model.addAttribute("member", member);
        }

        return "main";
    }

    @GetMapping(value = "/search")
    public String searchPost(@PageableDefault(size = 8, sort="id", direction = Sort.Direction.DESC) Pageable pageable,
                             @RequestParam String keyword,
                             @AuthenticationPrincipal Member member,
                             Model model) {
        model.addAttribute("boardList", postService.searchPost(pageable, keyword));
        model.addAttribute("keyword", keyword);

        if (member != null){
            model.addAttribute("member", member.getUsername());
        }

        return "search";
    }
}
