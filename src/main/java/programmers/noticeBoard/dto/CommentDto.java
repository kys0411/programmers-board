package programmers.noticeBoard.dto;

import lombok.Getter;
import lombok.Setter;
import programmers.noticeBoard.domain.Member;
import programmers.noticeBoard.domain.Post;

import java.time.LocalDateTime;

public class CommentDto {

    @Getter @Setter
    public static class Request {

        private String content;

    }

    @Getter
    public static class Response {

        private Long id;

        private String content;

        private Member member;

        private Post post;

        private LocalDateTime createdAt;

        private LocalDateTime updatedAt;
    }
}
