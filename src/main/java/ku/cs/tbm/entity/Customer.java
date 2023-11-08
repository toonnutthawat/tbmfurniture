package ku.cs.tbm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import ku.cs.tbm.common.CreditStatus;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Customer implements Comparable<Customer>{
    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String companyName;
    private String number;
    private String address;

    private String credit;

    public String getCompanyName() {
        return companyName;
    }

    @OneToMany(mappedBy = "customer")
    private List<PurchaseOrder> purchaseOrderList = new ArrayList<>();

    @Override
    public int compareTo(Customer other) {
        return this.name.compareTo(other.getName());
    }
}
