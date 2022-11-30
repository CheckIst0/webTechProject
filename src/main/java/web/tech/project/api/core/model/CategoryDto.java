package web.tech.project.api.core.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
    @Schema(description = "id записи")
    private Long id;
    @Schema(description = "Название категории")
    private String title;
    @Schema(description = "Картинка категории")
    private byte[] img;
    @Schema(description = "Техническая информация")
    private String name;
    @Schema(description = "Список позиций меню по данной категории")
    private List<MenuDto> menus;
}
