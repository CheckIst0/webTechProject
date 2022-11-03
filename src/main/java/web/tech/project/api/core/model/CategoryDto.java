package web.tech.project.api.core.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
