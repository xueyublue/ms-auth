package sg.darren.ms.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sg.darren.ms.auth.model.auth.CustUserDetails;
import sg.darren.ms.auth.model.entity.UserEntity;
import sg.darren.ms.auth.model.user.UserRegisterReqDto;
import sg.darren.ms.auth.model.user.UserResDto;
import sg.darren.ms.auth.repository.UserRepository;

import java.util.Arrays;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        return user.map((CustUserDetails::new)).orElseThrow(
                () -> new UsernameNotFoundException("User not found with username: " + username)
        );
    }

    public void create(UserRegisterReqDto dto) {
        userRepository.save(UserEntity.builder()
                .username(dto.getUsername())
                .password(encoder.encode(dto.getPassword()))
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .roles(String.join(",", dto.getRoles()))
                .build());
    }

    public UserResDto getUserByUsername(String username) {
        Optional<UserEntity> entity = userRepository.findByUsername(username);
        return entity.map(userEntity -> UserResDto.builder()
                .username(userEntity.getUsername())
                .fullName(userEntity.getFullName())
                .email(userEntity.getEmail())
                .roles(Arrays.stream(userEntity.getRoles().split(",")).toList())
                .createDate(userEntity.getCreateDate())
                .updateDate(userEntity.getUpdateDate())
                .build()).orElse(null);
    }

}
