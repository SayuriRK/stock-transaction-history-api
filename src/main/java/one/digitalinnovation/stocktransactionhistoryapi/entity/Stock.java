package one.digitalinnovation.stocktransactionhistoryapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digitalinnovation.stocktransactionhistoryapi.enums.ActionType;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tickerSymbol;

    @Column(nullable = false)
    private LocalDate date;

    @Column (nullable = false)
    private int quantity;

    @Column (nullable = false)
    private String totalPaid;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ActionType type;


}
