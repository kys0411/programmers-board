package programmers.noticeBoard.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Post extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Comment> commentList = new ArrayList<>();

    public Post(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
    }

    public void updatePost(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void addComment(Comment comment) {
        commentList.add(comment);
    }

    public void deleteComment(Comment comment) {
        commentList.remove(comment);
    }
}
