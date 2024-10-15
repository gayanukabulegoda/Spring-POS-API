package lk.ijse.springposapi.util;

import lk.ijse.springposapi.dto.impl.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Mapping {
    private final ModelMapper modelMapper;

    public <T> T convertToDTO(Object entity, Class<T> dtoClass) {
        if (dtoClass == null) return null;
        return modelMapper.map(entity, dtoClass);
    }

    public <T> T convertToEntity(Object dto, Class<T> entityClass) {
        if (entityClass == null) return null;
        return modelMapper.map(dto, entityClass);
    }

    public <T> List<T> convertToDTOList(Object entityList, Class<T> dtoClass) {
        if (entityList == null) return null;
        if (dtoClass.equals(OrderDTO.class)) {
            return modelMapper.map(entityList, new TypeToken<List<OrderDTO>>() {}.getType());
        }
        return modelMapper.map(entityList, new TypeToken<List<T>>() {}.getType());
    }
}
