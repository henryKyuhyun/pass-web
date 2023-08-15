package projectpassweb.repository.pass;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PassRepository extends JpaRepository<PassEntity,Integer> {
    @Query(value = "select p from PassEntity p " +
                   "join fetch p.packageEntity " +
                   "where p.userId = :userId " +
                   "order by p.endedAt desc nulls first ")  //종료 일시가 없는건 무기한이란 뜻이고 이걸 상단에올리자.
    List<PassEntity> findByUserId(String userId);
}
