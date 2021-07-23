package one.digitalinnovation.stocktransactionhistoryapi.controller;

import lombok.AllArgsConstructor;
import one.digitalinnovation.stocktransactionhistoryapi.dto.request.StockDTO;
import one.digitalinnovation.stocktransactionhistoryapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.stocktransactionhistoryapi.exception.StockNotFoundException;
import one.digitalinnovation.stocktransactionhistoryapi.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping ("/api/v1/STH")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StockController {

    private StockService stockService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createStock (@Valid @RequestBody StockDTO stockDTO) {
        return stockService.createStock(stockDTO);
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StockDTO findById (@PathVariable Long id) throws StockNotFoundException{
        return stockService.findById(id);
    }
    @GetMapping
    public List<StockDTO> listAll(){
    return stockService.listAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDTO updateByID(@PathVariable Long id,@Valid @RequestBody StockDTO stockDTO) throws StockNotFoundException{
        return stockService.updateById(id,stockDTO);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById (@PathVariable Long id) throws StockNotFoundException{
        stockService.deleteById(id);
    }
}
