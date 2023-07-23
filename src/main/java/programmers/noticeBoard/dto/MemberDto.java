package programmers.noticeBoard.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class MemberDto {

    @Getter @Setter
    public static class Request {

        private String username;

        private String password;

        private String profileImage;

        private String selfIntro;
    }

    @Getter @Setter
    public static class Profile {

        private String profileImage;

        private String selfIntro;
    }

    @Getter
    public static class Response {

        private Long id;

        private String username;

        private String profileImage;

        private String selfIntro;

        private LocalDateTime createdAt;

        private LocalDateTime updatedAt;

        @Builder
        public Response(Long id,
                        String username,
                        String profileImage,
                        String selfIntro,
                        LocalDateTime createdAt,
                        LocalDateTime updatedAt) {
            this.id = id;
            this.username = username;
            this.profileImage = profileImage;
            this.selfIntro = selfIntro;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }
    }
}
