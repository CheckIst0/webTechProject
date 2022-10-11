package web.tech.project.api.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.tech.project.api.core.mapper.OrderMapper;
import web.tech.project.api.core.model.OrderDto;
import web.tech.project.db.entity.Order;
import web.tech.project.db.repository.MenuRepository;
import web.tech.project.db.repository.OrderRepository;
import web.tech.project.db.repository.TableRepository;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private TableRepository tableRepository;

    public OrderDto addOrder(OrderDto order) {
        if (menuRepository.findAllMenuId().contains(order.getMenu().getId())
                && tableRepository.findAllTableId().contains(order.getTable().getId())) {
            Order orderNew = orderMapper.map(order, Order.class);
            orderNew.setMenu(menuRepository.getReferenceById(order.getMenu().getId()));
            orderNew.setTable(tableRepository.getReferenceById(order.getTable().getId()));
            orderNew = orderRepository.save(orderNew);
            return orderMapper.map(orderNew, OrderDto.class);
        } else {
            Order orderNew = orderMapper.map(order, Order.class);
            orderNew = orderRepository.save(orderNew);
            return orderMapper.map(orderNew, OrderDto.class);
        }
    }

    public OrderDto getById(Long id) {
        Order order = orderRepository.getReferenceById(id);
        return orderMapper.map(order, OrderDto.class);
    }
}