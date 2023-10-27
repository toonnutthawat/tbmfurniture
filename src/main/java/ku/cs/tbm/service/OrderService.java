package ku.cs.tbm.service;

import jakarta.persistence.criteria.Order;
import ku.cs.tbm.common.OrderStatus;
import ku.cs.tbm.entity.*;
import ku.cs.tbm.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Autowired
    private MemberRepository memberRepository;

    private UUID id;

    public void createNewOrder(String memberUsername){

        Member member = memberRepository.findByUsername(memberUsername);

        PurchaseOrder newOrder = new PurchaseOrder();
        newOrder.setStatus(OrderStatus.ORDER);
        newOrder.setMember(member);
        PurchaseOrder record = purchaseOrderRepository.save(newOrder);
        id = record.getId();
    }

    public void order(Customer customer,Product product,OrderList orderList,String memberUsername){
        if(id == null){
            createNewOrder(memberUsername);
        }

        PurchaseOrder currentOrder = purchaseOrderRepository.findById(id).get();
        currentOrder.setCustomer(customer);
        //Product product1 = productRepository.findById(product.getId()).get();

        OrderList newOrderList = new OrderList();
        newOrderList.setId(new OrderKey(id,product.getId()));
        newOrderList.setPurchaseOrder(currentOrder);
        newOrderList.setProduct(product);
        newOrderList.setQuantity(orderList.getQuantity());
        orderRepository.save(newOrderList);

    }

    public PurchaseOrder getCurrentOrder(String memberUsername) {
        if (id == null)
            createNewOrder(memberUsername);
        return purchaseOrderRepository.findById(id).get();
    }

    public List<PurchaseOrder> getAllOrders(){
        return purchaseOrderRepository.findAll();
    }

    public PurchaseOrder getOrderById(UUID id){
        return purchaseOrderRepository.findById(id).get();
    }

    public PurchaseOrder getOneOrderInCart(String memberUsername){
        if (id == null)
            createNewOrder(memberUsername);
        else {
            PurchaseOrder p = new PurchaseOrder();
            for (PurchaseOrder purchaseOrder : purchaseOrderRepository.findAll()) {
                if (purchaseOrder.getId().equals(id) && purchaseOrder.getMember().getUsername().equals(memberUsername))
                    ;
                p = purchaseOrder;
            }
            return p;
        }
        return null;
    }

    public List<PurchaseOrder> getConfirmOrders(){
        List<PurchaseOrder> orders = new ArrayList<>();
        for(PurchaseOrder purchaseOrder : purchaseOrderRepository.findAll()){
            if(!purchaseOrder.getStatus().equals(OrderStatus.ORDER)){
                orders.add(purchaseOrder);
            }
        }
        return orders;
    }

    public List<PurchaseOrder> getManufacturingOrders(){
        List<PurchaseOrder> orders = new ArrayList<>();
        for(PurchaseOrder purchaseOrder : purchaseOrderRepository.findAll()){
            if(purchaseOrder.getStatus().equals(OrderStatus.MANUFACTURING)){
                orders.add(purchaseOrder);
            }
        }
        return orders;
    }

    public void submitOrder(){
        PurchaseOrder currentOrder =
                purchaseOrderRepository.findById(id).get();
        currentOrder.setOrderDate(LocalDateTime.now());
        currentOrder.setStatus(OrderStatus.CONFIRM);
        purchaseOrderRepository.save(currentOrder);
        id = null;

    }

    public void giveManufacturingStatus(UUID id){
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id).get();
        purchaseOrder.setStatus(OrderStatus.MANUFACTURING);
        purchaseOrderRepository.save(purchaseOrder);
    }

    public void giveDeliveryStatus(UUID id){
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id).get();
        purchaseOrder.setStatus(OrderStatus.DELIVERY);
        purchaseOrderRepository.save(purchaseOrder);
    }

    public void giveFinishStatus(UUID id){
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id).get();
        purchaseOrder.setStatus(OrderStatus.FINISH);
        purchaseOrderRepository.save(purchaseOrder);
    }

}
