package sg.darren.ms.auth.model.role;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import sg.darren.ms.auth.model.entity.RoleEntity;

@Component
@RequiredArgsConstructor
public class RoleMapper {

    private final ModelMapper modelMapper;

    public RoleEntity createDtoToEntity(RoleCreateReqDto dto) {
        return RoleEntity.builder()
                .roleId(dto.getRoleId())
                .roleName(dto.getRoleName())
                .tokenValidValue(dto.getTokenValidValue())
                .tokenValidUnit(dto.getTokenValidUnit())
                .build();
    }

    public RoleEntity updateDtoToEntity(RoleUpdateReqDto dto, RoleEntity entity) {
        entity.setRoleName(dto.getRoleName());
        entity.setTokenValidValue(dto.getTokenValidValue());
        entity.setTokenValidUnit(dto.getTokenValidUnit());
        return entity;
    }

    public RoleResDto entityToResDto(RoleEntity entity) {
        return modelMapper.map(entity, RoleResDto.class);
    }

}
