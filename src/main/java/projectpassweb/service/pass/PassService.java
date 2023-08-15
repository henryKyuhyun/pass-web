package projectpassweb.service.pass;

import org.springframework.stereotype.Service;
import projectpassweb.repository.pass.PassEntity;
import projectpassweb.repository.pass.PassRepository;

import java.util.List;

@Service
public class PassService {

    private final PassRepository passRepository;

    public PassService(PassRepository passRepository) {
        this.passRepository = passRepository;
    }

    public List<Pass> getPasses(final String userId){
//        userId를 조건으로 Pass를 조회한다. 이때 PacageSeq에 맞는 package 정보도 필요
        final List<PassEntity> passEntities = passRepository.findByUserId(userId);
        return PassModelMapper.INSTANCE.map(passEntities);


    }
}
