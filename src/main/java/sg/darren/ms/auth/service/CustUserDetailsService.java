package sg.darren.ms.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sg.darren.ms.auth.model.entity.UserEntity;
import sg.darren.ms.auth.model.user.CustUserDetails;
import sg.darren.ms.auth.repository.UserRepository;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CustUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity entity = userRepository.findByUsername(username);
        if (!Objects.isNull(entity)) {
            return new CustUserDetails(entity);
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

}
