package web.tech.project.api.core.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;
import web.tech.project.api.core.model.OrderDto;
import web.tech.project.db.entity.Order;

@Component
public class OrderMapper extends ConfigurableMapper{
    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(Order.class, OrderDto.class)
                .byDefault()
                .register();
    }
}
