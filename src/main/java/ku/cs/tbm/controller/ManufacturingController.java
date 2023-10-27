package ku.cs.tbm.controller;

import ku.cs.tbm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/manufacturing")
public class ManufacturingController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String getAllManufacturingOrders(Model model){
        model.addAttribute("orders",orderService.getManufacturingOrders());
        return "manufacturing-order";
    }

    @GetMapping("/{id}")
    public String printOrderPurchase(@PathVariable UUID id, Model model){
        model.addAttribute("order",orderService.getOrderById(id));
        return "print-purchaseOrder";
    }
}
