package web.tech.project.api.core.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;
import web.tech.project.api.core.model.TableDto;
import web.tech.project.db.entity.Table;

@Component
public class TableMapper extends ConfigurableMapper {
    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(Table.class, TableDto.class)
                .byDefault()
                .register();
    }
}
