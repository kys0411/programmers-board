package programmers.noticeBoard.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import programmers.noticeBoard.domain.constant.Role;

import javax.persistence.*;
import java.util.Collection;

@Entity
@NoArgsConstructor
@Getter
public class Member extends BaseTime implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private String profileImage;

    private String selfIntro;

    @Enumerated(EnumType.STRING)
    private Role userRole = Role.USER;

    public Member(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

    public void updateProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void deleteProfileImage() {
        this.profileImage = null;
    }

    public void updateSelfIntro(String selfIntro) {
        this.selfIntro = selfIntro;
    }
}
