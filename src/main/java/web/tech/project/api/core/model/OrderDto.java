package web.tech.project.api.core.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto {
    @Schema(description = "ID записи")
    private Long id;
    @Schema(description = "ID клиента")
    private String client;
    @Schema(description = "Стол, занятый клиентом")
    private TableDto table;
    @Schema(description = "Статус заказа")
    private StatusDto status;
    @Schema(description = "Список заказанных блюд")
    private List<MealDto> meals;
}
