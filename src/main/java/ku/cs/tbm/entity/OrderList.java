package ku.cs.tbm.entity;

import jakarta.persistence.*;
import ku.cs.tbm.common.OrderStatus;
import lombok.Data;

@Data
@Entity
public class OrderList {

    @EmbeddedId
    private OrderKey id;

    private int quantity;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private PurchaseOrder purchaseOrder;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    public double getSubTotal(){
        return product.getPrice() * quantity;
    }



}
