package projectpassweb.service.pass;

import org.springframework.stereotype.Service;
import projectpassweb.controller.admin.BulkPassRequest;
import projectpassweb.repository.packaze.PackageEntity;
import projectpassweb.repository.packaze.PackageRepository;
import projectpassweb.repository.pass.BulkPassEntity;
import projectpassweb.repository.pass.BulkPassRepository;

import java.util.List;

@Service
public class BulkPassService {

    private final BulkPassRepository bulkPassRepository;
    private final PackageRepository packageRepository;

    public BulkPassService(BulkPassRepository bulkPassRepository, PackageRepository packageRepository) {
        this.bulkPassRepository = bulkPassRepository;
        this.packageRepository = packageRepository;
    }

    public List<BulkPass> getAllBulkPasses(){
//        startedAt 역순으로 bulkpass조회
        List<BulkPassEntity> bulkPassEntities = bulkPassRepository.findAllOrderByStartedAtDesc();
        return BulkPassModelMapper.INSTANCE.map(bulkPassEntities);
    }

    public void addBulkPass(BulkPassRequest bulkPassRequest){
//        bulkPassRequest 를 기반으로 passEntity를 생성하여 DB에 저장
        PackageEntity packageEntity = packageRepository.findById(bulkPassRequest.getPackageSeq()).orElseThrow();

        BulkPassEntity bulkPassEntity = BulkPassModelMapper.INSTANCE.map(bulkPassRequest);
        bulkPassEntity.setStatus(BulkPassStatus.READY);
        bulkPassEntity.setCount(packageEntity.getCount());
        bulkPassEntity.setEndedAt(packageEntity.getPeriod());

        bulkPassRepository.save(bulkPassEntity);
    }
}
