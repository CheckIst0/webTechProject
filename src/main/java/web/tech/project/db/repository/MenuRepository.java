package web.tech.project.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import web.tech.project.db.entity.Menu;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    @Query(value = "Select m.* from menu m where m.category_id = :categoryId", nativeQuery = true)
    List<Menu> findMenuByCategory(@Param("categoryId") int categoryId);

    @Query(value = "Select m.id from menu m", nativeQuery = true)
    List<Long> findAllMenuId();

    @Modifying
    @Transactional
    @Query(value = "update menu set image = :image where id = :id", nativeQuery = true)
    void setImageToMenu(@Param("image") byte[] image, @Param("id") Long id);
}