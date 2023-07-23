package programmers.noticeBoard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import programmers.noticeBoard.dto.MemberDto;
import programmers.noticeBoard.service.SignService;

@Controller
@RequiredArgsConstructor
public class SignController {

    private final SignService signService;

    @GetMapping(value = "/join")
    public String joinForm(Model model) {
        model.addAttribute("memberJoinDto", new MemberDto.Request());
        return "join";
    }

    @PostMapping(value = "/join")
    public String join(MemberDto.Request request, Model model){
        signService.signUp(request);
        return "redirect:/login";
    }

    @GetMapping(value = "/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "login";
    }
}
