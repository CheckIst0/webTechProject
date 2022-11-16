package web.tech.project.api.core.configs;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import web.tech.project.api.core.configs.MyBasicAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private MyBasicAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("adamFrank")
                .password("2Rt3f_m3@3y")
                .roles("USER")
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("CheckIst0")
                .password("z4:20=)13#V")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .cors().and()
                .csrf().disable()
                .authorizeRequests(auth -> {
                    auth.antMatchers(HttpMethod.GET).permitAll();
                    auth.antMatchers("/api/order/**").permitAll();
                    auth.antMatchers("/api/payment/**").permitAll();
                    auth.antMatchers("/api/meal/**").permitAll();
                    auth.anyRequest().hasRole("ADMIN");
                })
                .httpBasic()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and().build();
    }
}