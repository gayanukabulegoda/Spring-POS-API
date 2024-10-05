package lk.ijse.springposapi.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    private final ModelMapper modelMapper;

    @Autowired
    public Mapping(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <T> T convertToDTO(Object entity, Class<T> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    public <T> T convertToEntity(Object dto, Class<T> entityClass) {
        return modelMapper.map(dto, entityClass);
    }

    public <T> List<T> convertToDTOList(Object entityList) {
        return modelMapper.map(entityList, new TypeToken<List<T>>() {
        }.getType());
    }

    public <T> List<T> convertToEntityList(Object dtoList) {
        return modelMapper.map(dtoList, new TypeToken<List<T>>() {
        }.getType());
    }
}
