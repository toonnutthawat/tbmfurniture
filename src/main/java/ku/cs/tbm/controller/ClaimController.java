package ku.cs.tbm.controller;

import ku.cs.tbm.service.ClaimService;
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
@RequestMapping("/claim")
public class ClaimController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ClaimService claimService;

    @GetMapping
    private String getClaimAll(Model model){
        model.addAttribute("claims",orderService.getClaimOrders());
        return "claim-all";
    }

    @GetMapping("/{id}")
    private String getClaimById(@PathVariable UUID id, Model model){
        model.addAttribute("order",orderService.getOrderById(id));
        return "manage-claim";
    }



    @PostMapping("/{id}/repair")
    private String repairProduct(@PathVariable UUID id, Model model){
        orderService.giveRepairStatus(id);
        return "redirect:/claim";
    }

    @GetMapping("/{id}/printClaim")
    private String printClaim(@PathVariable UUID id, Model model){
        model.addAttribute("claim",claimService.getClaimById(id));
        return "print-claim";
    }

}
