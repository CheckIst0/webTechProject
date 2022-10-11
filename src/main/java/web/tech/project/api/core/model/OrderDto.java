package web.tech.project.api.core.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto {
//    @ApiModelProperty(value = "ID записи")
    private Long id;
//    @ApiModelProperty(value = "ID клиента")
    private String client;
//    @ApiModelProperty(value = "Стол, занятый клиентом")
    private TableDto table;
//    @ApiModelProperty(value = "Заказанное блюдо")
    private MenuDto menu;
}
