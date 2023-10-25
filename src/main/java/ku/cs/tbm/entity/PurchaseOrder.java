package ku.cs.tbm.entity;

import jakarta.persistence.*;
import ku.cs.tbm.common.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class PurchaseOrder {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;


    private LocalDateTime OrderDate;

    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "purchaseOrder")
    private List<OrderList> orderLists = new ArrayList<>();

    public double getTotal() {
        double total = 0;
        for (OrderList order : orderLists)
            total += order.getSubTotal();
        return total;
    }

    public boolean getTotalQuantity(){
        double total = 0;
        for (OrderList order : orderLists)
            total += order.getQuantity();
        if(total < 3){
            return true;
        }
        else {
            return false;
        }
    }
}
