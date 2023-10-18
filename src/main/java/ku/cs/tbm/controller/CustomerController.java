package ku.cs.tbm.controller;

import ku.cs.tbm.entity.Customer;
import ku.cs.tbm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/add")
    public String getSignupPage() {
        return "customer-add";
    }

    @PostMapping("/add")
    public String addCustomer(@ModelAttribute Customer customer, Model model){
        customerService.addCustomer(customer);
        //model.addAttribute("customer",customerService.getAllCustomer());
        return "redirect:/products";
    }

    @GetMapping("/{id}")
    public String getCustomerById(@PathVariable UUID id,Model model)
    {
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer",customer);
        return "check-credit";
    }

    @PostMapping(value = "/{id}",params = "giveGoodCredit")
    public String giveGoodCredit(@PathVariable UUID id,Model model)
    {
        customerService.giveGoodCredit(id);
        return "redirect:/customers";
    }

    @PostMapping(value = "/{id}",params = "giveBadCredit")
    public String giveBadCredit(@PathVariable UUID id,Model model)
    {
        customerService.giveBadCredit(id);
        return "redirect:/customers";
    }

    @GetMapping
    public String getAllCustomer(Model model){
        model.addAttribute("customers",customerService.getAllCustomer());
        return "customer-all";
    }
}
