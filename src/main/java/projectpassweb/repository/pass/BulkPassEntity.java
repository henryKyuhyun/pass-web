package projectpassweb.repository.pass;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class BulkPassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    @Enumerated(EnumType.STRING)
    private BulkPassStatus status;
    private Integer count;

    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

    public void setEndedAt(Integer period){
        if(period == null){
            return;
        }
        this.endedAt = this.startedAt.plusDays(period);
    }

    public void setEndedAt(LocalDateTime endedAt){
        this.endedAt = endedAt;
    }

}
