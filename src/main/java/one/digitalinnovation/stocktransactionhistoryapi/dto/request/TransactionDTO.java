package one.digitalinnovation.stocktransactionhistoryapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digitalinnovation.stocktransactionhistoryapi.enums.ActionType;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {

    private Long id;

    @Enumerated(EnumType.STRING)
    private ActionType type;

    @NotEmpty
    @Max(20)
    private Integer quantity;

    @NotEmpty
    @Max(20)
    private Integer totalpaid;

}
