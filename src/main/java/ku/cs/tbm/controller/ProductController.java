package ku.cs.tbm.controller;

import ku.cs.tbm.entity.Product;
import ku.cs.tbm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String getAllMenus(Model model) {
        model.addAttribute("product", productService.getAllProduct());
        return "product-all";
    }

    @GetMapping("/add")
    public String getProductAdd(Model model){;
        model.addAttribute("product", productService.getAllProduct());
        return "product-add";
    }

    @PostMapping("/add")
    public String createProduct(@ModelAttribute Product product, Model model){
        productService.createProduct(product);
        model.addAttribute("product", productService.getAllProduct());
        return "redirect:/products";
    }
}
