package programmers.noticeBoard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import programmers.noticeBoard.domain.Member;
import programmers.noticeBoard.dto.MemberDto;
import programmers.noticeBoard.service.MemberService;
import programmers.noticeBoard.service.PostService;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PostService postService;

    @GetMapping(value = "/{id}")
    public String getMember(@AuthenticationPrincipal Member member,
                            @PageableDefault(size = 5, sort="id", direction = Sort.Direction.DESC) Pageable pageable,
                            @PathVariable Long id, Model model) {

        if (member != null) {
            model.addAttribute("auth_member", member.getUsername());
        }

        model.addAttribute("member", memberService.get(id));
        model.addAttribute("boardList", postService.getAllByMember(pageable, id));
        return "memberProfile";
    }

    @GetMapping(value = "/update/{id}")
    public String getMemberUpdateForm(@PathVariable Long id, Model model) {
        MemberDto.Response member = memberService.get(id);

        model.addAttribute("memberUpdateDto", new MemberDto.Profile());
        model.addAttribute("memberInfoDto", member);
        return "updateProfile";
    }

    @PostMapping(value = "/update/{id}")
    public String updateMember(@PathVariable Long id,
                               @RequestParam(value = "profileImg", required = false) MultipartFile multipartFile,
                               MemberDto.Profile request) {
        memberService.updateProfile(id, multipartFile, request);
        return "redirect:/member/" + id;
    }
}
