package web.tech.project.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.tech.project.db.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
