package programmers.noticeBoard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import programmers.noticeBoard.domain.Member;
import programmers.noticeBoard.domain.Post;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findById(Long id);

    List<Post> findAll();

    Page<Post> findAll(Pageable pageable);

    Page<Post> findAllByMember(Pageable pageable, Member member);
}
