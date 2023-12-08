package sg.darren.ms.auth.service;

import io.jsonwebtoken.lang.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.darren.ms.auth.exception.DataDuplicateException;
import sg.darren.ms.auth.exception.DataNotFoundException;
import sg.darren.ms.auth.model.entity.UserEntity;
import sg.darren.ms.auth.model.enums.TokenValidUnitEnum;
import sg.darren.ms.auth.model.role.RoleResDto;
import sg.darren.ms.auth.model.user.UserCreateReqDto;
import sg.darren.ms.auth.model.user.UserMapper;
import sg.darren.ms.auth.model.user.UserResDto;
import sg.darren.ms.auth.model.user.UserUpdateReqDto;
import sg.darren.ms.auth.repository.UserRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleService roleService;

    public UserResDto getUserByUsername(String username) {
        UserEntity entity = userRepository.findByUsername(username);
        if (!Objects.isNull(entity)) {
            return userMapper.entityToResDto(entity);
        } else {
            return null;
        }
    }

    public List<UserResDto> getUsers() {
        List<UserEntity> list = userRepository.findAll();
        return list.stream()
                .map(entity -> userMapper.entityToResDto(entity))
                .collect(Collectors.toList());
    }

    public boolean isUsernameExists(String username) {
        return Objects.nonNull(userRepository.findByUsername(username));
    }

    public UserResDto create(UserCreateReqDto dto) {
        // check if username exists
        if (isUsernameExists(dto.getUsername())) {
            throw new DataDuplicateException("Username has been registered.");
        }
        // check if all roles are valid
        List<RoleResDto> roles = roleService.getRoleByRoleIds(dto.getRoles());
        if (Collections.isEmpty(roles) || dto.getRoles().size() != roles.size()) {
            throw DataNotFoundException.roleIdNotFound(dto.getRoles().toString());
        }
        // save
        UserEntity entity = userMapper.createDtoToEntity(dto);
        entity = userRepository.save(entity);
        return userMapper.entityToResDto(entity);
    }

    public UserResDto updateByUsername(String username, UserUpdateReqDto dto) {
        // check if all roles are valid
        List<RoleResDto> roles = roleService.getRoleByRoleIds(dto.getRoles());
        if (Collections.isEmpty(roles) || dto.getRoles().size() != roles.size()) {
            throw DataNotFoundException.roleIdNotFound(dto.getRoles().toString());
        }
        // check if username exists
        UserEntity oldEntity = userRepository.findByUsername(username);
        if (Objects.isNull(oldEntity)) {
            throw DataNotFoundException.usernameNotFound(username);
        }
        // save
        UserEntity savedEntity = userRepository.save(userMapper.updateDtoToEntity(dto, oldEntity));
        return userMapper.entityToResDto(savedEntity);
    }

    public void deleteByUsername(String username) {
        if (!isUsernameExists(username)) {
            throw DataNotFoundException.usernameNotFound(username);
        }
        userRepository.deleteByUsername(username);
    }

    public Integer getHighestTokenValid(String username) {
        List<String> roleIds = userRepository.findByUsername(username).getRoles();
        List<RoleResDto> roleList = roleService.getRoleByRoleIds(roleIds);
        return roleList.stream()
                .map(role -> {
                    int value = role.getTokenValidValue();
                    if (TokenValidUnitEnum.MINUTE == role.getTokenValidUnit()) {
                        return 1000 * 60 * value;
                    } else if (TokenValidUnitEnum.HOUR == role.getTokenValidUnit()) {
                        return 1000 * 60 * 60 * value;
                    } else if (TokenValidUnitEnum.DAY == role.getTokenValidUnit()) {
                        return 1000 * 60 * 60 * 24 * value;
                    } else {
                        return 0;
                    }
                })
                .toList()
                .stream()
                .sorted(Comparator.reverseOrder())
                .toList().get(0);
    }

}
