package ku.cs.tbm.controller;

import ku.cs.tbm.common.OrderStatus;
import ku.cs.tbm.entity.Claim;
import ku.cs.tbm.service.ClaimService;
import ku.cs.tbm.service.OrderService;
import ku.cs.tbm.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/delivery")
public class DeliveryController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ReceiptService receiptService;

    @Autowired
    private ClaimService claimService;

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
        orderService.giveStatus(id,"PAYMENTCOMPLETE");
        receiptService.receipt(id);
        return "redirect:/delivery";
    }

    @GetMapping("/{id}/claim")
    public String claim(@PathVariable UUID id,Model model){
        model.addAttribute("order",orderService.getOrderById(id));
        return "claim-page";
    }

    @PostMapping("/{id}/claimed")
    public String submitClaim(@PathVariable UUID id, @ModelAttribute Claim claim, Model model){
        claimService.claim(id,claim);
        orderService.giveStatus(id,"CLAIM");
        return "redirect:/claim";
    }

    @GetMapping("/{id}/receipt")
    public String printReceipt(@PathVariable UUID id,Model model){
        model.addAttribute("receipt",receiptService.getReceiptById(id));
        return "print-receipt";
    }

}
