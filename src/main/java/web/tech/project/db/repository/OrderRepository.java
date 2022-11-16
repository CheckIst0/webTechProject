package web.tech.project.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import web.tech.project.db.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Transactional
    @Modifying
    @Query(value = "Update \"order\" set status_id = 2 where id = :id", nativeQuery = true)
    void paymentSuccess(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "Update \"order\" set status_id = 4 where id = :id", nativeQuery = true)
    void paymentError(@Param("id") Long id);
}
