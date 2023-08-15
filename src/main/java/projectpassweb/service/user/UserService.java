package projectpassweb.service.user;

import org.springframework.stereotype.Service;
import projectpassweb.repository.user.UserEntity;
import projectpassweb.repository.user.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {this.userRepository = userRepository;}

    public User getUser(final String userId){
//        userId 를 조건으로 사용자 정보 조회 , 프로필에 노출할 사용자의 이름이필요
        UserEntity userEntity = userRepository.findByUserId(userId);
        return UserModelMapper.INSTANCE.map(userEntity);
    }
}
