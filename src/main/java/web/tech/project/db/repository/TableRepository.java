package web.tech.project.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import web.tech.project.db.entity.Table;

import java.util.List;

public interface TableRepository extends JpaRepository<Table, Long> {

    @Query(value = "Select t.id from \"table\" t", nativeQuery = true)
    List<Long> findAllTableId();
}
