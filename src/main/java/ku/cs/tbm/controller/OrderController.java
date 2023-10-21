package ku.cs.tbm.controller;

import ku.cs.tbm.entity.Customer;
import ku.cs.tbm.entity.OrderList;
import ku.cs.tbm.entity.Product;
import ku.cs.tbm.entity.PurchaseOrder;
import ku.cs.tbm.service.CustomerService;
import ku.cs.tbm.service.OrderService;
import ku.cs.tbm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String viewOderListPage(Model model){
        model.addAttribute("cart",orderService.getCurrentOrder());
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
                         Model model){
        orderService.order(customer,product,orderList);
        return "home";
    }






}
