package web.tech.project.api.core.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MealDto {

    @Schema(description = "id записи")
    private Long id;
    @Schema(description = "Блюдо, заказанное клиентом")
    private MenuDto menu;
}
