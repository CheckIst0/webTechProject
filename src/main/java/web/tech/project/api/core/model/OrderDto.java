package web.tech.project.api.core.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Schema(description = "Заказанное блюдо")
    private MenuDto menu;
    @Schema(description = "Статус заказа")
    private StatusDto status;
}
