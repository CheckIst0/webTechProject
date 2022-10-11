package web.tech.project.api.core.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
//    @ApiModelProperty(value = "ID записи")
    private Long id;
//    @ApiModelProperty(value = "Название категории")
    private String title;
}
