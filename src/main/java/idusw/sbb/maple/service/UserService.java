package idusw.sbb.maple.service;

import idusw.sbb.maple.domain.User;
import idusw.sbb.maple.domain.UserDetail;
import idusw.sbb.maple.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(id);

        if(user == null) {
            throw new UsernameNotFoundException(id);
        }

        return new UserDetail(user);
    }
}
