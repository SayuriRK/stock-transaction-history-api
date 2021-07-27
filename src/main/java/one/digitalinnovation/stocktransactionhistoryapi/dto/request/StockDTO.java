package one.digitalinnovation.stocktransactionhistoryapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockDTO {

    private Long id;

    @NotEmpty
    @Size(min = 4, max = 7)
    private String tickerSymbol;

    @NotNull
    private LocalDate date;

    @Valid
    @NotEmpty
    private List<TransactionDTO> transactions;

}
