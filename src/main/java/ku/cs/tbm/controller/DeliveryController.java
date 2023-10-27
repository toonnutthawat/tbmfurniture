package ku.cs.tbm.controller;

import ku.cs.tbm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/delivery")
public class DeliveryController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String getAllDeliveryOrders(Model model){
        model.addAttribute("orders",orderService.getDeliveryOrders());
        return "delivery-order";
    }

    @GetMapping("/{id}")
    public String manageDeliveryOrders(@PathVariable UUID id, Model model){
        model.addAttribute("order",orderService.getOrderById(id));
        return "manage-delivery";
    }

    @PostMapping(value = "/{id}/managed",params = "givePaymentCompleteStatus")
    public String givePaymentCompleteStatus(@PathVariable UUID id,Model model){
        orderService.givePaymentCompleteStatus(id);
        orderService.receipt(id);
        return "redirect:/delivery";
    }

    @GetMapping("/{id}/receipt")
    public String printReceipt(@PathVariable UUID id,Model model){
        model.addAttribute("receipt",orderService.getReceiptById(id));
        return "print-receipt";
    }

}
