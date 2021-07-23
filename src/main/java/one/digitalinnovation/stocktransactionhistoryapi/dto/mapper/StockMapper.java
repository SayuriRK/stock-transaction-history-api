package one.digitalinnovation.stocktransactionhistoryapi.dto.mapper;

import one.digitalinnovation.stocktransactionhistoryapi.dto.request.StockDTO;
import one.digitalinnovation.stocktransactionhistoryapi.entity.Stock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StockMapper {
    StockMapper INSTANCE = Mappers.getMapper(StockMapper.class);

    @Mapping(target = "date", source = "date", dateFormat = "dd-MM-yyyy")
    Stock toModel(StockDTO dto);

    StockDTO toDTO(Stock dto);
}
