package web.tech.project.api.core.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;
import web.tech.project.api.core.model.MealDto;
import web.tech.project.db.entity.Meal;

@Component
public class MealMapper extends ConfigurableMapper {
    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(Meal.class, MealDto.class)
                .byDefault()
                .register();
    }
}
