package one.digitalinnovation.stocktransactionhistoryapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digitalinnovation.stocktransactionhistoryapi.enums.ActionType;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {

    private Long id;

    @Enumerated(EnumType.STRING)
    private ActionType type;

    @NotEmpty
    @Size(min = 1, max = 14)
    private String quantity;

    @NotEmpty
    @Size(min = 1, max = 200)
    private String totalPaid;

}
