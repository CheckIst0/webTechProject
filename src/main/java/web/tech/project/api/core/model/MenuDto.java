package web.tech.project.api.core.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MenuDto {
//    @ApiModelProperty(value = "ID записи")
    private Long id;
//    @ApiModelProperty(value = "Название блюда в меню")
    private String title;
//    @ApiModelProperty(value = "Категория")
    private CategoryDto category;
//    @ApiModelProperty(value = "Стоимость")
    private int price;
//    @ApiModelProperty(value = "Статус наличия")
    private boolean isAvailable;
//    @ApiModelProperty(value = "Описание")
    private String description;
//    @ApiModelProperty(value = "Масса")
    private int mass;
//    @ApiModelProperty(value = "Путь к картинке")
    private String imageSource;
}
