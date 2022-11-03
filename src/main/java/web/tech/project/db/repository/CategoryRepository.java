package web.tech.project.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import web.tech.project.db.entity.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "Select c.id from category c", nativeQuery = true)
    List<Long> findAllCategoryId();

    @Query(value = "update \"category\" set img = :image where id = :id", nativeQuery = true)
    void setImageToCategory(@Param("image") byte[] image, @Param("id") Long id);
}
