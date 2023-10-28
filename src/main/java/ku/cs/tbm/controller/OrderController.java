package ku.cs.tbm.controller;

import ku.cs.tbm.common.OrderStatus;
import ku.cs.tbm.entity.Customer;
import ku.cs.tbm.entity.OrderList;
import ku.cs.tbm.entity.Product;
import ku.cs.tbm.entity.PurchaseOrder;
import ku.cs.tbm.service.CustomerService;
import ku.cs.tbm.service.OrderService;
import ku.cs.tbm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    ProductService productService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @GetMapping
    private String getAllInfo(Model model){
        model.addAttribute("customers",customerService.getALlCustomerHasGoodCredit());
        model.addAttribute("products",productService.getAllProduct());
        return "order-page";
    }

    @GetMapping("/orderList")
    public String viewCart(Model model, Authentication authentication){
        model.addAttribute("cart",orderService.getCurrentOrder(authentication.getName()));
        //model.addAttribute("cart",orderService.getOneOrderInCart(authentication.getName()));
        return "cart";
    }

    @PostMapping("/orderList")
    public String submitOrder(Model model){
        orderService.submitOrder();
        //model.addAttribute("three",false);
        return "home";
    }

    @PostMapping
    private String order(@ModelAttribute Customer customer,
                         @ModelAttribute Product product,
                         @ModelAttribute OrderList orderList,
                         Authentication authentication,
                         Model model){
        orderService.order(customer,product,orderList,authentication.getName());
        return "home";
    }

    @GetMapping("/allOrders")
    public String getAllOrders(Model model){
        model.addAttribute("orders",orderService.getConfirmOrders());
        return "order-all";
    }

    @GetMapping("/allOrders/{id}")
    public String manageOrder(@PathVariable UUID id, Model model){
        model.addAttribute("order",orderService.getOrderById(id));
        return "manage-order";
    }

    @PostMapping(value = "/allOrders/{id}/managed",params = "giveManufacturingStatus")
    public String giveManufacturingStatus(@PathVariable UUID id, Model model){
        orderService.giveStatus(id, OrderStatus.MANUFACTURING);
        return "redirect:/orders/allOrders";
    }

    @PostMapping(value = "/allOrders/{id}/managed",params = "giveDeliveryStatus")
    public String giveDeliveryStatus(@PathVariable UUID id, Model model){
        orderService.giveStatus(id,OrderStatus.DELIVERY);
        return "redirect:/orders/allOrders";
    }

    @PostMapping(value = "/allOrders/{id}/managed",params = "giveFinishStatus")
    public String giveFinishStatus(@PathVariable UUID id, Model model){
        orderService.giveStatus(id,OrderStatus.FINISH);
        return "redirect:/orders/allOrders";
    }






}
