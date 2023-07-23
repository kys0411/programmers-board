package programmers.noticeBoard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import programmers.noticeBoard.domain.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
