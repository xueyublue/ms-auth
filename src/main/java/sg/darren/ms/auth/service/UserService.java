package sg.darren.ms.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.darren.ms.auth.model.entity.UserEntity;
import sg.darren.ms.auth.model.user.UserMapper;
import sg.darren.ms.auth.model.user.UserRegisterReqDto;
import sg.darren.ms.auth.model.user.UserResDto;
import sg.darren.ms.auth.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public UserResDto create(UserRegisterReqDto dto) {
        UserEntity entity = userMapper.registerDtoToEntity(dto);
        entity = userRepository.save(entity);
        return userMapper.entityToResDto(entity);
    }

    public UserResDto getUserByUsername(String username) {
        Optional<UserEntity> entity = userRepository.findByUsername(username);
        return entity.map(userEntity -> userMapper.entityToResDto(userEntity))
                .orElse(null);
    }

}
