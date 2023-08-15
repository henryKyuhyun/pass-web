package projectpassweb.service.user;

import org.springframework.stereotype.Service;
import projectpassweb.repository.user.UserGroupMappingRepository;

import java.util.List;

@Service
public class UserGroupMappingService {

    private final UserGroupMappingRepository userGroupMappingRepository;

    public UserGroupMappingService(UserGroupMappingRepository userGroupMappingRepository) {
        this.userGroupMappingRepository = userGroupMappingRepository;
    }

    public List<String> getAllUserGroupIds() {
//        user Group id 를 중복없이 User group id 순으로 조회합니다.
        return userGroupMappingRepository.findDistinctUserGroupId();
    }
}
