package web.tech.project.api.core.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;
import web.tech.project.api.core.model.MenuDto;
import web.tech.project.db.entity.Menu;

@Component
public class MenuMapper extends ConfigurableMapper {
    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(Menu.class, MenuDto.class)
                .byDefault()
                .register();
    }
}
