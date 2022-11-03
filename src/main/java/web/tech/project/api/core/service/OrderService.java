package web.tech.project.api.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.tech.project.api.core.mapper.OrderMapper;
import web.tech.project.api.core.mapper.StatusMapper;
import web.tech.project.api.core.model.OrderDto;
import web.tech.project.db.entity.Order;
import web.tech.project.db.entity.Status;
import web.tech.project.db.repository.MenuRepository;
import web.tech.project.db.repository.OrderRepository;
import web.tech.project.db.repository.TableRepository;

import java.util.List;

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
    @Autowired
    private StatusMapper statusMapper;

    public OrderDto addOrder(OrderDto order) {
        Order orderNew = orderMapper.map(order, Order.class);
        orderNew.setMenu(menuRepository.getReferenceById(order.getMenu().getId()));
        orderNew.setTable(tableRepository.getReferenceById(order.getTable().getId()));
        orderNew = orderRepository.save(orderNew);
        return orderMapper.map(orderNew, OrderDto.class);
    }

    public List<OrderDto> addOrders(List<OrderDto> orderDtoList) {
        List<Order> orderList = orderMapper.mapAsList(orderDtoList, Order.class);
        for (int i = 0; i < orderList.toArray().length; i++) {
            orderList.get(i).setTable(tableRepository.getReferenceById(orderList.get(i).getTable().getId()));
            orderList.get(i).setMenu(menuRepository.getReferenceById(orderList.get(i).getMenu().getId()));
        }
        orderList = orderRepository.saveAll(orderList);
        return orderMapper.mapAsList(orderList, OrderDto.class);
    }

    public OrderDto getById(Long id) {
        Order order = orderRepository.getReferenceById(id);
        return orderMapper.map(order, OrderDto.class);
    }

    public void changeStatus(OrderDto orderDto, Long id) {
        Order order = orderRepository.getReferenceById(id);
        order.setStatus(statusMapper.map(orderDto.getStatus(), Status.class));
        orderRepository.save(order);
    }
}