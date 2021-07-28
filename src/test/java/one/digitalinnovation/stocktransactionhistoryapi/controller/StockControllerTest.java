package one.digitalinnovation.stocktransactionhistoryapi.controller;

import one.digitalinnovation.stocktransactionhistoryapi.builder.StockDTOBuilder;
import one.digitalinnovation.stocktransactionhistoryapi.dto.request.StockDTO;
import one.digitalinnovation.stocktransactionhistoryapi.exception.StockNotFoundException;
import one.digitalinnovation.stocktransactionhistoryapi.service.StockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Collections;

import static one.digitalinnovation.stocktransactionhistoryapi.utils.JsonConversionUtils.asJsonString;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class StockControllerTest {
    private static final String STOCK_API_URL_PATH = "/api/v1/STH";
    private static final long INVALID_STOCK_ID = 2l;

    private MockMvc mockMvc;

    @Mock
    private StockService stockService;

    @InjectMocks
    private StockController stockController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(stockController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenPOSTIsCalledWithoutRequiredFieldThenErrorIsReturned() throws Exception {
        //given
        StockDTO stockDTO = StockDTOBuilder.builder().build().toStockDTO();
        stockDTO.setQuantity(null);

        //then
        mockMvc.perform(post(STOCK_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(stockDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenGETIsCalledWithValidIdThenOkStatusIsReturned() throws Exception {
        //given
        StockDTO stockDTO = StockDTOBuilder.builder().build().toStockDTO();

        //when
        when(stockService.findById(stockDTO.getId())).thenReturn(stockDTO);

        //then
        mockMvc.perform(MockMvcRequestBuilders.get(STOCK_API_URL_PATH + "/" + stockDTO.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void whenGETIsCalledWithoutCorrectIdThenNotFoundStatusIsReturned() throws Exception {
        //given
        StockDTO stockDTO = StockDTOBuilder.builder().build().toStockDTO();

        //when
        when(stockService.findById(stockDTO.getId())).thenThrow(StockNotFoundException.class);

        //then
        mockMvc.perform(MockMvcRequestBuilders.get(STOCK_API_URL_PATH + "/" + stockDTO.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenGETListWithStockIsCalledThenOkStatusIsReturned() throws Exception {
        //given
        StockDTO stockDTO = StockDTOBuilder.builder().build().toStockDTO();

        //when
        when(stockService.listAll()).thenReturn(Collections.singletonList(stockDTO));

        //then
        mockMvc.perform(MockMvcRequestBuilders.get(STOCK_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].tickerSymbol", is(stockDTO.getTickerSymbol())))
                .andExpect(jsonPath("$[0].totalPaid", is(stockDTO.getTotalPaid())))
                .andExpect(jsonPath("$[0].type", is(stockDTO.getType().toString())));
    }

    @Test
    void whenGETListWithoutStockIsCalledThenOkStatusIsReturned() throws Exception {
        //given
        StockDTO stockDTO = StockDTOBuilder.builder().build().toStockDTO();

        //when
        when(stockService.listAll()).thenReturn(Collections.singletonList(stockDTO));

        //then
        mockMvc.perform(MockMvcRequestBuilders.get(STOCK_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void whenDELETEIsCalledWithValidIdThenNoContentStatusIsReturned() throws Exception {
        //given
        StockDTO stockDTO = StockDTOBuilder.builder().build().toStockDTO();

        //when
        doNothing().when(stockService).deleteById(stockDTO.getId());

        //then
        mockMvc.perform(MockMvcRequestBuilders.delete(STOCK_API_URL_PATH + "/" + stockDTO.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void whenDELETEIsCalledWithInvalidIdThenNotFoundStatusIsReturned() throws Exception {
        //when
        doThrow(StockNotFoundException.class).when(stockService).deleteById(INVALID_STOCK_ID);

        //then
        mockMvc.perform(MockMvcRequestBuilders.delete(STOCK_API_URL_PATH + "/" + INVALID_STOCK_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}

