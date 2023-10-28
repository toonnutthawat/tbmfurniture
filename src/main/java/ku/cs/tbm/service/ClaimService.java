package ku.cs.tbm.service;

import ku.cs.tbm.entity.Claim;
import ku.cs.tbm.entity.PurchaseOrder;
import ku.cs.tbm.entity.Receipt;
import ku.cs.tbm.repository.ClaimRepository;
import ku.cs.tbm.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ClaimService {

    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    public void claim(UUID id,Claim claim){
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id).get();

        Claim newClaim = new Claim();
        newClaim.setPurchaseOrder(purchaseOrder);
        newClaim.setClaimDate(LocalDateTime.now());
        newClaim.setClaimDetail(claim.getClaimDetail());
        claimRepository.save(newClaim);
    }

    public Claim getClaimById(UUID id){
        for(Claim claim : claimRepository.findAll()){
            if(claim.getPurchaseOrder().getId().equals(id)){
                return claim;
            }
        }
        return null;
    }
}
