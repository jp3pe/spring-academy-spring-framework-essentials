package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity
public class RestSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http,
            HandlerMappingIntrospector introspector
    ) throws Exception {
        MvcRequestMatcher.Builder mvc = new MvcRequestMatcher.Builder(introspector);

        // @formatter:off
        http.authorizeHttpRequests((authz) -> authz
                        .requestMatchers(mvc.pattern(HttpMethod.GET, "/accounts/**")).hasAnyRole("USER", "ADMIN", "SUPERADMIN")
                        .requestMatchers(mvc.pattern(HttpMethod.PUT, "/accounts/**")).hasAnyRole("SUPERADMIN", "ADMIN")
                        .requestMatchers(mvc.pattern(HttpMethod.POST, "/accounts/**")).hasAnyRole("SUPERADMIN", "ADMIN")
                        .requestMatchers(mvc.pattern(HttpMethod.DELETE, "/accounts/**")).hasRole("SUPERADMIN")
                        .requestMatchers(mvc.pattern(HttpMethod.GET, "/authorities")).hasAnyRole("USER", "ADMIN", "SUPERADMIN")
                .anyRequest().denyAll())
        .httpBasic(withDefaults())
        .csrf(CsrfConfigurer::disable);
        // @formatter:on

        return http.build();
    }

/*    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.withUsername("user").password(passwordEncoder.encode("user")).roles("USER").build();
        UserDetails admin = User.withUsername("admin").password(passwordEncoder.encode("admin")).roles("USER", "ADMIN")
                .build();
        UserDetails superadmin = User.withUsername("superadmin").password(passwordEncoder.encode("superadmin"))
                .roles("USER", "ADMIN", "SUPERADMIN").build();

        return new InMemoryUserDetailsManager(user, admin, superadmin);
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
