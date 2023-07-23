package programmers.noticeBoard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import programmers.noticeBoard.domain.Member;
import programmers.noticeBoard.dto.MemberDto;
import programmers.noticeBoard.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class SignService {

    private final MemberRepository memberRepository;

    public void signUp(MemberDto.Request request){
        if (memberRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        Member member = new Member(request.getUsername(), request.getPassword());

        memberRepository.save(member);
    }
}
