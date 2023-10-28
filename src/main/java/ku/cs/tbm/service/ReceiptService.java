package ku.cs.tbm.service;

import ku.cs.tbm.entity.PurchaseOrder;
import ku.cs.tbm.entity.Receipt;
import ku.cs.tbm.repository.PurchaseOrderRepository;
import ku.cs.tbm.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ReceiptService {
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private ReceiptRepository receiptRepository;

    public void receipt(UUID id){
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id).get();

        Receipt receipt = new Receipt();
        receipt.setPurchaseOrder(purchaseOrder);
        receipt.setDeliveryDate(LocalDateTime.now());
        receiptRepository.save(receipt);
    }

    public Receipt getReceiptById(UUID id){
        for(Receipt receipt : receiptRepository.findAll()){
            if(receipt.getPurchaseOrder().getId().equals(id)){
                return receipt;
            }
        }
        return null;
    }
}
