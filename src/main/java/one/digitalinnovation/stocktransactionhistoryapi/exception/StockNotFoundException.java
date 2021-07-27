package one.digitalinnovation.stocktransactionhistoryapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StockNotFoundException extends Exception {

    public StockNotFoundException (Long id) {
        super(String.format("Stock with ID %s was not found!", id));
    }

}
