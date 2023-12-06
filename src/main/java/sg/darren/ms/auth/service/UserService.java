package sg.darren.ms.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sg.darren.ms.auth.model.auth.CustUserDetails;
import sg.darren.ms.auth.model.entity.UserEntity;
import sg.darren.ms.auth.model.user.UserMapper;
import sg.darren.ms.auth.model.user.UserRegisterReqDto;
import sg.darren.ms.auth.model.user.UserResDto;
import sg.darren.ms.auth.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        return user.map((CustUserDetails::new)).orElseThrow(
                () -> new UsernameNotFoundException("User not found with username: " + username)
        );
    }

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
