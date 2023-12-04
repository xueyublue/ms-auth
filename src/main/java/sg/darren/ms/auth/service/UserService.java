package sg.darren.ms.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sg.darren.ms.auth.model.dto.CustUserDetails;
import sg.darren.ms.auth.model.entity.UserEntity;
import sg.darren.ms.auth.repository.UserRepository;

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

    public String addUser(UserEntity userInfo) {
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        userRepository.save(userInfo);
        return "User Added Successfully";
    }

}
