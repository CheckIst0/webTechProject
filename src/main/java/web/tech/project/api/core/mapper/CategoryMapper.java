package web.tech.project.api.core.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;
import web.tech.project.api.core.model.CategoryDto;
import web.tech.project.db.entity.Category;

@Component
public class CategoryMapper extends ConfigurableMapper {
    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(Category.class, CategoryDto.class)
                .byDefault()
                .register();
    }
}
