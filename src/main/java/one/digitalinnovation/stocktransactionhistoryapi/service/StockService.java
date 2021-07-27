package one.digitalinnovation.stocktransactionhistoryapi.service;

import lombok.AllArgsConstructor;
import one.digitalinnovation.stocktransactionhistoryapi.dto.mapper.StockMapper;
import one.digitalinnovation.stocktransactionhistoryapi.dto.request.StockDTO;
import one.digitalinnovation.stocktransactionhistoryapi.dto.request.TransactionDTO;
import one.digitalinnovation.stocktransactionhistoryapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.stocktransactionhistoryapi.entity.Stock;
import one.digitalinnovation.stocktransactionhistoryapi.exception.StockNotFoundException;
import one.digitalinnovation.stocktransactionhistoryapi.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StockService {

    private final StockRepository stockRepository;
    private final StockMapper stockMapper;

    public MessageResponseDTO createStock (StockDTO stockDTO){
        Stock stockToSave = stockMapper.toModel(stockDTO);
        Stock savedStock = stockRepository.save(stockToSave);
        return createMessageResponseDTO (savedStock.getId(), "Created stock with ID");
    }

    public List<StockDTO> listAll(){
        return stockRepository.findAll()
                .stream()
                .map(stockMapper::toDTO)
                .collect(Collectors.toList());
    }
    public StockDTO findById (Long id) throws StockNotFoundException{
        Stock stock = verifyIfExists(id);
        return stockMapper.toDTO(stock);
    }

    public void deleteById (Long id) throws StockNotFoundException{
        verifyIfExists(id);
        stockRepository.deleteById(id);
    }
    public MessageResponseDTO updateById (Long id, StockDTO stockDTO) throws StockNotFoundException{
        verifyIfExists(id);
        Stock stockToUpdate = stockMapper.toModel(stockDTO);
        Stock updatedStock = stockRepository.save(stockToUpdate);
        return createMessageResponseDTO(updatedStock.getId(), "Stock successfully updated with ID ");
    }

    private Stock verifyIfExists(Long id)  throws StockNotFoundException{
    return stockRepository.findById(id)
            .orElseThrow(() -> new StockNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponseDTO(Long id, String message) {
    return MessageResponseDTO
            .builder()
            .message(message + id)
            .build();
    }

}
