package ku.cs.tbm.controller;

import ku.cs.tbm.entity.Customer;
import ku.cs.tbm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping
    public String getAllCustomer(Model model){
        model.addAttribute("customers",customerService.getAllCustomer());
        return "customer-all";
    }
}
