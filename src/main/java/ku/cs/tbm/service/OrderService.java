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

    @Autowired
    private ReceiptRepository receiptRepository;

    private UUID id;

    public void createNewOrder(String memberUsername){

        Member member = memberRepository.findByUsername(memberUsername);

        PurchaseOrder newOrder = new PurchaseOrder();
        newOrder.setStatus("ORDER");
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
            if(!purchaseOrder.getStatus().equals("ORDER") && !purchaseOrder.getStatus().equals("FINISH")){
                orders.add(purchaseOrder);
            }
        }
        return orders;
    }

    public List<PurchaseOrder> getManufacturingOrders(){
        List<PurchaseOrder> orders = new ArrayList<>();
        for(PurchaseOrder purchaseOrder : purchaseOrderRepository.findAll()){
            if(purchaseOrder.getStatus().equals("MANUFACTURING")){
                orders.add(purchaseOrder);
            }
        }
        return orders;
    }

    public List<PurchaseOrder> getDeliveryOrders(){
        List<PurchaseOrder> orders = new ArrayList<>();
        for(PurchaseOrder purchaseOrder : purchaseOrderRepository.findAll()){
            if(purchaseOrder.getStatus().equals("DELIVERY") || purchaseOrder.getStatus().equals("PAYMENTCOMPLETE")){
                orders.add(purchaseOrder);
            }
        }
        return orders;
    }

    public List<PurchaseOrder> getClaimOrders(){
        List<PurchaseOrder> orders = new ArrayList<>();
        for(PurchaseOrder purchaseOrder : purchaseOrderRepository.findAll()){
            if(purchaseOrder.getStatus().equals("CLAIM") || purchaseOrder.getStatus().equals("REPAIR")){
                orders.add(purchaseOrder);
            }
        }
        return orders;
    }

    public void submitOrder(){
        PurchaseOrder currentOrder =
                purchaseOrderRepository.findById(id).get();
        currentOrder.setOrderDate(LocalDateTime.now());
        currentOrder.setStatus("CONFIRM");
        purchaseOrderRepository.save(currentOrder);
        id = null;

    }
    public void giveStatus(UUID id,String orderStatus){
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id).get();
        purchaseOrder.setStatus(orderStatus);
        purchaseOrderRepository.save(purchaseOrder);
    }


}
