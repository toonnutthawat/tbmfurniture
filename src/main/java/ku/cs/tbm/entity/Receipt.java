package ku.cs.tbm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class Receipt {
    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne
    private PurchaseOrder purchaseOrder;

    private LocalDateTime deliveryDate;

}
