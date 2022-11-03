package web.tech.project.api.core.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;
import web.tech.project.api.core.model.OrderDto;
import web.tech.project.api.core.model.StatusDto;
import web.tech.project.db.entity.Order;
import web.tech.project.db.entity.Status;

@Component
public class StatusMapper extends ConfigurableMapper {
    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(Status.class, StatusDto.class)
                .byDefault()
                .register();
    }
}
