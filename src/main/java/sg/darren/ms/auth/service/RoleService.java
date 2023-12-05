package sg.darren.ms.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sg.darren.ms.auth.model.role.RoleRegisterReqDto;
import sg.darren.ms.auth.model.entity.RoleEntity;
import sg.darren.ms.auth.repository.RoleRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleEntity getByRoleId(String roleId) {
        return roleRepository.findByRoleId(roleId);
    }

    public void createRole(RoleRegisterReqDto dto) {
        roleRepository.save(RoleEntity.builder()
                .roleId(dto.getRoleId())
                .roleName(dto.getRoleName())
                .build());
    }

}
