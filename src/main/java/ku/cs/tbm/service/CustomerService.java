package ku.cs.tbm.service;

import ku.cs.tbm.common.CreditStatus;
import ku.cs.tbm.entity.Customer;
import ku.cs.tbm.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }

    public void addCustomer(Customer customer){
        Customer record = new Customer();
        record.setName(customer.getName());
        record.setCompanyName(customer.getCompanyName());
        record.setNumber(customer.getNumber());
        record.setAddress(customer.getAddress());
        record.setCredit(CreditStatus.NotSpecified);

        customerRepository.save(record);
    }

}
