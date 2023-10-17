package ku.cs.tbm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import ku.cs.tbm.common.CreditStatus;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String companyName;
    private String number;
    private String address;

    private CreditStatus credit;
}
