package web.tech.project.api.core.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Lob;
import java.io.InputStream;
import java.nio.ByteBuffer;

@Getter
@Setter
@NoArgsConstructor
public class MenuDto {
    @Schema(description = "ID записи")
    private Long id;
    @Schema(description = "Название блюда в меню")
    private String title;
//    @Schema(description = "Категория")
//    private CategoryDto category;
    @Schema(description = "Стоимость")
    private int price;
    @Schema(description = "Статус наличия")
    private boolean isAvailable;
    @Schema(description = "Описание блюда")
    private String description;
    @Schema(description = "Масса")
    private int mass;
    @Schema(description = "Путь к картинке")
    private byte[] image;
}
