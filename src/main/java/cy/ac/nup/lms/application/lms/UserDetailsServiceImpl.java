package cy.ac.nup.lms.application.lms;

import cy.ac.nup.lms.domain.Username;
import cy.ac.nup.lms.usecase.access.UserExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserExtractor userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(Username.from(username)).orElseThrow();
    }
}
