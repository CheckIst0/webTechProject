package web.tech.project.api.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.tech.project.api.core.mapper.OrderMapper;
import web.tech.project.api.core.mapper.StatusMapper;
import web.tech.project.api.core.model.OrderDto;
import web.tech.project.db.entity.Meal;
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
        orderNew = orderRepository.save(orderNew);
        return orderMapper.map(orderNew, OrderDto.class);
    }

    public OrderDto getById(Long id) {
        Order order = orderRepository.getReferenceById(id);
        List<Meal> meals = order.getMeals();
        for (Meal meal : meals) {
            meal.getMenu().setImage(null);
        }
        order.setMeals(meals);
        return orderMapper.map(order, OrderDto.class);
    }

    public void changeStatus(OrderDto orderDto, Long id) {
        Order order = orderRepository.getReferenceById(id);
        order.setStatus(statusMapper.map(orderDto.getStatus(), Status.class));
        orderRepository.save(order);
    }

    public List<OrderDto> getAllOrders() {
        List<Order> orderList = orderRepository.findAll();
        return orderMapper.mapAsList(orderList, OrderDto.class);
    }

    public String paymentSuccess(Long id) {
        orderRepository.paymentSuccess(id);
        return "The payment was successful";
    }

    public String paymentError(Long id) {
        orderRepository.paymentError(id);
        return "An error occurred during the payment";
    }
}