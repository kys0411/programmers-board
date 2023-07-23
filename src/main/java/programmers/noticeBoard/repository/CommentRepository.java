package programmers.noticeBoard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import programmers.noticeBoard.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
