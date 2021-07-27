package one.digitalinnovation.stocktransactionhistoryapi.repository;

import one.digitalinnovation.stocktransactionhistoryapi.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findByTickerSymbol(String tickerSymbol);
}
