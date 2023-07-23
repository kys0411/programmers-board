package programmers.noticeBoard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import programmers.noticeBoard.domain.Member;
import programmers.noticeBoard.dto.MemberDto;
import programmers.noticeBoard.repository.MemberRepository;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberDto.Response get(Long memberId) {
        Member member = findMemberById(memberId);
        return toDto(member);
    }

    public Member findMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("회원이 존재하지 않습니다."));
    }

    private MemberDto.Response toDto(Member member) {
        return MemberDto.Response.builder()
                .id(member.getId())
                .username(member.getUsername())
                .profileImage(member.getProfileImage())
                .selfIntro(member.getSelfIntro())
                .createdAt(member.getCreatedAt())
                .updatedAt(member.getUpdatedAt())
                .build();
    }
}
