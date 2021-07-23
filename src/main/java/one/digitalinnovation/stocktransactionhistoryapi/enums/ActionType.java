package one.digitalinnovation.stocktransactionhistoryapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ActionType {

    SELL  ("SELL"),
    BUY ("BUY");

    private final String description;
}
