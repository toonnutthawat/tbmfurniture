package ku.cs.tbm.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class Claim {

    @Id
    @GeneratedValue
    private UUID Id;

    @OneToOne
    private PurchaseOrder purchaseOrder;

    private LocalDateTime claimDate;

    private String claimDetail;
}
