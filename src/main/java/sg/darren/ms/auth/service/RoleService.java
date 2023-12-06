package sg.darren.ms.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sg.darren.ms.auth.model.entity.RoleEntity;
import sg.darren.ms.auth.model.role.RoleMapper;
import sg.darren.ms.auth.model.role.RoleCreateReqDto;
import sg.darren.ms.auth.repository.RoleRepository;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleEntity getByRoleId(String roleId) {
        return roleRepository.findByRoleId(roleId);
    }

    public boolean checkRoleIdExists(String roleId) {
        return Objects.nonNull(roleRepository.findByRoleId(roleId));
    }

    public void createRole(RoleCreateReqDto dto) {
        roleRepository.save(roleMapper.createDtoToEntity(dto));
    }

}
