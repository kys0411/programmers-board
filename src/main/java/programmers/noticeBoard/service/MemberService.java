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
    private final FileUploadService fileUploadService;

    public MemberDto.Response get(Long memberId) {
        Member member = findMemberById(memberId);
        return toDto(member);
    }

    public MemberDto.Response updateProfile(Long memberId, MultipartFile multipartFile, MemberDto.Profile request) {
        Member member = findMemberById(memberId);

        if (!multipartFile.getOriginalFilename().equals("")) {
            if (member.getProfileImage() != null) {
                fileUploadService.deleteImage(member.getProfileImage());
            }
            String fileName = fileUploadService.uploadImage(multipartFile);
            member.updateProfileImage(fileName);
        }
        member.updateSelfIntro(request.getSelfIntro());

        return toDto(member);
    }

    public void deleteUser(Long memberId) {
        Member member = findMemberById(memberId);
        memberRepository.deleteById(memberId);
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
