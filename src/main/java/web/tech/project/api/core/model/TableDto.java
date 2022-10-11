package web.tech.project.api.core.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TableDto {
//    @ApiModelProperty(value = "ID записи")
    private Long id;
//    @ApiModelProperty(value = "Свободен ли столик")
    private boolean isFree;
}
