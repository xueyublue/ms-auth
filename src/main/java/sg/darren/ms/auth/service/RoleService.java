package sg.darren.ms.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sg.darren.ms.auth.exception.DataDuplicateException;
import sg.darren.ms.auth.exception.DataNotFoundException;
import sg.darren.ms.auth.model.entity.RoleEntity;
import sg.darren.ms.auth.model.role.RoleCreateReqDto;
import sg.darren.ms.auth.model.role.RoleMapper;
import sg.darren.ms.auth.model.role.RoleResDto;
import sg.darren.ms.auth.model.role.RoleUpdateReqDto;
import sg.darren.ms.auth.repository.RoleRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleResDto getRoleByRoleId(String roleId) {
        RoleEntity entity = roleRepository.findByRoleId(roleId);
        if (Objects.isNull(entity)) {
            return null;
        }
        return roleMapper.entityToResDto(entity);
    }

    public List<RoleResDto> getRoles() {
        List<RoleEntity> list = roleRepository.findAll();
        return list.stream()
                .map(roleMapper::entityToResDto)
                .collect(Collectors.toList());
    }

    public boolean isRoleIdExists(String roleId) {
        return Objects.nonNull(roleRepository.findByRoleId(roleId));
    }

    public RoleResDto create(RoleCreateReqDto dto) {
        if (isRoleIdExists(dto.getRoleId())) {
            throw new DataDuplicateException("Role ID has been registered.");
        }
        RoleEntity entity = roleMapper.createDtoToEntity(dto);
        entity = roleRepository.save(entity);
        return roleMapper.entityToResDto(entity);
    }

    public RoleResDto updateByRoleId(String roleId, RoleUpdateReqDto dto) {
        RoleEntity entity = roleRepository.findByRoleId(roleId);
        if (Objects.isNull(entity)) {
            throw DataNotFoundException.usernameNotFound(roleId);
        }
        entity = roleRepository.save(roleMapper.updateDtoToEntity(dto, entity));
        return roleMapper.entityToResDto(entity);
    }

    public void deleteByRoleId(String roleId) {
        if (!isRoleIdExists(roleId)) {
            throw DataNotFoundException.usernameNotFound(roleId);
        }
        roleRepository.deleteByRoleId(roleId);
    }

}
