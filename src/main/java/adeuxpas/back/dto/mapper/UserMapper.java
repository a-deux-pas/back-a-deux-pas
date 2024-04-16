package adeuxpas.back.dto.mapper;

import adeuxpas.back.dto.CityAndPostalCodeDTO;
import adeuxpas.back.entity.User;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface UserMapper {
    CityAndPostalCodeDTO userToCityAndPostalCodeDTO(User user);
}
