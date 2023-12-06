package sg.darren.ms.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.darren.ms.auth.exception.DataDuplicateException;
import sg.darren.ms.auth.exception.DataNotFoundException;
import sg.darren.ms.auth.model.entity.UserEntity;
import sg.darren.ms.auth.model.user.UserCreateReqDto;
import sg.darren.ms.auth.model.user.UserMapper;
import sg.darren.ms.auth.model.user.UserResDto;
import sg.darren.ms.auth.model.user.UserUpdateReqDto;
import sg.darren.ms.auth.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public UserResDto getUserByUsername(String username) {
        Optional<UserEntity> entity = userRepository.findByUsername(username);
        return entity.map(userEntity -> userMapper.entityToResDto(userEntity))
                .orElse(null);
    }

    public List<UserResDto> getUsers() {
        List<UserEntity> list = userRepository.findAll();
        return list.stream()
                .map(entity -> userMapper.entityToResDto(entity))
                .collect(Collectors.toList());
    }

    public boolean isUsernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public UserResDto create(UserCreateReqDto dto) {
        if (isUsernameExists(dto.getUsername())) {
            throw new DataDuplicateException("Username has been registered.");
        }
        UserEntity entity = userMapper.createDtoToEntity(dto);
        entity = userRepository.save(entity);
        return userMapper.entityToResDto(entity);
    }

    public UserResDto updateByUsername(String username, UserUpdateReqDto dto) {
        Optional<UserEntity> optionalEntity = userRepository.findByUsername(username);
        if (optionalEntity.isEmpty()) {
            throw DataNotFoundException.usernameNotFound(username);
        }
        UserEntity entity = userRepository.save(userMapper.updateDtoToEntity(dto, optionalEntity.get()));
        return userMapper.entityToResDto(entity);
    }

    public void deleteByUsername(String username) {
        if (!isUsernameExists(username)) {
            throw DataNotFoundException.usernameNotFound(username);
        }
        userRepository.deleteByUsername(username);
    }

}
