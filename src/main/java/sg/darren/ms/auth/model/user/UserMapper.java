package sg.darren.ms.auth.model.user;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import sg.darren.ms.auth.model.entity.UserEntity;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper modelMapper;
    private final PasswordEncoder encoder;

    public UserEntity createDtoToEntity(UserCreateReqDto dto) {
        UserEntity entity = modelMapper.map(dto, UserEntity.class);
        entity.setPassword(encoder.encode(dto.getPassword()));
        entity.setRoles(String.join(",", dto.getRoles()));
        return entity;
    }

    public UserEntity updateDtoToEntity(UserUpdateReqDto dto, UserEntity entity) {
        entity.setFullName(dto.getFullName());
        entity.setEmail(dto.getEmail());
        entity.setRoles(String.join(",", dto.getRoles()));
        return entity;
    }

    public UserResDto entityToResDto(UserEntity entity) {
        UserResDto dto = modelMapper.map(entity, UserResDto.class);
        dto.setRoles(List.of(entity.getRoles().split(",")));
        return dto;
    }

}
