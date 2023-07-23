package programmers.noticeBoard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import programmers.noticeBoard.domain.Member;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findById(Long id);

    Optional<Member> findByUsername(String username);
}
