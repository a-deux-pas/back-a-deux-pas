package adeuxpas.back.dto.mapper;

import adeuxpas.back.dto.CityAndPostalCodeResponseDTO;
import adeuxpas.back.entity.User;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface UserMapper {
    CityAndPostalCodeResponseDTO userToCityAndPostalCodeDTO(User user);
}
