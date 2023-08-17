package projectpassweb.service.packaze;

import org.springframework.stereotype.Service;
import projectpassweb.repository.packaze.PackageEntity;
import projectpassweb.repository.packaze.PackageRepository;

import java.util.List;

@Service
public class PackageService {

    private final PackageRepository packageRepository;

    public PackageService(PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    public List<Package> getAllPackages() {
//        패키지 이름으로 정렬하여 모든 패키지 조회
        List<PackageEntity> bulkPassEntities = packageRepository.findAllByOrderByPackageName();
        return PackageModelMapper.INSTANCE.map(bulkPassEntities);
//        return PackageModelMapper.INSTANCE.map
    }

}
