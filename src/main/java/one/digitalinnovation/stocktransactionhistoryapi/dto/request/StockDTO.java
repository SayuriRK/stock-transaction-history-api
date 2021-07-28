package one.digitalinnovation.stocktransactionhistoryapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digitalinnovation.stocktransactionhistoryapi.enums.ActionType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockDTO {

    private Long id;

    @NotNull
    @Size(min = 5, max = 6)
    private String tickerSymbol;

    @NotNull
    private LocalDate date;

    @NotNull
    @Max(10000)
    private Integer quantity;

    @NotNull
    @Size(min = 1, max = 100)
    private String totalPaid;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ActionType type;

}
