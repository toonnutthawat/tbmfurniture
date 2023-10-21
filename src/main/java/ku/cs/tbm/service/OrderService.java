package ku.cs.tbm.service;

import ku.cs.tbm.common.OrderStatus;
import ku.cs.tbm.entity.*;
import ku.cs.tbm.repository.CustomerRepository;
import ku.cs.tbm.repository.OrderRepository;
import ku.cs.tbm.repository.ProductRepository;
import ku.cs.tbm.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private UUID id;

    public void createNewOrder(){
        PurchaseOrder newOrder = new PurchaseOrder();
        newOrder.setStatus(OrderStatus.ORDER);
        PurchaseOrder record = purchaseOrderRepository.save(newOrder);
        id = record.getId();
    }

    public void order(Customer customer,Product product,OrderList orderList){
        if(id == null){
            createNewOrder();
        }

        PurchaseOrder currentOrder = purchaseOrderRepository.findById(id).get();
        currentOrder.setCustomer(customer);
        Product product1 = productRepository.findById(product.getId()).get();

        OrderList newOrderList = new OrderList();
        newOrderList.setId(new OrderKey(id,product.getId()));
        newOrderList.setPurchaseOrder(currentOrder);
        newOrderList.setProduct(product);
        newOrderList.setQuantity(orderList.getQuantity());
        orderRepository.save(newOrderList);

    }

    public PurchaseOrder getCurrentOrder() {
        if (id == null)
            createNewOrder();
        return purchaseOrderRepository.findById(id).get();
    }

    public List<PurchaseOrder> getAllOrders(){
        return purchaseOrderRepository.findAll();
    }

    public void submitOrder(){
        PurchaseOrder currentOrder =
                purchaseOrderRepository.findById(id).get();
        currentOrder.setOrderDate(LocalDateTime.now());
        currentOrder.setStatus(OrderStatus.CONFIRM);
        purchaseOrderRepository.save(currentOrder);
        id = null;

    }

}
