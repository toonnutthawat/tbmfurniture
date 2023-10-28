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


    private LocalDateTime orderDate;


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
    public boolean checkConfirmStatus(){
        if(status.equals(OrderStatus.CONFIRM)){
            return true;
        }
        return false;
    }

    public boolean checkManufacturingStatus(){
        if(status.equals(OrderStatus.MANUFACTURING) || status.equals(OrderStatus.REPAIR)){
            return true;
        }
        return false;
    }
    public boolean checkDeliveryStatus(){
        if(status.equals(OrderStatus.DELIVERY)){
            return true;
        }
        return false;
    }

    public boolean checkPaymentCompleteStatus(){
        if(status.equals(OrderStatus.PAYMENTCOMPLETE)){
            return true;
        }
        return false;
    }

    public boolean checkClaimStatus(){
        if(status.equals(OrderStatus.CLAIM)){
            return true;
        }
        return false;
    }


    public boolean checkFinishStatus(){
        if(status.equals(OrderStatus.FINISH)){
            return true;
        }
        return false;
    }
}
