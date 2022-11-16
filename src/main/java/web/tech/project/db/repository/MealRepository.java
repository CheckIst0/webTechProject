package web.tech.project.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.tech.project.db.entity.Meal;

public interface MealRepository extends JpaRepository<Meal, Long> {

}
