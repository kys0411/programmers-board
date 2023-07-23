package programmers.noticeBoard.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import programmers.noticeBoard.domain.Member;
import programmers.noticeBoard.repository.MemberRepository;
import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을수 없습니다."));

        return member;
    }
}
