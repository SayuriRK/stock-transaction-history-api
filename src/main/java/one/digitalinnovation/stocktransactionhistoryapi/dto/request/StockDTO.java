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
    @Size(min = 4, max = 7)
    private String tickerSymbol;

    @NotNull
    private LocalDate date;

    @NotNull
    @Max(1000)
    private Integer quantity;

    @NotNull
    @Size(min = 1, max = 200)
    private String totalPaid;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ActionType type;

}
