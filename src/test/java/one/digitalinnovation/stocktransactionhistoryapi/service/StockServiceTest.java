package one.digitalinnovation.stocktransactionhistoryapi.service;

import one.digitalinnovation.stocktransactionhistoryapi.builder.StockDTOBuilder;
import one.digitalinnovation.stocktransactionhistoryapi.dto.mapper.StockMapper;
import one.digitalinnovation.stocktransactionhistoryapi.dto.request.StockDTO;
import one.digitalinnovation.stocktransactionhistoryapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.stocktransactionhistoryapi.entity.Stock;
import one.digitalinnovation.stocktransactionhistoryapi.exception.StockNotFoundException;
import one.digitalinnovation.stocktransactionhistoryapi.repository.StockRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StockServiceTest {

    private final StockMapper stockMapper = StockMapper.INSTANCE;
    @Mock
    private StockRepository stockRepository;
    @InjectMocks
    private StockService stockService;

    @Test
    void testGivenStockDTOThenReturnSavedMessage() {
        StockDTO expectedSavedStockDTO = StockDTOBuilder.builder().build().toStockDTO();
        Stock expectedSavedStock = stockMapper.toModel(expectedSavedStockDTO);

        when(stockRepository.save(any(Stock.class))).thenReturn(expectedSavedStock);

        MessageResponseDTO expectedSuccessMessage = createExpectedMessageResponse(expectedSavedStock.getId());

        MessageResponseDTO successMessage = stockService.createStock(expectedSavedStockDTO);

        assertEquals(expectedSuccessMessage, successMessage);
    }

    private MessageResponseDTO createExpectedMessageResponse(Long id) {
        return MessageResponseDTO
                .builder()
                .message("Created stock with ID: " + id)
                .build();
    }

    @Test
    void whenValidStockIdIsGivenThenReturnStock() throws StockNotFoundException {

        // given
        StockDTO expectedFoundStockDTO = StockDTOBuilder.builder().build().toStockDTO();
        Stock expectedFoundStock = stockMapper.toModel(expectedFoundStockDTO);

        //when
        when(stockRepository.findById(expectedFoundStock.getId())).thenReturn(Optional.of(expectedFoundStock));

        //then
        StockDTO foundStockDTO = stockService.findById(expectedFoundStockDTO.getId());

        assertThat(foundStockDTO, is(equalTo(expectedFoundStockDTO)));
    }

    @Test
    void whenListIsCalledThenReturnListOfStocks() {

        // given
        StockDTO expectedFoundStockDTO = StockDTOBuilder.builder().build().toStockDTO();
        Stock expectedFoundStock = stockMapper.toModel(expectedFoundStockDTO);

        //when
        when(stockRepository.findAll()).thenReturn(Collections.singletonList(expectedFoundStock));

        //then
        List<StockDTO> foundListStockDTO = stockService.listAll();

        assertThat(foundListStockDTO, is(not(empty())));
        assertThat(foundListStockDTO.get(0), is(equalTo(expectedFoundStockDTO)));
    }

    @Test
    void whenListIsCalledThenReturnEmptyListOfStocks() {

        //when
        when(stockRepository.findAll()).thenReturn(Collections.EMPTY_LIST);

        //then
        List<StockDTO> foundListStocksDTO = stockService.listAll();

        assertThat(foundListStocksDTO, is(empty()));
    }

    @Test
    void whenExclusionIsCalledWithValidIdThenStockShouldBeDeleted() throws StockNotFoundException {
        // given
        StockDTO expectedDeletedStockDTO = StockDTOBuilder.builder().build().toStockDTO();
        Stock expectedDeletedStock = stockMapper.toModel(expectedDeletedStockDTO);

        //when
        when(stockRepository.findById(expectedDeletedStockDTO.getId())).thenReturn(Optional.of(expectedDeletedStock));
        doNothing().when(stockRepository).deleteById(expectedDeletedStockDTO.getId());

        //then
        stockService.deleteById(expectedDeletedStockDTO.getId());

        verify(stockRepository, times(1)).findById(expectedDeletedStockDTO.getId());
        verify(stockRepository, times(1)).deleteById(expectedDeletedStockDTO.getId());
    }

    @Test
    public void whenMatchesFourLettersAndOneNumberThenCorrect() {
        Pattern pattern = Pattern.compile("^[A-Z]{4}[\\d]{1}$");
        Matcher matcher = pattern.matcher("PETR4");
        assertTrue(matcher.matches());
    }
}

