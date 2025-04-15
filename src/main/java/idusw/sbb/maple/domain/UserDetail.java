package idusw.sbb.maple.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetail implements UserDetails {

    private Long userIdx;
    private String username;
    private String password;
    private String nickname;
    private LocalDateTime createdAt;
    private List<Route> routes = new ArrayList<>();
    private List<Board> boards = new ArrayList<>();
    private List<Comment> comments = new ArrayList<>();

    public UserDetail(User user) {
        this.userIdx = user.getUserIdx();
        this.username = user.getUserId();
        this.password = user.getUserPassword();
        this.nickname = user.getNickname();
        this.createdAt = user.getCreatedAt();
        this.routes = user.getRoutes();
        this.boards = user.getBoards();
        this.comments = user.getComments();

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
