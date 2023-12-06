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
        return modelMapper.map(dto, RoleEntity.class);
    }

}
