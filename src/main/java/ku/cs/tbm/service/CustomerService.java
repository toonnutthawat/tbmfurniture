package ku.cs.tbm.service;

import ku.cs.tbm.common.CreditStatus;
import ku.cs.tbm.entity.Customer;
import ku.cs.tbm.entity.Product;
import ku.cs.tbm.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }

    public List<Customer> getALlCustomerHasGoodCredit(){
        List<Customer> list = new ArrayList<>();
        for(Customer customer: customerRepository.findAll()){
            if(customer.getCredit().equals("GOODCREDIT")){
                list.add(customer);
            }
        }
        return list;
    }

    public void addCustomer(Customer customer){
        Customer record = new Customer();
        record.setName(customer.getName());
        record.setCompanyName(customer.getCompanyName());
        record.setNumber(customer.getNumber());
        record.setAddress(customer.getAddress());
        record.setCredit("NOTSPECIFIED");

        customerRepository.save(record);
    }

    public Customer getCustomerById(UUID id){
        return customerRepository.findById(id).get();
    }

    public void giveGoodCredit(UUID id){
        Customer customer =  customerRepository.findById(id).get();
        customer.setCredit("GOODCREDIT");
        customerRepository.save(customer);
    }

    public void giveBadCredit(UUID id){
        Customer customer =  customerRepository.findById(id).get();
        customer.setCredit("BADCREDIT");
        customerRepository.save(customer);
    }


}
