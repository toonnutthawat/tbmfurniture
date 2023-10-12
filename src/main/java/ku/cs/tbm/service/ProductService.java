package ku.cs.tbm.service;

import ku.cs.tbm.entity.Product;
import ku.cs.tbm.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public void createProduct(Product product){
        Product record = new Product();
        record.setName(product.getName());
        record.setPrice(product.getPrice());
        productRepository.save(record);
    }

}
