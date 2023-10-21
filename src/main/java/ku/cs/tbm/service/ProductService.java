package ku.cs.tbm.service;

import ku.cs.tbm.entity.Customer;
import ku.cs.tbm.entity.Product;
import ku.cs.tbm.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }



    public Product findProductById(UUID id){
        return productRepository.findById(id).get();
    }

    public void createProduct(Product product){
        Product record = new Product();
        record.setName(product.getName());
        record.setPrice(product.getPrice());
        productRepository.save(record);
    }

}
