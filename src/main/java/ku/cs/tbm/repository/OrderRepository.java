package ku.cs.tbm.repository;

import ku.cs.tbm.entity.OrderKey;
import ku.cs.tbm.entity.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderList, OrderKey> {
}
