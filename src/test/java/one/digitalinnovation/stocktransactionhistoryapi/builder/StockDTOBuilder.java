package one.digitalinnovation.stocktransactionhistoryapi.builder;

import lombok.Builder;
import one.digitalinnovation.stocktransactionhistoryapi.dto.request.StockDTO;
import one.digitalinnovation.stocktransactionhistoryapi.enums.ActionType;

import java.time.LocalDate;

@Builder
public class StockDTOBuilder {
    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String tickerSymbol = "VALE3";

    @Builder.Default
    private LocalDate date = LocalDate.of(2010,10,1);

    @Builder.Default
    private String totalPaid = "R$ 5639,45";

    @Builder.Default
    private int quantity = 10;

    @Builder.Default
    private ActionType type = ActionType.SELL;

    public StockDTO toStockDTO() {
        return new StockDTO(id,
                tickerSymbol,
                date,
                quantity,
                totalPaid,
                type);
    }
}
