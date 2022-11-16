package web.tech.project.api.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Card {
    private String number;
    private String expiryDate;
    private String cvc;
}
