package projectpassweb.service.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import projectpassweb.repository.user.UserStatus;

@Getter
@Setter
@ToString
public class User {
    private String userId;
    private String userName;
    private UserStatus status;
}
