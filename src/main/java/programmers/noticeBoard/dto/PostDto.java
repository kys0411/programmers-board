package programmers.noticeBoard.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import programmers.noticeBoard.domain.Comment;
import programmers.noticeBoard.domain.Member;

import java.time.LocalDateTime;
import java.util.List;

public class PostDto {

    @Getter @Setter
    public static class Request {
        private String title;

        private String content;

        private Member member;
    }

    @Getter
     public static class Response {
        private Long id;

        private String title;

        private String content;

        private Member member;

        private List<Comment> commentList;

        private LocalDateTime createdAt;

        private LocalDateTime updatedAt;

        @Builder
        public Response(Long id,
                        String title,
                        String content,
                        Member member,
                        List<Comment> commentList,
                        LocalDateTime createdAt,
                        LocalDateTime updatedAt) {
            this.id = id;
            this.title = title;
            this.content = content;
            this.member = member;
            this.commentList = commentList;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }
    }
}
