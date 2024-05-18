package cy.ac.nup.lms.application.lms;

import cy.ac.nup.lms.domain.Role;
import cy.ac.nup.lms.usecase.JwtUsernameExtractor;
import cy.ac.nup.lms.usecase.JwtValidator;
import cy.ac.nup.lms.usecase.access.UserExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@RequiredArgsConstructor
public class SecurityConfiguration { ;

    @Bean
    public SecurityFilterChain securityFilter(HttpSecurity http, UserDetailsService userDetailsService, JwtTokenFilter jwtTokenFilter) throws Exception { // @formatter:off
        http = http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable);

        http = http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http = http.userDetailsService(userDetailsService);

        http = http.authorizeHttpRequests(request ->
                request.requestMatchers(new AntPathRequestMatcher("/api/auth/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/admin/**")).hasRole("ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/api/user/**")).hasRole("USER")
                        .anyRequest().authenticated());

        http.addFilterBefore(
                jwtTokenFilter,
                UsernamePasswordAuthenticationFilter.class
        );

        return http.build();
    } // @formatter:on

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws
            Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public UserDetailsServiceImpl userDetailsService(UserExtractor userExtractor) {
        return new UserDetailsServiceImpl(userExtractor);
    }

    @Bean
    public JwtTokenFilter jwtTokenFilter(JwtValidator jwtValidator, UserDetailsServiceImpl userDetailsService,
            JwtUsernameExtractor jwtUsernameExtractor) {
        return new JwtTokenFilter(jwtValidator, userDetailsService, jwtUsernameExtractor);
    }
}
